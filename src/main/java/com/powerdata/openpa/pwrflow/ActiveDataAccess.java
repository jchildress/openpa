package com.powerdata.openpa.pwrflow;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.PAModelException;

/**
 * Provide appropriate data access for 1-terminal device data during
 * mismatch calculations
 */
@FunctionalInterface
interface ActiveDataAccess {
    float[] get() throws PAModelException;
}