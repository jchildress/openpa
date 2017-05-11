package com.powerdata.openpa.tools;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.BaseObject;
import com.powerdata.openpa.ListMetaType;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;

import java.util.EnumMap;
import java.util.Map;

public class PrettyName {
    static Map<ListMetaType, StationAccess> _StationAccess = new EnumMap<>(ListMetaType.class);
    static StationAccess _Nothing = (m, b) -> "";

    static {
        _StationAccess.put(ListMetaType.Bus,
                (m, b) -> m.getBuses().get(b.getIndex()).getStation().getName());
        _StationAccess.put(ListMetaType.Transformer,
                (m, b) -> m.getTransformers().get(b.getIndex()).getFromBus().getStation().getName());
        _StationAccess.put(ListMetaType.SeriesCap,
                (m, b) -> m.getSeriesCapacitors().get(b.getIndex()).getFromBus().getStation().getName());
        _StationAccess.put(ListMetaType.SeriesReac,
                (m, b) -> m.getSeriesReactors().get(b.getIndex()).getFromBus().getStation().getName());
        _StationAccess.put(ListMetaType.PhaseShifter,
                (m, b) -> m.getPhaseShifters().get(b.getIndex()).getFromBus().getStation().getName());
    }

    static String getStation(PAModel m, BaseObject b) throws PAModelException {
        StationAccess sa = _StationAccess.get(b.getList().getListMeta());
        return (sa == null) ? "" : sa.getStationName(m, b) + " ";
    }

    public static String getObjectName(PAModel m, BaseObject b) throws PAModelException {
        return getStation(m, b) + b.getName();
    }

    @FunctionalInterface
    interface StationAccess {
        String getStationName(PAModel m, BaseObject b) throws PAModelException;
    }
}
