package com.indracompany.bdf.pwp.web.suite;

import indra.cilantrum.cilantrum.CilantrumTest;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;

public class PwPWebLoginTest extends CilantrumTest{

	public void preExecute() throws ExecutionException{
		deviceOperation("cleardata", "");
		navigateTo(Global.getGlobalsDevice().URL);
		
	}
	
	public void execute() throws ExecutionException {
		Reusables.execute("PwPTransactionReusable", "true");
		
	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

}
