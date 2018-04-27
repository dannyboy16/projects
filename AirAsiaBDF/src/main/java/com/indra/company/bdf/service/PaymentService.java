package com.indra.company.bdf.service;

import com.indracompany.bdf.web.model.LanguageOptions;
import com.indracompany.bdf.web.model.TestSuites;
import com.indracompany.bdf.web.model.PaymentTypes;

import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;
import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;

public class PaymentService extends CilantrumReusable{
	
	private String orderNumber;

	@Override	
	protected void loadParameters() {
		// TODO Auto-generated method stub
		
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
	
	public void paymentMethod(String paymentType, String language) throws ExecutionException {
		logger.info(1, "Input personal details in the form. ");
		inputPersonalDetails(language);
		
		logger.info(1, "Input credit card details. ");
		if(paymentType==PaymentTypes.CreditCard.toString()) {
			inputCreditCardDetails(language);
			creditCardPayment(language);

		}else if(paymentType==PaymentTypes.Alipay.toString()){
			alipayPayment(language);

		}else if(paymentType==PaymentTypes.UnionPay.toString()){
			unionpayPayment(language);

		}else if(paymentType==PaymentTypes.FPX.toString()){
			fpxPayment(language);

		}

		wait(5);
		orderVerification(language);
	}

	public void creditCardPayment(String language) throws ExecutionException {
		wait(8);
		hoverAndPushOn("card_agreement_terms checkbox");
		wait(8);
		
		hoverAndPushOn("card_continue_pay button");
		wait(20);	
		verifyProductAfterPaying(language); 
		
	}
	
	/**
	 * use for credit or debit card payment
	 */
	
	public void changeCurrency(String language,String paymentType) throws ExecutionException {
		wait(3);
		hoverAndPushOn("currency_arrow icon");
		
		wait(3);
		
		String currencyXpath;
		
		if(language==LanguageOptions.English.toString()){
			currencyXpath = "text_currency select_currency";
		}else{
			currencyXpath = "cn_text_currency select_currency";
		}
		hoverAndPushOn(currencyXpath);
	}
	
	public void alipayPayment(String language) throws ExecutionException {
//		wait(3);
//		hoverAndPushOn("currency_arrow icon");
//		
//		wait(3);
//		
//		String currencyXpath;
//		
//		if(language==LanguageOptions.English.toString()){
//			currencyXpath = "text_currency select_currency";
//		}else{
//			currencyXpath = "cn_text_currency select_currency";
//		}
//		hoverAndPushOn(currencyXpath);
		
		wait(5);
		hoverAndPushOn("internet_banking div");
		
		wait(3);
		hoverAndPushOn("alipay_icon radiobutton");
		
		wait(5);
		hoverAndPushOn("alipay_agreement_terms checkbox");
		
		wait(5);
		
		String contPayBtnXpath;
		if(language==LanguageOptions.English.toString()){
			contPayBtnXpath = "text_cont_pay_button search_button_by_text";
		}else{
			contPayBtnXpath = "cn_text_cont_pay_button search_button_by_text";
		}
		
		hoverAndPushOn(contPayBtnXpath);
		
		if(exists("alipay_confirmation_approve button",100))
		{
			hoverAndPushOn("alipay_confirmation_approve button");
			verifyProductAfterPaying(language); 
			
			wait(10);
		}
	}
	
	public void fpxPayment(String language) throws ExecutionException {
//		wait(3);
//		hoverAndPushOn("currency_arrow icon");
//		
//		wait(3);
//		
//		String currencyXpath;
//		
//		if(language==LanguageOptions.English.toString()){
//			currencyXpath = "text_currency select_currency";
//		}else{
//			currencyXpath = "cn_text_currency select_currency";
//		}
//		hoverAndPushOn(currencyXpath);
		
		wait(3);
		hoverAndPushOn("internet_banking div");
		
		wait(3);
		hoverAndPushOn("union_and_fpx_icon radiobutton");
		
		wait(5);
		hoverAndPushOn("fpx_agreement_terms checkbox");
		
		wait(5);
		
		String contPayBtnXpath;
		if(language==LanguageOptions.English.toString()){
			contPayBtnXpath = "text_cont_pay_button search_button_by_text";
		}else{
			contPayBtnXpath = "cn_text_cont_pay_button search_button_by_text";
		}
		
		hoverAndPushOn(contPayBtnXpath);
		
		waitTo("fpx_confirmation_approve button", 100);
		hoverAndPushOn("fpx_confirmation_approve button");
		verifyProductAfterPaying(language); 
		
	}
	
	public void unionpayPayment(String language) throws ExecutionException {
//	
		wait(3);
		hoverAndPushOn("unionpay_banking div");
		
		wait(3);
		hoverAndPushOn("union_and_fpx_icon radiobutton");
		
		wait(5);
		hoverAndPushOn("unionpay_agreement_terms checkbox");
		
		wait(5);
		
		String contPayBtnXpath;
		if(language==LanguageOptions.English.toString()){
			contPayBtnXpath = "text_cont_pay_button search_button_by_text";
		}else{
			contPayBtnXpath = "cn_text_cont_pay_button search_button_by_text";
		}
		
		hoverAndPushOn(contPayBtnXpath);
		
		waitTo("unionpay_confirmation_approve button", 100);
		hoverAndPushOn("unionpay_confirmation_approve button");
		verifyProductAfterPaying(language); 
		
	}
	
//	public void verifyProductAfterPaying(String language) throws ExecutionException 
//	{
//		wait(15);
//		verify("orderno div");
//		verify("purchasedate div");
//		orderNumber = getProperty("orderno div", "text").substring(7);
//		verify("class_product_name_summ_p search_p_by_class");
//		verify("class_brand_name_summ_p search_p_by_class");
//		verify("class_purchased_date_p search_p_by_class");
//		verify("class_qty_p search_p_by_class");
//		verify("orderProductPrice div");
//		verify("class_email_confirm search_p_by_class");
//		verify("class_suggestions search_div_by_class");
//	}
//	
	
	public void verifyProductAfterPaying(String language) throws ExecutionException 
	{
		waitTo("orderno div",100);
		verify("orderno div");
		verify("purchasedate div");
		if(language==LanguageOptions.English.toString()){
			orderNumber = getProperty("orderno div", "text").substring(7);
		}else{
			orderNumber = getProperty("orderno div", "text").substring(3);
		}
	
		//logger.info(1, "YESSSSSS : " + orderNumber);
		verify("class_product_name_summ_p search_p_by_class");
		verify("class_brand_name_summ_p search_p_by_class");
		verify("class_purchased_date_p search_p_by_class");
		verify("class_qty_p search_p_by_class");
		verify("orderProductPrice div");
		verify("class_email_confirm search_p_by_class");
		verify("class_suggestions search_div_by_class");
	}
	
	/**
	 * This method inputs credit card details before payment.
	 * @throws ExecutionException
	 */
	public void inputCreditCardDetails(String language) throws ExecutionException 
	{
		String creditCardBtnXpath;
		
		if(language==LanguageOptions.English.toString()){
			creditCardBtnXpath = "text_credit_card_li search_li_by_text";
		}else{
			creditCardBtnXpath = "cn_text_credit_card_li search_li_by_text";
		}
		hoverAndPushOn(creditCardBtnXpath);
		wait(2);
		String creditCard = Global.getExcelData().get("CREDIT_CARD").trim();
		
		String creditCardTFXpath;
		if(language==LanguageOptions.English.toString()){
			creditCardTFXpath = "text_input_credit_card_placeholder search_input_by_placeholder";
		}else{
			creditCardTFXpath = "cn_text_input_credit_card_placeholder search_input_by_placeholder";
		}
		clearField(creditCardTFXpath);
		writeInto(creditCardTFXpath, creditCard);
		
		wait(2);
		selectOptions("ccmonth div", Global.getExcelData().get("CC_EXPIRE_MM").trim());
		wait(2);
		selectOptions("ccyear div", Global.getExcelData().get("CC_EXPIRE_YEAR").trim());
		wait(2);
		
		String nameOfCardTFXpath;
		if(language==LanguageOptions.English.toString()){
			nameOfCardTFXpath = "text_input_name_card_placeholder search_input_by_placeholder";
		}else{
			nameOfCardTFXpath = "cn_text_input_name_card_placeholder search_input_by_placeholder";
		}
		
		clearField(nameOfCardTFXpath);
		writeInto(nameOfCardTFXpath, Global.getExcelData().get("GIVEN_NAME").trim());
		wait(2);
		clearField("text_input_cvv_placeholder search_input_by_placeholder");
		writeInto("text_input_cvv_placeholder search_input_by_placeholder", Global.getExcelData().get("CC_CVV").trim());
		
		//hoverAndPushOn("text_cont_pay_button search_button_by_text");
	}
	
//	public void orderVerification() throws ExecutionException{
//		wait(5);
//		navigateTo("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
//		wait(10);
//		
//	
//		String username = Global.getExcelData().get("EMAIL").trim();
//		clearField("email_username field");
//		writeInto("email_username field", username);
//		
//		hoverAndPushOn("email_username_next button");
//		wait(2);
//		
//		String password = Global.getExcelData().get("EMAIL_PASSWORD").trim();
//		clearField("email_pw field");
//		writeInto("email_pw field", password);
//		
//		hoverAndPushOn("email_pw_next button");
//		wait(5);
//		
//	//	hoverAndPushOn("email_message link");
//	//	wait(5);
//		
//		
//		String latestEmailMessege = getProperty("email_message link", "text");
//		
//		wait(5);
//		if(latestEmailMessege.contains(orderNumber))
//		{
////			navigateTo("https://mail.google.com/mail/#inbox");
////			wait(5);
//			logger.info(1, "SUCCESS");
//			hoverAndPushOn("email_message link");
//			wait(5);
//	
//			verify("email_success_verification div");
//		}
//		else
//		{
//			logger.info(1, "FAILED");
//		}
//	}
	
	public void orderVerification(String language) throws ExecutionException{
		wait(5);
		navigateTo("https://login.yahoo.com/?.src=ym&.intl=us&.partner=none&authMechanism=primary&yid=&done=https%3A%2F%2Fmail.yahoo.com%2F%3F.intl%3Den%26amp%253B.lang%3Den-PH%26amp%253B.partner%3Dnone%26amp%253B.src%3Dfp&eid=100&add=1");
		wait(10);
		
	
		String username = Global.getExcelData().get("EMAIL").trim();
		clearField("email_username field");
		writeInto("email_username field", username);
		
		hoverAndPushOn("email_username_next button");
		wait(2);
		
		String password = Global.getExcelData().get("EMAIL_PASSWORD").trim();
		clearField("email_pw field");
		writeInto("email_pw field", password);
		
		hoverAndPushOn("email_pw_next button");
		wait(5);
		
	//	hoverAndPushOn("email_message link");
	//	wait(5);
		
		
		String latestEmailMessege = getProperty(orderNumber + " email_message", "text");
		
		wait(5);
		if(latestEmailMessege.contains(orderNumber))
		{
//			navigateTo("https://mail.google.com/mail/#inbox");
//			wait(5);
			logger.info(1, "SUCCESS");
			hoverAndPushOn(orderNumber + " email_message");
			wait(5);
	
			verify("email_success_verification div");
		}
		else
		{
			logger.info(1, "FAILED");
		}
		
		wait(3);
		
		String printReceiptXpath;
		if(language==LanguageOptions.English.toString()){
			printReceiptXpath = "text_print_receipt search_a_by_text";
		}else{
			printReceiptXpath = "cn_text_print_receipt search_a_by_text";
		}
		
		hoverAndPushOn(printReceiptXpath);
		wait(10);
	}
	
	public void checkProductBeforeCheckout(boolean login,String language) throws ExecutionException 
	{
		if (!login)
		{
			
			String flight = Global.getExcelData().get("FLIGHT").trim();
			clearField("anonymousflightdetails input");
			
			writeInto("anonymousflightdetails input", flight);	
			
			String applyBtnXpath;
			
			if(language==LanguageOptions.English.toString()){
				applyBtnXpath = "text_flight_checkout_span search_span_by_text";
			}else{
				applyBtnXpath = "cn_text_flight_checkout_span search_span_by_text";
			}
			
			hoverAndPushOn(applyBtnXpath);
			
			wait(5);
			waitTo("selectflightdetails select", 20);
			verify("selectflightdetails select");
			String flightDetails;
			if(language==LanguageOptions.English.toString()){
				flightDetails = Global.getExcelData().get("FLIGHT_DETAILS").trim();
		
			}else{
				flightDetails = Global.getExcelData().get("FLIGHT_DETAILS_CHINESE").trim();
			}
			
			selectOptions("selectflightdetails select", flightDetails);
		}
		else
		{
			waitTo("selectflightdetails select", 20);
			verify("selectflightdetails select");
			String flightDetails;
			if(language==LanguageOptions.English.toString()){
				flightDetails = Global.getExcelData().get("BOOKING").trim();
		
			}else{
				flightDetails = Global.getExcelData().get("BOOKING_CHINESE").trim();
			}
			
			selectOptions("selectflightdetails select", flightDetails);
		}
		
		
		String totalPriceActual = getProperty("class_summary_total_div search_div_by_class", "text");		
		String currency = Global.getExcelData().get("CURRENCY").trim();
		String totalPriceInput;
		
		if(language==LanguageOptions.English.toString()){
			totalPriceInput = currency + Global.getExcelData().get("PRICE").trim();
		}else{
			totalPriceInput = currency + Global.getExcelData().get("PRICE_CHINESE").trim();
		}
		
		if (totalPriceActual.equalsIgnoreCase(totalPriceInput))
		{
			reportValidation("Total Price is correct", true, true);
		}
		else
		{
			reportValidation("Total Price is incorrect", false, true);
		}
		
		
		String givenName = Global.getExcelData().get("GIVEN_NAME").trim();
		String lastName = Global.getExcelData().get("LAST_NAME").trim();
		selectOptions("selectrecipientdetails select", givenName + " " + lastName);
		
		wait(10);
		
		if(AirAsiaBDFWebMain.specialRequest) {

			String specialRequesBtnXpath;
			if(language==LanguageOptions.English.toString()){
				specialRequesBtnXpath="text_special_request_p search_p_by_text";
			}else{
				specialRequesBtnXpath="cn_text_special_request_p search_p_by_text";
			}
			hoverAndPushOn(specialRequesBtnXpath);
			
			String specialRequest = Global.getExcelData().get("SPECIAL_REQUEST").trim();
			hoverAndPushOn("addmessage div");
			clearField("addmessage div");
			writeInto("addmessage div", specialRequest);
			
		}
		
		wait(10);
	}
	
	/**
	 * This method inputs personal details for checkout.
	 * @throws ExecutionException
	 */
	public void inputPersonalDetails(String language) throws ExecutionException 
	{
		hoverAndPushOn("id_same_flight_input input");
		
		clearField("id_fname_input input");
		writeInto("id_fname_input input", Global.getExcelData().get("GIVEN_NAME").trim());	

		wait(1);
		
		clearField("id_lname_input input");
		writeInto("id_lname_input input", Global.getExcelData().get("LAST_NAME").trim());	
		
		wait(1);

		clearField("id_email_input input");
		writeInto("id_email_input input", Global.getExcelData().get("EMAIL").trim());	

		wait(1);
		
		clearField("id_cnum_input input");
		writeInto("id_cnum_input input", Global.getExcelData().get("Cnum").trim());	

		wait(1);
		
		clearField("id_bsid_input input");
		writeInto("id_bsid_input input", Global.getExcelData().get("MEMBER_ID").trim());

		wait(1);
		
		String procPayButtonXpath;
		
		if(language==LanguageOptions.English.toString()){
			procPayButtonXpath = "text_proceed_pay_button search_button_by_text";
		}else{
			procPayButtonXpath = "cn_text_proceed_pay_button search_button_by_text";
		}
		hoverAndPushOn(procPayButtonXpath);
	}
}
