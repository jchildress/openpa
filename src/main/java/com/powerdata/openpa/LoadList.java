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

public interface LoadList extends OneTermDevListIfc<Load> {
    static Set<ColumnMeta> Cols = EnumSet
            .copyOf(Arrays.asList(new ColumnMeta[]{ColumnMeta.LoadBUS,
                    ColumnMeta.LoadID, ColumnMeta.LoadNAME, ColumnMeta.LoadINSVC,
                    ColumnMeta.LoadP, ColumnMeta.LoadPMAX, ColumnMeta.LoadQ,
                    ColumnMeta.LoadQMAX}));

    static LoadList emptyList() {
        return EmptyLists.EMPTY_LOADS;
    }

    float getMaxP(int ndx) throws PAModelException;

    void setMaxP(int ndx, float mw) throws PAModelException;

    float[] getMaxP() throws PAModelException;

    void setMaxP(float[] mw) throws PAModelException;

    float getMaxQ(int ndx) throws PAModelException;

    void setMaxQ(int ndx, float mvar) throws PAModelException;

    float[] getMaxQ() throws PAModelException;

    void setMaxQ(float[] mvar) throws PAModelException;

    @Override
    default Set<ColumnMeta> getColTypes() {
        return Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.Line;
    }
}
