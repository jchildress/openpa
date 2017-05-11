package com.powerdata.openpa.tools.psmfmt;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import com.powerdata.openpa.LoadList;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;

public class CaseLoadOPA extends ExportOpenPA<LoadList>
{
	public CaseLoadOPA(PAModel m) throws PAModelException
	{
		super(m.getLoads(), CaseLoad.values().length);
		assign(CaseLoad.ID, new StringWrap(i -> _list.getID(i)));
		assign(CaseLoad.MW, i -> String.valueOf(-_list.getP(i)));
		assign(CaseLoad.MVAr, i -> String.valueOf(-_list.getQ(i)));
		assign(CaseLoad.InService, i -> String.valueOf(_list.isInService(i)));
	}
	
	@Override
	protected String getPsmFmtName()
	{
		return "PsmCase" + PsmCaseFmtObject.Load.toString();
	}
}
