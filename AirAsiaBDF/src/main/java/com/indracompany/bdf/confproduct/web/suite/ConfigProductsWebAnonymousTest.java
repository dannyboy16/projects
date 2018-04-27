package com.indracompany.bdf.confproduct.web.suite;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumTest;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.exception.ExecutionException;

public class ConfigProductsWebAnonymousTest extends CilantrumTest{
	
	public void preExecute() throws ExecutionException{
		deviceOperation("cleardata", "");
		navigateTo(Global.getGlobalsDevice().URL);
		//pushOn("title_mobilesite imgtitle");
	}

	public void execute() throws ExecutionException{
		
		Reusables.execute("ConfigProductsTransaction","false");
	}

	public void postExecute() throws ExecutionException{
	}
}
