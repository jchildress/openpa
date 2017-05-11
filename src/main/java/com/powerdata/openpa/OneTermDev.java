package com.powerdata.openpa;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.OneTermBaseList.OneTermBase;


public class OneTermDev extends InService implements OneTermBase

{
	OneTermDevListIfc<? extends OneTermDev>	_list;

	public OneTermDev(OneTermDevListIfc<? extends OneTermDev> list, int ndx)
	{
		super(list, ndx);
		_list = list;
	}

	/** Get device active power in MW */
	public float getP() throws PAModelException
	{
		return _list.getP(_ndx);
	}
	
	/** Get device reactive power in MVAr */
	public float getQ() throws PAModelException
	{
		return _list.getQ(_ndx);
	}
	
	/** Set device active power in MW */
	public void setP(float p) throws PAModelException
	{
		_list.setP(_ndx, p);
	}
	
	/** Set device reactive power in MVAr */
	public void setQ(float q) throws PAModelException
	{
		_list.setQ(_ndx, q);
	}

	@Override
	public Bus getBus() throws PAModelException
	{
		return _list.getBus(_ndx);
	}
	
}
