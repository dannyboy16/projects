package com.indra.company.bdf.service;

import indra.cilantrum.cilantrum.CilantrumReusable;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.TestSuites;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;

public class FeedbackService extends CilantrumReusable{

	public void execute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadParameters() {
		// TODO Auto-generated method stub
		
	}
	
	public void feedBackWithoutSubmitting() throws ExecutionException{
		verify("feedback button");
		hoverAndPushOn("feedback button");
		verify("feedback div");
		
		String input = Global.getExcelData().get("FEEDBACK_MESSAGE").trim();;
		String inputted = "";
		writeInto("feedback textarea", input);
		wait(3);

		inputted = getProperty("feedback textarea", "value");
		if(input.equals(inputted)){
			reportValidation("Entered data is displayed", true, true);
			
		}else{
			reportError("Entered data is not displayed",true,null,false);
		
		}
		
		if(AirAsiaBDFWebMain.feedbackEmail){
			String email = Global.getExcelData().get("FEEDBACK_EMAIL").trim();
			clearField("feedback_email textarea");
			writeInto("feedback_email textarea",email);
			wait(3);
		}else{
			if(AirAsiaBDFWebMain.testSuiteType==TestSuites.FeedbackCustomerServiceSection.toString())
			clearField("feedback_email textarea");
		}
		
		hoverAndPushOn("feedback closediv");
		if(!exists("feedback div",5)){
			reportValidation("The modal window is closed.", true, true);				
		}else{
			reportError("The modal window is not closed.",true,null,false);
		}
	}
	
	public void feedbackWithSubmitting() throws ExecutionException{
		verify("feedback button");
		hoverAndPushOn("feedback button");
		verify("feedback div");
		
		String input = Global.getExcelData().get("FEEDBACK_MESSAGE").trim();
		String inputted = "";	

		writeInto("feedback textarea", input);
		inputted = getProperty("feedback textarea", "value");
		if(input.equals(inputted)){
			reportValidation("Entered data is displayed", true, true);
			
		}else{
			reportError("Entered data is not displayed",true,null,false);
		
		}
		
		if(AirAsiaBDFWebMain.feedbackEmail){
			String email = Global.getExcelData().get("FEEDBACK_EMAIL").trim();
			clearField("feedback_email textarea");
			writeInto("feedback_email textarea",email);
			wait(3);
		}else{
			clearField("feedback_email textarea");
		}
		
		hoverAndPushOn("feedback_send button");
		verify("feedback_success message");
		
		hoverAndPushOn("feedback_success closediv");
		if(!exists("feedback_success message",5)){
			reportValidation("The modal window is closed.", true, true);				
		}else{
			reportError("The modal window is not closed.",true,null,false);
		}
	}

}
