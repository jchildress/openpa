package com.powerdata.openpa.tools.psmfmt;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.BusRefIndex;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;
import com.powerdata.openpa.TransformerList;

import java.io.*;

public class RatioTapChangerOPA extends ExportOpenPA<TransformerList> {
    protected FmtInfo[] _lfi;

    public RatioTapChangerOPA(PAModel m, BusRefIndex bri) throws PAModelException {
        super(m.getTransformers(), RatioTapChanger.values().length);
        BusRefIndex.TwoTerm bx = bri.get2TBus(_list);
        assignTap(bx.getFromBus(), bri, 'f', _finfo);
//		_lfi = _finfo.clone();
        _lfi = new FmtInfo[RatioTapChanger.values().length];
        assignTap(bx.getToBus(), bri, 't', _lfi);
    }

    void assignTap(int[] tnode, BusRefIndex bri, char side, FmtInfo[] finfo) {
        assign(RatioTapChanger.ID,
                i -> String.format("\"%s:%ctap\"", _list.getID(i), side),
                finfo);
        assign(RatioTapChanger.TapNode,
                new StringWrap(i -> bri.getBuses().get(tnode[i]).getID()),
                finfo);
        assign(RatioTapChanger.TransformerWinding,
                new StringWrap(i -> _list.getID(i) + ":wnd1"),
                finfo);
    }

    @Override
    public void export(File outputdir) throws PAModelException, IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(new File(outputdir, getPsmFmtName() + ".csv"))));
        printHeader(pw);
        printData(pw, _lfi, getCount());
        printData(pw, _finfo, getCount());
        pw.close();
    }

    @Override
    protected String getPsmFmtName() {
        return PsmMdlFmtObject.RatioTapChanger.toString();
    }
}
