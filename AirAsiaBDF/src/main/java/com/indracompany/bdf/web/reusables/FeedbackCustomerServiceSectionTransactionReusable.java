package com.indracompany.bdf.web.reusables;

import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.exception.ExecutionException;
import com.indra.company.bdf.service.FeedbackService;
import com.indra.company.bdf.service.LanguageService;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import com.indra.company.bdf.service.LoginService;
import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;


public class FeedbackCustomerServiceSectionTransactionReusable extends CilantrumReusable{

	boolean withLogin;
	private String language;
	FeedbackService feedbackService;
	LoginService loginService;
	LanguageService languageService;

	@Override
	protected void loadParameters() {
		newParameters("withLogin");
	}
	
	public void execute() throws ExecutionException {

		withLogin = Boolean.valueOf(getParameter("withLogin"));
		
		feedbackService = new FeedbackService();
		loginService = new LoginService();
		languageService = new LanguageService();
		
		wait(10);
		
		language = AirAsiaBDFWebMain.languageOption;
		
		if(language==LanguageOptions.Chinese.toString()){
			languageService.selectLanguage();
		}
		
		if(withLogin)
		{
			loginService.login();
		}
		
		verifyLinkTextsExist(language);
		
		verifyLinkTextFeedback(languageService.languageTranslation("text_prebook_collect_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_lowest_price_guarantee_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_orders_and_payment_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_delivery_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_faq_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_duty_allowances_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_terms_of_online_sales_link search_a_by_text"));
		//verifyLinkTextFeedback(languageService.languageTranslation("text_contact_us_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_about_us_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_acceptable_use_policy_link search_a_by_text"));
		verifyLinkTextFeedback(languageService.languageTranslation("text_privacy_policy_link search_a_by_text"));
		
	
	}

	private void verifyLinkTextFeedback(String xpath) throws ExecutionException {
		
		wait(5);
		
		hoverAndPushOn(xpath);
		feedbackService.feedBackWithoutSubmitting();
		feedbackService.feedbackWithSubmitting();
		
		wait(5);
		hoverAndPushOn("class_website_logo_img_div website_logo_img_div");

	}

	private void verifyLinkTextsExist(String language) throws ExecutionException {
		
		wait(5);
		
		verify("text_prebook_collect_link search_a_by_text");
		
		if(exists("text_prebook_collect_link search_a_by_text", 1) || exists("cn_text_prebook_collect_link search_a_by_text", 1)){
			logger.info(1, "Pre-book + Collect link exist.");
		}else{
			logger.info(1, "Pre-book + Collect link not exist.");
		}

		if(exists("text_lowest_price_guarantee_link search_a_by_text", 1) || exists("cn_text_lowest_price_guarantee_link search_a_by_t", 1)){
			logger.info(1, "Lowest Price Guarantee link exist.");
		}else{
			logger.info(1, "Lowest Price Guarantee link not exist.");
		}

		if(exists("text_orders_and_payment_link search_a_by_text", 1) || exists("cn_text_orders_and_payment_link search_a_by_text", 1)){
			logger.info(1, "Orders & Payment link exist.");
		}else{
			logger.info(1, "Orders & Payment link not exist.");
		}

		if(exists("text_delivery_link search_a_by_text", 1) || exists("cn_text_delivery_link search_a_by_text", 1)){
			logger.info(1, "Delivery link exist.");
		}else{
			logger.info(1, "Delivery link not exist.");
		}

		if(exists("text_faq_link search_a_by_text", 1) || exists("cn_text_faq_link search_a_by_text", 1)){
			logger.info(1, "FAQ link exist.");
		}else{
			logger.info(1, "FAQ link not exist.");
		}

		if(exists("text_duty_allowances_link search_a_by_text", 1) || exists("cn_text_duty_allowances_link search_a_by_text", 1)){
			logger.info(1, "Duty Allowances link exist.");
		}else{
			logger.info(1, "Duty Allowances link not exist.");
		}

		if(exists("text_terms_of_online_sales_link search_a_by_text", 1) || exists("cn_text_terms_of_online_sales_link search_a_by_te", 1)){
			logger.info(1, "Terms of Online Sales link exist.");
		}else{
			logger.info(1, "Terms of Online Sales link not exist.");
		}

		if(exists("text_about_us_link search_a_by_text", 1) || exists("cn_text_about_us_link search_a_by_text", 1)){
			logger.info(1, "About Us link exist.");
		}else{
			logger.info(1, "About Us link not exist.");
		}

		if(exists("text_acceptable_use_policy_link search_a_by_text", 1) || exists("cn_text_acceptable_use_policy_link search_a_by_te", 1)){
			logger.info(1, "Acceptable Use Policy link exist.");
		}else{
			logger.info(1, "Acceptable Use Policy link not exist.");
		}

		if(exists("text_privacy_policy_link search_a_by_text", 1) || exists("cn_text_privacy_policy_link search_a_by_text", 1)){
			logger.info(1, "Privacy Policy link exist.");
		}else{
			logger.info(1, "Privacy Policy link not exist.");
		}
		
	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

}
