package com.indracompany.bdf.web.reusables;

import com.indra.company.bdf.service.FeedbackService;
import com.indra.company.bdf.service.LanguageService;
import com.indra.company.bdf.service.LoginService;
import com.indra.company.bdf.service.PaymentService;
import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;

import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;

public class FeedbackMainSectionTransactionReusable extends CilantrumReusable{
	
	boolean withLogin;
	private String language;
	FeedbackService feedbackService = new FeedbackService();
	PaymentService paymentService = new PaymentService();
	LanguageService languageService = new LanguageService();
	LoginService loginService = new LoginService();
	@Override
	protected void loadParameters() {
		newParameters("withLogin");
		
	}
	
	public void execute() throws ExecutionException {
	language = AirAsiaBDFWebMain.languageOption;
		
		if(language==LanguageOptions.Chinese.toString()){
			languageService.selectLanguage();
		}
		
		withLogin = Boolean.valueOf(getParameter("withLogin"));
		
		// TODO Auto-generated method stub
		

		if(withLogin){
			loginService.login(); //PROINTES-3268 JAVE
			wait(15);

		}
		homepageFeedback();
		categoryMenuFeedback();
		productFeedback();
		cartFeedback();
		orderFeedback();
		paymentFeedback();
		confirmationFeedback();
		searchFeedback();
	}

	
	
	// 1 -12
	public void homepageFeedback() throws ExecutionException{
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	//12 - 22
	public void categoryMenuFeedback() throws ExecutionException{
		wait(2);
		if(language==LanguageOptions.English.toString()){
			hoverAndPushOn("text_menu_name_anon select_menu_by_text");
		}else{
			hoverAndPushOn("cn_text_menu_name_anon select_menu_by_text");
		}
//		
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	//22 - 32
	public void productFeedback() throws ExecutionException{
		if(language==LanguageOptions.English.toString()){
			hoverAndPushOn("id_product_name_with_freegift search_p_by_text");
		}else{
			hoverAndPushOn("cn_id_product_name_with_freegift search_p_by_text");
		}
		
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	//32-43
	public void cartFeedback() throws ExecutionException{
		hoverAndPushOn("id_product_details_add_to_cart_button product_details_add_to_cart_btn");
		wait(3);
		hoverAndPushOn("class_div_medium_up_header cart_icon_img");
		wait(2);
//		feedbackService.feedBackWithoutSubmitting();
//		feedbackService.feedbackWithSubmitting();
	}
	
	//43-58
	public void orderFeedback() throws ExecutionException{
		if (!withLogin)
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
		
		String givenName = Global.getExcelData().get("GIVEN_NAME").trim();
		String lastName = Global.getExcelData().get("LAST_NAME").trim();
		selectOptions("selectrecipientdetails select",givenName + " " + lastName );
		
		wait(5);
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
		
		String checkOutBtnXpath;
		if(language==LanguageOptions.English.toString()){
			checkOutBtnXpath = "text_checkout_button search_button_by_text";
		}else{
			checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
		}
		
		wait(2);
		hoverAndPushOn(checkOutBtnXpath);
		wait(5);
		
		hoverAndPushOn("id_same_flight_input input");
		
		clearField("id_fname_input input");
		writeInto("id_fname_input input", Global.getExcelData().get("GIVEN_NAME").trim());	
		
		clearField("id_lname_input input");
		writeInto("id_lname_input input", Global.getExcelData().get("LAST_NAME").trim());	
		
		clearField("id_email_input input");
		writeInto("id_email_input input", Global.getExcelData().get("EMAIL").trim());	
		
		clearField("id_cnum_input input");
		writeInto("id_cnum_input input", Global.getExcelData().get("Cnum").trim());	
		
		clearField("id_bsid_input input");
		writeInto("id_bsid_input input", Global.getExcelData().get("MEMBER_ID").trim());
		
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	//58-74
	public void paymentFeedback() throws ExecutionException{
		if(language==LanguageOptions.English.toString()){
			hoverAndPushOn("text_proceed_pay_button search_button_by_text");
		}else{
			hoverAndPushOn("cn_text_proceed_pay_button search_button_by_text");
		}
		
		wait(3);
		
		paymentService.inputCreditCardDetails(language);
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();

	}
	
	//74-86
	public void confirmationFeedback() throws ExecutionException{
		paymentService.creditCardPayment(language);
		wait(10);	
		paymentService.verifyProductAfterPaying(language);
		
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	//86-96
	public void searchFeedback() throws ExecutionException{
		hoverAndPushOn("class_div_medium_up_header search_input_btn");
		wait(10);	
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
	}
	
	
	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}



}
