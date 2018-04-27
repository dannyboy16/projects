package com.indracompany.bdf.feedback.customerservicesection.web.suite;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.AccessTypes;

import indra.cilantrum.cilantrum.CilantrumSuite;
import indra.cilantrum.exception.ExecutionException;

public class FeedbackCustomerServiceSectionWebSuite extends CilantrumSuite{

	public void loadTests() 
	{
		if(AirAsiaBDFWebMain.accessType == AccessTypes.Login.toString()){
			loadTest("FeedbackCustomerServiceSectionWebLoginTest");
		}else{
			loadTest("FeedbackCustomerServiceSectionWebAnonymousTest");
		}	
		
		//loadTest("FeedbackCustomerServiceSectionWebAnonymousTest");
	}
	
	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

}
