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

public interface ShuntCapList extends FixedShuntListIfc<ShuntCapacitor> {
    static Set<ColumnMeta> _Cols = EnumSet
            .copyOf(Arrays.asList(new ColumnMeta[]{ColumnMeta.ShcapB,
                    ColumnMeta.ShcapBUS, ColumnMeta.ShcapID,
                    ColumnMeta.ShcapNAME, ColumnMeta.ShcapINSVC,
                    ColumnMeta.ShcapP, ColumnMeta.ShcapQ}));

    static ShuntCapList emptyList() {
        return EmptyLists.EMPTY_SHUNTCAPS;
    }

    @Override
    default Set<ColumnMeta> getColTypes() {
        return _Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.ShuntCap;
    }
}
