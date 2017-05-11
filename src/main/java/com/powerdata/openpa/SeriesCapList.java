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

public interface SeriesCapList extends ACBranchListIfc<SeriesCap> {
    static Set<ColumnMeta> Cols = EnumSet.copyOf(Arrays
            .asList(new ColumnMeta[]{ColumnMeta.SercapBUSFROM,
                    ColumnMeta.SercapBUSTO, ColumnMeta.SercapID,
                    ColumnMeta.SercapNAME, ColumnMeta.SercapINSVC,
                    ColumnMeta.SercapPFROM, ColumnMeta.SercapPTO,
                    ColumnMeta.SercapQFROM, ColumnMeta.SercapQTO,
                    ColumnMeta.SercapR, ColumnMeta.SercapRATLT,
                    ColumnMeta.SercapX}));

    static SeriesCapList emptyList() {
        return EmptyLists.EMPTY_SERIESCAPS;
    }

    @Override
    default Set<ColumnMeta> getColTypes() {
        return Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.SeriesCap;
    }
}
