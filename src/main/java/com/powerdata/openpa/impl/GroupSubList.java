package com.powerdata.openpa.impl;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.*;

public abstract class GroupSubList<T extends Group> extends SubList<T> implements GroupListIfc<T> {
    GroupListIfc<T> _src;

    public GroupSubList(GroupListIfc<T> src, int[] ndx) {
        super(src, ndx);
        _src = src;

//		_grp = new TIntIntHashMap(_size, 0.5f, -1, -1);
//		for(int i=0; i < _size; ++i)
//		{
//			_grp.put(_ndx[i], i);
//		}
    }

    @Override
    public BusList getBuses(int ndx) throws PAModelException {
        return _src.getBuses(_ndx[ndx]);
    }

    @Override
    public SwitchList getSwitches(int ndx) throws PAModelException {
        return _src.getSwitches(_ndx[ndx]);
    }

    @Override
    public LineList getLines(int ndx) throws PAModelException {
        return _src.getLines(_ndx[ndx]);
    }

    @Override
    public SeriesReacList getSeriesReactors(int ndx) throws PAModelException {
        return _src.getSeriesReactors(_ndx[ndx]);
    }

    @Override
    public SeriesCapList getSeriesCapacitors(int ndx) throws PAModelException {
        return _src.getSeriesCapacitors(_ndx[ndx]);
    }

    @Override
    public TransformerList getTransformers(int ndx) throws PAModelException {
        return _src.getTransformers(_ndx[ndx]);
    }

    @Override
    public PhaseShifterList getPhaseShifters(int ndx) throws PAModelException {
        return _src.getPhaseShifters(_ndx[ndx]);
    }

    @Override
    public TwoTermDCLineList getTwoTermDCLines(int ndx) throws PAModelException {
        return _src.getTwoTermDCLines(_ndx[ndx]);
    }

    @Override
    public GenList getGenerators(int ndx) throws PAModelException {
        return _src.getGenerators(_ndx[ndx]);
    }

    @Override
    public LoadList getLoads(int ndx) throws PAModelException {
        return _src.getLoads(_ndx[ndx]);
    }

    @Override
    public ShuntReacList getShuntReactors(int ndx) throws PAModelException {
        return _src.getShuntReactors(_ndx[ndx]);
    }

    @Override
    public ShuntCapList getShuntCapacitors(int ndx) throws PAModelException {
        return _src.getShuntCapacitors(_ndx[ndx]);
    }

    @Override
    public SVCList getSVCs(int ndx) throws PAModelException {
        return _src.getSVCs(_ndx[ndx]);
    }

    @Override
    public T getByBus(Bus b) throws PAModelException {
        return _src.getByBus(b);
    }

    @Override
    public int[] translateBusIndexes(int[] indexes) {
        return _src.translateBusIndexes(indexes);
    }

}
