package com.powerdata.openpa.tools.psmfmt;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.*;

public class SeriesReactorOPA extends ExportOpenPA<SeriesReacList> {
    public SeriesReactorOPA(PAModel m, BusRefIndex bri) throws PAModelException {
        super(m.getSeriesReactors(), SeriesReactor.values().length);
        BusRefIndex.TwoTerm bx = bri.get2TBus(_list);
        BusList buses = bri.getBuses();
        assign(SeriesCapacitor.ID, new StringWrap(i -> _list.getID(i)));
        assign(SeriesCapacitor.Name, new StringWrap(i -> _list.getName(i)));
        assign(SeriesCapacitor.Node1, new StringWrap(i -> buses.get(bx.getFromBus()[i])
                .getID()));
        assign(SeriesCapacitor.Node2, new StringWrap(i -> buses.get(bx.getToBus()[i])
                .getID()));
        assign(SeriesCapacitor.R, i -> String.valueOf(_list.getR(i)));
        assign(SeriesCapacitor.X, i -> String.valueOf(_list.getX(i)));
        assign(SeriesCapacitor.NormalOperatingLimit,
                i -> String.valueOf(_list.getLTRating(i)));
    }


    @Override
    protected String getPsmFmtName() {
        return PsmMdlFmtObject.SeriesReactor.toString();
    }
}
