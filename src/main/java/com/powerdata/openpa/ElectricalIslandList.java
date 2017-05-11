package com.powerdata.openpa;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import com.powerdata.openpa.impl.EmptyLists;

public interface ElectricalIslandList extends GroupListIfc<ElectricalIsland>
{
	static ElectricalIslandList emptyList() {return EmptyLists.EMPTY_ISLANDS;}
	
	boolean isEnergized(int ndx) throws PAModelException;
	boolean[] isEnergized() throws PAModelException;
	float getFreq(int ndx) throws PAModelException;
	void setFreq(int ndx, float f) throws PAModelException;
	float[] getFreq() throws PAModelException;
	void setFreq(float[] f) throws PAModelException;
	static Set<ColumnMeta> Cols = EnumSet.copyOf(Arrays
			.asList(new ColumnMeta[] { ColumnMeta.IslandEGZSTATE,
					ColumnMeta.IslandFREQ, ColumnMeta.IslandID,
					ColumnMeta.IslandNAME }));
	@Override
	default Set<ColumnMeta> getColTypes()
	{
		return Cols;
	}
	@Override
	default ListMetaType getListMeta(){return ListMetaType.Island;}
}
