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


public interface AreaList extends GroupListIfc<Area> {
    static Set<ColumnMeta> Cols = EnumSet.copyOf(Arrays.asList(new ColumnMeta[]
            {
                    ColumnMeta.AreaID,
                    ColumnMeta.AreaNAME
            }));

    static AreaList emptyList() {
        return EmptyLists.EMPTY_AREAS;
    }

    @Override
    default Set<ColumnMeta> getColTypes() {
        return Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.Area;
    }
}
