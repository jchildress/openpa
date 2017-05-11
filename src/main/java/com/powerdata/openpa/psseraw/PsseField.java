package com.powerdata.openpa.psseraw;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

/**
 * Track information for a single psse field
 *
 * @author chris@powerdata.com
 */
public class PsseField {
    private String _name;
    private PsseFieldType _type;

    public PsseField(String name, PsseFieldType type) {
        _name = name;
        _type = type;
    }

    public String getName() {
        return _name;
    }

    public PsseFieldType getType() {
        return _type;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", getName(), getType());
    }


}
