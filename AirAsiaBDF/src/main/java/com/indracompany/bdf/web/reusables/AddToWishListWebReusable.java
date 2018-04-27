package com.indracompany.bdf.web.reusables;

import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;
import indra.cilantrum.util.Logger;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;
import com.indracompany.bdf.web.model.PaymentTypes;
import com.indra.company.bdf.service.LanguageService;
import com.indra.company.bdf.service.LoginService;
import com.indra.company.bdf.service.PaymentService;

public class AddToWishListWebReusable extends CilantrumReusable{
	
	private Logger logger = new Logger(getClass().getSimpleName());
	private boolean withLogin = false;
	private String language;
	LoginService loginService = new LoginService();
	PaymentService paymentService = new PaymentService();
	LanguageService languageService;
	
	String category = "";
	String product = "";
	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void execute() throws ExecutionException {
		
		withLogin = Boolean.valueOf(getParameter("withLogin"));
		language = AirAsiaBDFWebMain.languageOption;
				
		languageService = new LanguageService();
		EndToEndWebTransactionReusable reusable = new EndToEndWebTransactionReusable();
		
		if(language==LanguageOptions.Chinese.toString()){
			languageService.selectLanguage();
		}
		
		if(AirAsiaBDFWebMain.paymentType==PaymentTypes.Alipay.toString()){
			paymentService.changeCurrency(AirAsiaBDFWebMain.languageOption,AirAsiaBDFWebMain.paymentType);

		}
		
		category = languageService.languageHeader("CATEGORY");
		product = languageService.languageHeader("PRODUCT");
		
		if (!withLogin)
		{
			wait(20);
			
			
			
				
			/*
			if(language==LanguageOptions.English.toString()){
				category = Global.getExcelData().get("CATEGORY").trim();
			}else if(language==LanguageOptions.Chinese.toString()) {
				category = Global.getExcelData().get("CATEGORY_CHINESE").trim();
			}
			*/
			
			logger.info(1, "Click product category from the menu.");
			hoverAndPushOn(category + " menucategory");
			wait(15);
			
			logger.info(1, "Click heart icon at the top right of the product to add to wishlist.");
			hoverAndPushOn("class_wish_default_div heartdivclass");
			

			logger.info(1, "Click x button from the popup.");
			hoverAndPushOn("close-icon search_img_by_class");
			
			/*
			if(language==LanguageOptions.English.toString()){
				product = Global.getExcelData().get("PRODUCT").trim();
			}else if(language==LanguageOptions.Chinese.toString()) {
				product = Global.getExcelData().get("PRODUCT_CHINESE").trim();
			}
			*/
			
			logger.info(1, "Click product from the catalog.");
			hoverAndPushOn(product + " search_p_by_text");
			wait(5);

			logger.info(1, "Click add to wishlist button.");
			hoverAndPushOn("text_add_wishlist_span search_span_by_text");
			wait(5);

			logger.info(1, "Click x button from the popup.");
			hoverAndPushOn("close-icon search_img_by_class");
			
			/*
			logger.info(1, "Click login from the link.");
			waitTo("text_login_link_2 search_a_by_text", 5);
			hoverAndPushOn("text_login_link_2 search_a_by_text");
			*/
			
			wait(10);
			//reusable.login();
			loginService.login();
			
			withLogin = true;
			
			waitTo("class_hi_user_span span", 10);
			logger.info(1, "Verify successful login.");
			verify("class_hi_user_span span");

			navigateTo(Global.getGlobalsDevice().URL);
		}else {
			

			/*
			logger.info(1, "Click login link.");
			waitTo("text_login_link search_a_by_text", 10);
			hoverAndPushOn("text_login_link search_a_by_text");
			
			reusable.login();
			
	  		String bookingNo = Global.getExcelData().get("FLIGHT_NAME").trim();
			//selectOptions("id_select_flight select", bookingNo);
	  		wait(10);
	  		waitTo("id_select_flight search_component_by_id", 20);
	  		logger.info(1, "Select flight from the dropdown at the top part of the screen.");
	  		selectOptions("id_select_flight search_component_by_id", bookingNo);
	  		wait(15);

	  		*/
	  		loginService.login();
	  		
	  		wait(15);
			
	  		/*
			if(language==LanguageOptions.English.toString()){
				category = Global.getExcelData().get("CATEGORY").trim();
			}else if(language==LanguageOptions.Chinese.toString()) {
				category = Global.getExcelData().get("CATEGORY_CHINESE").trim();
			}
			*/
			
			wait(15);
			
			/*
			String product = Global.getExcelData().get("PRODUCT");
			
			logger.info(1, "Click heart icon at the top right of the product to add to wishlist.");
			hoverAndPushOn(product + " search_p_by_text");
			//verify("class_wish_added_div heartdivclass");
			wait(5);
			
			*/

			category = languageService.languageHeader("CATEGORY");
			
			logger.info(1, "Click category from the menu.");
			hoverAndPushOn(category + " menucategory");
			wait(15);
			
			/*
		
			logger.info(1, "Select product from the catalog.");
			waitTo(category + " search_span_by_text", 10);
			hoverAndPushOn("productgrid product");
			wait(15);
			
			product + " search_span_by_text
			
			
			*/
			/*	
			if(language==LanguageOptions.English.toString()){
				product = Global.getExcelData().get("PRODUCT").trim();
			}else if(language==LanguageOptions.Chinese.toString()) {
				product = Global.getExcelData().get("PRODUCT_CHINESE").trim();
			}
			*/
			
			product = languageService.languageHeader("PRODUCT");

			logger.info(1, "Click product from the catalog.");
			hoverAndPushOn(product + " search_p_by_text");
			wait(5);
			
			logger.info(1, "Click add to wishlist button.");
			hoverAndPushOn(languageService.languageTranslation("text_add_wishlist_span search_span_by_text"));
			wait(3);
			verify(languageService.languageTranslation("text_add_wishlist_added_span search_span_by_text"));
			
			
			logger.info(1, "Click username at the top right part of the screen.");
			hoverAndPushOn("class_hi_user_span span");
			
			logger.info(1, "Select wishlist from the menu.");
			hoverAndPushOn(languageService.languageTranslation("text_settings_wishlist_a search_a_by_text"));
			wait(10);	
			
			/*
			logger.info(1, "Click add to cart button from the wishlist.");
			hoverAndPushOn("wishlistaddtocard span2"); //change since product to add in cart may not be in same location
			*/
			
			logger.info(1, "Click add to cart button.");
			hoverAndPushOn(languageService.languageTranslation("text_add_all_to_cart search_span_by_text"));
			wait(10);
			
			
			hoverAndPushOn("cart image");
			wait(20);			
			
			/*
			loginService.selectFlight();
			
			wait(20);
			
			*/
			
			
			String givenName = Global.getExcelData().get("GIVEN_NAME").trim();
			String lastName = Global.getExcelData().get("LAST_NAME").trim();
			selectOptions("selectrecipientdetails select", givenName + " " + lastName);
			
			wait(10);
			
			if(AirAsiaBDFWebMain.specialRequest) {
				
				/*
				String specialRequesBtnXpath;
				if(AirAsiaBDFWebMain.languageOption==LanguageOptions.English.toString()){
					specialRequesBtnXpath="text_special_request_p search_p_by_text";
				}else{
					specialRequesBtnXpath="cn_text_special_request_p search_p_by_text";
				}
				*/
				//hoverAndPushOn(specialRequesBtnXpath);
				hoverAndPushOn(languageService.languageTranslation("text_special_request_p search_p_by_text"));
				
				String specialRequest = Global.getExcelData().get("SPECIAL_REQUEST").trim();
				hoverAndPushOn("addmessage div");
				clearField("addmessage div");
				writeInto("addmessage div", specialRequest);
				
				wait(10);
				
			}
			
			/*
			String checkOutBtnXpath;
			if(AirAsiaBDFWebMain.languageOption==LanguageOptions.English.toString()){
				checkOutBtnXpath = "text_checkout_button search_button_by_text";
			}else{
				checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
			}
			
			*/
			logger.info(1, "Click Checkout button.");
			
			hoverAndPushOn(languageService.languageTranslation("text_checkout_button search_button_by_text"));
			wait(10);
						
			paymentService.paymentMethod(AirAsiaBDFWebMain.paymentType, AirAsiaBDFWebMain.languageOption);	
	  		
		}
		
		//paymentService.paymentMethod(AirAsiaBDFWebMain.paymentType, AirAsiaBDFWebMain.languageOption);
	}

