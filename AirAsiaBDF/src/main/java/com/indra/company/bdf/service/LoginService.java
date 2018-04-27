package com.indra.company.bdf.service;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.exception.ExecutionException;

public class LoginService extends CilantrumReusable{
	
	private String xpath="";

	public void login() throws ExecutionException {
		
		clickLoginSignup();
		clickLoginSignup();
		inputLoginDetails();
		clickLoginButton();
		selectFlight();

	}
	
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
	
	/**
	 * This method will click the login/signup button and will direct us to the login page.
	 * @throws ExecutionException
	 */
	public void clickLoginSignup() throws ExecutionException{
		wait(3);
		this.xpath = "login-sign-up search_link_by_span_class";
		
		wait(5);
		
		this.xpath = "login-sign-up search_link_by_span_class";
		
		hoverAndPushOn(this.xpath);
		logger.info(1, "Verifying if size selected is correct");
		
		this.xpath = "id_username_textfield search_component_by_id";
		if(exists(this.xpath,3)){
			reportValidation("login page is displayed.", true, true);				
		}else{
			reportError("login page is not displayed.",true,null,false);
		}	
	}
	
	/**
	 * This method will fill up all the login details
	 * @throws ExecutionException
	 */
	public void inputLoginDetails() throws ExecutionException{
		String userInput, passwordInput, userInputted, passwordInputted;
		userInput = Global.getExcelData().get("USERNAME");
		passwordInput = Global.getExcelData().get("PASSWORD");
		
		logger.info(1, "Writing values to the login details...");
		this.xpath = "username-input--login search_component_by_id";
		writeInto(this.xpath, userInput);
		wait(3);
		userInputted = getProperty(this.xpath, "value");
		
		this.xpath = "password-input--login search_component_by_id";
		writeInto(this.xpath, passwordInput);
		wait(3);
		passwordInputted = getProperty(this.xpath, "value");
		
		if(userInput.equals(userInputted) && passwordInput.equals(passwordInputted)){
			reportValidation("Values are inputted correctly.", true, true);				
		}else{
			reportError("Values are not inputted correctly.",true,null,false);
		}
		
	}
	
	/**
	 * This method will click the submit button and will direct us back to the homepage
	 * @throws ExecutionException
	 */
	public void clickLoginButton() throws ExecutionException{
		
		this.xpath = "loginbutton search_component_by_id";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking login button...");
		wait(10);	
		
		this.xpath = "bookingSelection search_component_by_id";
		if(exists(this.xpath,90)){
			reportValidation("BDF homepage is displayed.", true, true);				
		}else{
			reportError("BDF homepage is not displayed.",true,null,false);
		}	
		
	}
	
	/**
	 * This method will select the chosen flight name on the flight option field
	 * @throws ExecutionException
	 */
	public void selectFlight() throws ExecutionException{
			
		String value = null;
		waitTo("bookingSelection search_component_by_id", 20);
		value = Global.getExcelData().get("FLIGHT_NAME").trim();
		this.xpath = "bookingSelection search_component_by_id";
		
		logger.info(1, "Select flight from the dropdown at the top part of the screen.");
		selectOptions(this.xpath,value);

		if(exists(this.xpath, 3)){
			reportValidation("Flight is selected.", true, true);
			
		}else{
			reportError("Flight is not selected.",true,null,false);
		

		}

	}
	
}
