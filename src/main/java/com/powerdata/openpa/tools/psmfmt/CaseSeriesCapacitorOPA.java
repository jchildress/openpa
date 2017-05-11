package com.powerdata.openpa.tools.psmfmt;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;
import com.powerdata.openpa.SeriesCapList;

public class CaseSeriesCapacitorOPA extends ExportOpenPA<SeriesCapList>
{

	public CaseSeriesCapacitorOPA(PAModel m) throws PAModelException
	{
		super(m.getSeriesCapacitors(), CaseSeriesCapacitor.values().length);
		assign(CaseSeriesCapacitor.ID, new StringWrap(i -> _list.getID(i)));
		assign(CaseSeriesCapacitor.FromMW, i -> String.valueOf(_list.getFromP(i)));
		assign(CaseSeriesCapacitor.FromMVAr, i -> String.valueOf(_list.getFromQ(i)));
		assign(CaseSeriesCapacitor.ToMW, i -> String.valueOf(_list.getToP(i)));
		assign(CaseSeriesCapacitor.ToMVAr, i -> String.valueOf(_list.getToQ(i)));
		assign(CaseSeriesCapacitor.InService, i -> String.valueOf(_list.isInService(i)));
	}

	@Override
	protected String getPsmFmtName()
	{
		return "PsmCase" + PsmCaseFmtObject.SeriesCapacitor.toString();
	}
}
