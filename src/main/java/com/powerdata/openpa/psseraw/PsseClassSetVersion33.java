package com.powerdata.openpa.psseraw;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

/**
 * Attempt Version 33 using a raw file and no official docs yet.  Note that we have skipped
 * versions 31 and 32, and once those are known, this should be reviewed.
 *
 * @author chris@powerata.com
 */
public class PsseClassSetVersion33 extends PsseClassSetVersion30 {
    public static final PsseClass Bus33 = new PsseClass("Bus");
    public static final PsseClass FixedShunt33 = new PsseClass("FixedShunt");
    public static final PsseClass NontransformerBranch33 = new PsseClass("NontransformerBranch");
    private static final int VersionMajor = 33;
    private static final PsseClass[] ClassList = new PsseClass[]
            {
                    Bus33, Load, FixedShunt33, Generator, NontransformerBranch33, Transformer30, AreaInterchange,
                    TwoTerminalDCLine, VSC_DCLine, SwitchedShunt30,
                    TxImpedanceCorrection, MultiTermDCLine, MultiSectionLine,
                    Zone, InterAreaTransfer, Owner, FACTSDevice30
            };

    static {
        Bus33.addLine(new PsseField[]{
                new PsseField("I", PsseFieldType.Integer),
                new PsseField("NAME", PsseFieldType.String),
                new PsseField("BASKV", PsseFieldType.Float),
                new PsseField("IDE", PsseFieldType.Integer),
                new PsseField("AREA", PsseFieldType.Integer),
                new PsseField("ZONE", PsseFieldType.Integer),
                new PsseField("OWNER", PsseFieldType.Integer),
                new PsseField("VM", PsseFieldType.Float),
                new PsseField("VA", PsseFieldType.Float)});

        /**
         * TODO: The STAT field is just a guess, verify once docs become
         * available
         */
        FixedShunt33.addLine(new PsseField[]{
                new PsseField("I", PsseFieldType.Integer),
                new PsseField("ID", PsseFieldType.String),
                new PsseField("STAT", PsseFieldType.Integer),
                new PsseField("G", PsseFieldType.Float),
                new PsseField("B", PsseFieldType.Float)});

        /**
         * TODO: After ST it looks like there is a new integer field before LEN
         */
        NontransformerBranch33.addLine(new PsseField[]{
                new PsseField("I", PsseFieldType.String),
                new PsseField("J", PsseFieldType.String),
                new PsseField("CKT", PsseFieldType.String),
                new PsseField("R", PsseFieldType.Float),
                new PsseField("X", PsseFieldType.Float),
                new PsseField("B", PsseFieldType.Float),
                new PsseField("RATEA", PsseFieldType.Float),
                new PsseField("RATEB", PsseFieldType.Float),
                new PsseField("RATEC", PsseFieldType.Float),
                new PsseField("GI", PsseFieldType.Float),
                new PsseField("BI", PsseFieldType.Float),
                new PsseField("GJ", PsseFieldType.Float),
                new PsseField("BJ", PsseFieldType.Float),
                new PsseField("ST", PsseFieldType.Integer),
                new PsseField("UNKNOWN1", PsseFieldType.Integer),
                new PsseField("LEN", PsseFieldType.Float),
                new PsseField("O1", PsseFieldType.Integer),
                new PsseField("F1", PsseFieldType.Float),
                new PsseField("O2", PsseFieldType.Integer),
                new PsseField("F2", PsseFieldType.Float),
                new PsseField("O3", PsseFieldType.Integer),
                new PsseField("F3", PsseFieldType.Float),
                new PsseField("O4", PsseFieldType.Integer),
                new PsseField("F4", PsseFieldType.Float)});


    }

    @Override
    public int getVersionMajor() {
        return VersionMajor;
    }

    @Override
    public PsseClass[] getPsseClasses() {
        return ClassList.clone();
    }

    @Override
    public PsseClass getBus() {
        return Bus33;
    }

    @Override
    public PsseClass getNontransformerBranch() {
        return NontransformerBranch33;
    }

    @Override
    public PsseClass getFixedShunt() {
        return FixedShunt33;
    }


}
