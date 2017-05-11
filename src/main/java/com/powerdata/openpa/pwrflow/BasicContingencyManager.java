package com.powerdata.openpa.pwrflow;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.CloneModelBuilder;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;
import com.powerdata.openpa.pwrflow.ContingencySet.Contingency;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Run contingencies using simple scheduling algoriths, either serial or using
 * parallel streams
 *
 * @author chris@powerdata.com
 */
public abstract class BasicContingencyManager extends ContingencyManager {
    static BiFunction<PAModel, String, CAWorker> _useratings = CAWorker::new,
            _noratings = CAWorkerNoRat::new;
    BiFunction<PAModel, String, CAWorker> _cawrkr = _useratings;
    ConvergenceList _startPFResults;
    int _cont = 0;
    private boolean _par = false;

    public BasicContingencyManager(PAModel m, ConvergenceList startPfResults) {
        super(m);
        _startPFResults = startPfResults;
        //TODO:  it would be useful to allow ModelBuilder().load to be called
        // as many times as desired to create new models
    }

    public boolean getParallel() {
        return _par;
    }

    public void setParallel(boolean p) {
        _par = p;
    }

    public boolean getIgnoreRatings() {
        return _cawrkr == _noratings;
    }

    public void setIgnoreRatings(boolean i) {
        _cawrkr = i ? _noratings : _useratings;
    }

    @Override
    public void runSet(ContingencySet set) {
        Stream<Contingency> cstream = _par ? set.parallelStream() : set.stream();
        long ts = System.currentTimeMillis();
        System.err.format("Processing %d contingencies\n", set.size());
        cstream.forEach(c -> applyContingency(c));
        long t = System.currentTimeMillis() - ts;
        long sec = Math.round((double) t / 1000.0);
        System.err.format("Contingencies processed in %d sec (avg %6.2f ms)", sec, ((float) t) / ((float) set.size()));
    }

    void applyContingency(Contingency c) {
        try {
            PAModel clm = new CloneModelBuilder(_model, _localcols).load();
            c.execute(clm);
            clm.refreshTopology();
            CAWorker w = _cawrkr.apply(clm, c.getName());
            w.runContingency();
            report(c, w.getResults(_startPFResults), clm);
        } catch (PAModelException e) {
            recordException(c, e);
        }
    }

    static class CAWorkerNoRat extends CAWorker {
        public CAWorkerNoRat(PAModel model, String cname) {
            super(model, cname);
        }

        @Override
        public Set<Result> getOverloads() {
            return Collections.emptySet();
        }
    }
}
