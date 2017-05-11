package com.powerdata.openpa.tools;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.BaseObject;
import com.powerdata.openpa.ListMetaType;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;

public class EqType {
    static ListMetaType[] _Ltypes = ListMetaType.values();

    public static BaseObject getObject(PAModel mdl, ListMetaType type, int ndx)
            throws PAModelException {
        return mdl.getList(type).get(ndx);
    }

    public static BaseObject getObject(PAModel mdl, long id) throws PAModelException {
        ListMetaType t = _Ltypes[(int) (id >> 32)];
        int ndx = (int) (id & 0xFFFFFFFF);
        return getObject(mdl, t, ndx);
    }

    public static ListMetaType GetType(BaseObject obj) {
        return obj.getList().getListMeta();
    }

    public static long GetID(BaseObject dev) {
        long type = GetType(dev).ordinal();
        long ndx = dev.getIndex();
        long id = (type << 32) | ndx;
        return id;
    }
}
