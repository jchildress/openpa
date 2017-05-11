package com.powerdata.openpa.tools;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.*;

import java.util.EnumMap;

public class ACBranchByType
        extends
        EnumMap<ListMetaType, com.powerdata.openpa.tools.ACBranchByType.BranchListSupplier> {
    private static final long serialVersionUID = 1L;
    private static ACBranchByType _BranchByType = new ACBranchByType();

    public ACBranchByType() {
        super(ListMetaType.class);
        put(ListMetaType.Line, i -> i.getLines());
        put(ListMetaType.SeriesCap, i -> i.getSeriesCapacitors());
        put(ListMetaType.SeriesReac, i -> i.getSeriesReactors());
        put(ListMetaType.PhaseShifter, i -> i.getPhaseShifters());
        put(ListMetaType.Transformer, i -> i.getTransformers());
    }

    public static ACBranchListIfc<? extends ACBranch> get(ListMetaType type,
                                                          PALists lists) throws PAModelException {
        return _BranchByType.get(type).get(lists);
    }

    @FunctionalInterface
    interface BranchListSupplier {
        ACBranchListIfc<? extends ACBranch> get(PALists lists) throws PAModelException;
    }
}
