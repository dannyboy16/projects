package com.indracompany.bdf.feedback.customerservicesection.web.suite;

import indra.cilantrum.cilantrum.CilantrumTest;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.exception.ExecutionException;
import indra.cilantrum.cilantrum.Cilantrum.Global;

public class FeedbackCustomerServiceSectionWebLoginTest extends CilantrumTest{

	public void preExecute() throws ExecutionException {
		deviceOperation("cleardata", "");
		navigateTo(Global.getGlobalsDevice().URL);
	}

	public void execute() throws ExecutionException {
		Reusables.execute("FeedbackCustomerServiceSectionTransactionReusable", "true");

	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

}
