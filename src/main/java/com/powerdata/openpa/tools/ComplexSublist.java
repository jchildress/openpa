package com.powerdata.openpa.tools;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import java.util.AbstractList;
import java.util.List;

public class ComplexSublist extends AbstractList<Complex>
{
	int[] _ndx;
	List<Complex> _src;
	
	public ComplexSublist(List<Complex> src, int[] ndx)
	{
		_src = src;
		_ndx = ndx;
	}

	@Override
	public Complex get(int index)
	{
		return _src.get(_ndx[index]);
	}

	@Override
	public int size()
	{
		return _ndx.length;
	}
}
