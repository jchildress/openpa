package com.powerdata.openpa.impl;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.ColumnMeta;
import com.powerdata.openpa.PAModelException;
import com.powerdata.openpa.ShuntCapList;
import com.powerdata.openpa.ShuntCapacitor;

public class ShuntCapListI extends FixedShuntListI<ShuntCapacitor> implements ShuntCapList
{
	static final ShuntEnum _PFld = new ShuntEnum()
	{
		@Override
		public ColumnMeta id() {return ColumnMeta.ShcapID;}
		@Override
		public ColumnMeta name() {return ColumnMeta.ShcapNAME;}
		@Override
		public ColumnMeta bus() {return ColumnMeta.ShcapBUS;}
		@Override
		public ColumnMeta p() {return ColumnMeta.ShcapP;}
		@Override
		public ColumnMeta q() {return ColumnMeta.ShcapQ;}
		@Override
		public ColumnMeta insvc() {return ColumnMeta.ShcapINSVC;}
		@Override
		public ColumnMeta b() {return ColumnMeta.ShcapB;}
	};
	
	public ShuntCapListI(PAModelI model, int[] keys) throws PAModelException
	{
		super(model, keys, _PFld);
	}
	public ShuntCapListI(PAModelI model, int size) throws PAModelException
	{
		super(model, size, _PFld);
	}
	public ShuntCapListI() {super();}

	@Override
	public ShuntCapacitor get(int index)
	{
		return new ShuntCapacitor(this, index);
	}
}
