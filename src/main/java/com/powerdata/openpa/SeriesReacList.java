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

public interface SeriesReacList extends ACBranchListIfc<SeriesReac> {
    static Set<ColumnMeta> Cols = EnumSet.copyOf(Arrays
            .asList(new ColumnMeta[]{ColumnMeta.SerreacBUSFROM,
                    ColumnMeta.SerreacBUSTO, ColumnMeta.SerreacID,
                    ColumnMeta.SerreacNAME, ColumnMeta.SerreacINSVC,
                    ColumnMeta.SerreacPFROM, ColumnMeta.SerreacPTO,
                    ColumnMeta.SerreacQFROM, ColumnMeta.SerreacQTO,
                    ColumnMeta.SerreacR, ColumnMeta.SerreacRATLT,
                    ColumnMeta.SerreacX}));

    static SeriesReacList emptyList() {
        return EmptyLists.EMPTY_SERIESREACS;
    }

    @Override
    default Set<ColumnMeta> getColTypes() {
        return Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.SeriesReac;
    }
}
