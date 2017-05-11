package com.powerdata.openpa.impl;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.*;
import com.powerdata.openpa.Gen.Mode;
import com.powerdata.openpa.Switch.State;

public class ElectricalIslandListI extends GroupListI<ElectricalIsland> implements ElectricalIslandList {
    static final PAListEnum _PFld = new PAListEnum() {
        @Override
        public ColumnMeta id() {
            return ColumnMeta.IslandID;
        }

        @Override
        public ColumnMeta name() {
            return ColumnMeta.IslandNAME;
        }
    };
    protected FloatData _freq = new FloatData(ColumnMeta.IslandFREQ);
    BoolData _egzd;
    BusList _buses;

    public ElectricalIslandListI(PAModelI model) throws PAModelException {
        super(model, new BusGrpMapBldr(model) {
            @Override
            protected boolean incSW(Switch d) throws PAModelException {
                return d.getState() == State.Closed;
            }

            @Override
            protected boolean incLN(Line d) throws PAModelException {
                return d.isInService();
            }

            @Override
            protected boolean incSR(SeriesReac d) throws PAModelException {
                return d.isInService();
            }

            @Override
            protected boolean incSC(SeriesCap d) throws PAModelException {
                return d.isInService();
            }

            @Override
            protected boolean incTX(Transformer d) throws PAModelException {
                return d.isInService();
            }

            @Override
            protected boolean incPS(PhaseShifter d) throws PAModelException {
                return d.isInService();
            }

            @Override
            protected boolean incD2(TwoTermDCLine d) throws PAModelException {
                return d.isInService();
            }
        }.addAll().getMap(), _PFld);
        _buses = model.getBuses();
        setupEgStatus();
    }

    public ElectricalIslandListI() {
        super();
    }

    ;

    @Override
    public ElectricalIsland get(int index) {
        return new ElectricalIsland(this, index);
    }

    void setupEgStatus() throws PAModelException {
        int n = size();
        boolean[] e = new boolean[_size];
        for (int i = 0; i < n; ++i) {
            for (Gen g : getGenerators(i)) {
                Mode m = g.getMode();
                if (m != Mode.OFF && m != Mode.PMP) {
                    e[i] = true;
                    break;
                }
            }
        }
        _egzd = new BoolData(ColumnMeta.IslandEGZSTATE) {
            @Override
            boolean[] load() throws PAModelException {
                return e;
            }
        };
    }

    @Override
    public boolean isEnergized(int ndx) throws PAModelException {
        return _egzd.get(ndx);
    }

    @Override
    public boolean[] isEnergized() throws PAModelException {
        return _egzd.get();
    }

    @Override
    public float getFreq(int ndx) throws PAModelException {
        return _freq.get(ndx);
    }

    @Override
    public void setFreq(int ndx, float f) throws PAModelException {
        _freq.set(ndx, f);
    }

    @Override
    public float[] getFreq() throws PAModelException {
        return _freq.get();
    }

    @Override
    public void setFreq(float[] f) throws PAModelException {
        _freq.set(f);
    }

    @Override
    public ListMetaType getListMeta() {
        return ListMetaType.Island;
    }
}
