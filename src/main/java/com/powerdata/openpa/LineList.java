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

public interface LineList extends ACBranchListIfc<Line> {
    static Set<ColumnMeta> _Cols = EnumSet.copyOf(Arrays
            .asList(new ColumnMeta[]{ColumnMeta.LineBFROM,
                    ColumnMeta.LineBTO, ColumnMeta.LineBUSFROM,
                    ColumnMeta.LineBUSTO, ColumnMeta.LineID,
                    ColumnMeta.LineNAME, ColumnMeta.LineINSVC,
                    ColumnMeta.LinePFROM, ColumnMeta.LinePTO,
                    ColumnMeta.LineQFROM, ColumnMeta.LineQTO, ColumnMeta.LineR,
                    ColumnMeta.LineRATLT, ColumnMeta.LineX}));

    static LineList emptyList() {
        return EmptyLists.EMPTY_LINES;
    }

    float getFromBchg(int ndx) throws PAModelException;

    void setFromBchg(int ndx, float b) throws PAModelException;

    float[] getFromBchg() throws PAModelException;

    void setFromBchg(float[] b) throws PAModelException;

    float getToBchg(int ndx) throws PAModelException;

    void setToBchg(int ndx, float b) throws PAModelException;

    float[] getToBchg() throws PAModelException;

    void setToBchg(float[] b) throws PAModelException;

    @Override
    default Set<ColumnMeta> getColTypes() {
        return _Cols;
    }

    @Override
    default ListMetaType getListMeta() {
        return ListMetaType.Line;
    }
}
