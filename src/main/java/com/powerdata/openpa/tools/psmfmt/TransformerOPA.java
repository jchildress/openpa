package com.powerdata.openpa.tools.psmfmt;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.*;

import java.io.*;

public class TransformerOPA extends ExportOpenPA<ACBranchListIfc<? extends ACBranch>> {
    FmtInfo[] _txi;
    int _ntx, _nps;

    public TransformerOPA(PAModel m) throws PAModelException {
        super(null, Transformer.values().length);
        TransformerList tlist = m.getTransformers();
        PhaseShifterList plist = m.getPhaseShifters();
        _ntx = tlist.size();
        _nps = plist.size();
        assign(tlist);
        _txi = _finfo.clone();
        assign(plist);
    }

    void assign(ACBranchListIfc<? extends ACBranch> list) {
        assign(Transformer.ID, new StringWrap(i -> list.getID(i)));
        assign(Transformer.Name, new StringWrap(i -> list.getName(i)));
        assign(Transformer.WindingCount, i -> "2");
    }

    @Override
    protected String getPsmFmtName() {
        return PsmMdlFmtObject.Transformer.toString();
    }

    @Override
    public void export(File outputdir) throws PAModelException, IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(new File(outputdir, getPsmFmtName() + ".csv"))));
        printHeader(pw);
        printData(pw, _txi, _ntx);
        printData(pw, _finfo, _nps);
        pw.close();
    }


}