	public void postExecute() throws ExecutionException
	{
		navigateTo(Global.getGlobalsDevice().URL);
		
		wait(15);
		hoverAndPushOn("cart image");
		wait(15);
		
		if (!exists("text_no_items search_p_by_text", 15))
		{
			while (exists("cleacartlist icon", 10))
			{
				hoverAndPushOn("cleacartlist icon");
				wait(5);
			}
		}

		if(AirAsiaBDFWebMain.specialRequest) {
			logger.info(1, "Verify Special Request Function");
			hoverAndPushOn("text_special_request_p search_p_by_text");

			String specialRequest = Global.getExcelData().get("SPECIAL_REQUEST").trim();
			hoverAndPushOn("addmessage div");
			clearField("addmessage div");
			writeInto("addmessage div", specialRequest);
			wait(10);
		}

		if (withLogin)
		{
			hoverAndPushOn("class_hi_user_span span");
			
			hoverAndPushOn("text_settings_wishlist_a search_a_by_text");
			
			while (exists("clearwishlist icon", 10))
			{
				hoverAndPushOn("clearwishlist icon");
				wait(5);
			}
			
			hoverAndPushOn("class_hi_user_span span");
			
			hoverAndPushOn("text_settings_logout_a search_a_by_text");
		}
		

		deviceOperation("cleardata", "");
	}

	@Override
	protected void loadParameters()
	{
		newParameters("withLogin");
		
	}

}
