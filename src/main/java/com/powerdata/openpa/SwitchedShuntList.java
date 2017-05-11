package com.powerdata.openpa;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.impl.EmptyLists;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public interface SwitchedShuntList extends OneTermDevListIfc<SwitchedShunt> {
    static Set<ColumnMeta> Cols = EnumSet.copyOf(Arrays
            .asList(new ColumnMeta[]{ColumnMeta.SwshB, ColumnMeta.SwshID,
                    ColumnMeta.SwshNAME, ColumnMeta.SwshOOS, ColumnMeta.SwshP,
                    ColumnMeta.SwshQ}));

    static SwitchedShuntList emptyList() {
        return EmptyLists.EMPTY_SWITCHEDSHUNTS;
    }

    @Override
    default Set<ColumnMeta> getColTypes() {
        return Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.SwitchedShunt;
    }

}
