package com.powerdata.openpa.impl;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.ListMetaType;
import com.powerdata.openpa.Station;
import com.powerdata.openpa.StationList;

public class StationSubList extends GroupSubList<Station> implements
		StationList
{

	public StationSubList(StationList src, int[] ndx)
	{
		super(src, ndx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Station get(int index)
	{
		return new Station(this, index);
	}

	@Override
	public ListMetaType getListMeta()
	{
		return ListMetaType.Station;
	}

}
