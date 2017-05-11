package com.powerdata.openpa.psseraw;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.psseraw.PsseRepository.CaseFormat;
import com.powerdata.openpa.psseraw.PsseRepository.PsmFormat;
import gnu.trove.map.TObjectIntMap;

import java.io.IOException;

public class PsseAreaTool extends PsseEquipment {
    int _id, _name, _intsch;

    public PsseAreaTool(PsseClass pc, PsseRepository rep) throws IOException {
        super(rep);
        TObjectIntMap<String> fldMap = PsseEquipment.buildMap(pc.getLines());
        _id = fldMap.get("i");
        _name = fldMap.get("arname");
        _intsch = fldMap.get("pdes");
    }

    static public String mkId(String psseId) {
        return new StringBuilder(psseId).append("_ca").toString();
    }

    @Override
    public void writeRecord(PsseClass pclass, String[] record)
            throws PsseProcException {
        String id = mkId(record[_id]);
        _rep.findWriter(PsmFormat.ControlArea).format("\"%s\",\"%s\"\n", id, record[_name]);
        _rep.findWriter(CaseFormat.ControlArea).format("\"%s\",%s\n", id, record[_intsch]);
    }

}
