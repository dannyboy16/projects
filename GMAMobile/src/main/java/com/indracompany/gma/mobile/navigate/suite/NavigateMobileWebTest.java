package com.indracompany.gma.mobile.navigate.suite;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumTest;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.exception.ExecutionException;

public class NavigateMobileWebTest extends CilantrumTest{

	public void execute() throws ExecutionException {
		Reusables.execute("NavigateMobileWebReusable");
		
	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		//deviceOperation("cleardata", "");
		navigateTo(Global.getGlobalsDevice().URL);
		
	}

}
