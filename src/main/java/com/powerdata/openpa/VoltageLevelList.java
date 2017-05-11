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

public interface VoltageLevelList extends GroupListIfc<VoltageLevel> {
    static Set<ColumnMeta> _Cols = EnumSet.copyOf(Arrays
            .asList(new ColumnMeta[]{ColumnMeta.VlevBASKV, ColumnMeta.VlevID,
                    ColumnMeta.VlevNAME}));

    static VoltageLevelList emptyList() {
        return EmptyLists.EMPTY_VOLTAGELEVELS;
    }

    float getBaseKV(int ndx) throws PAModelException;

    void setBaseKV(int ndx, float k) throws PAModelException;

    float[] getBaseKV() throws PAModelException;

    void setBaseKV(float[] kv) throws PAModelException;

    @Override
    default Set<ColumnMeta> getColTypes() {
        return _Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.VoltageLevel;
    }
}
