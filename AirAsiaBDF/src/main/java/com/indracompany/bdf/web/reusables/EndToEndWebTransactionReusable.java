package com.indracompany.bdf.web.reusables;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.boon.validation.ValidationException;
import org.openqa.selenium.Keys;

import com.indra.company.bdf.service.LanguageService;
import com.indra.company.bdf.service.PaymentService;
import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;
import com.indracompany.bdf.web.model.PaymentTypes;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.exception.ExecutionException;
import indra.cilantrum.util.Logger;

import com.indra.company.bdf.service.LoginService; //PROINTES-3268 JAVE 

public class EndToEndWebTransactionReusable extends CilantrumReusable{

	final Logger logger = new Logger(getClass().getSimpleName());
	private boolean withLogin = false;
	LoginService loginService = new LoginService(); //PROINTES-3268 JAVE
	@Override
	protected void loadParameters() {
		newParameters("withLogin");
	}
	
	private String orderNumber;
	private String language;
	
	PaymentService paymentService = new PaymentService();
	LanguageService languageService = new LanguageService();
	
	
	public void execute() throws ExecutionException {
		//languageService.selectLanguage();
		language = AirAsiaBDFWebMain.languageOption;
		
		if(language==LanguageOptions.Chinese.toString()){
			languageService.selectLanguage();
		}
		
		withLogin = Boolean.valueOf(getParameter("withLogin"));
		
		if(AirAsiaBDFWebMain.paymentType==PaymentTypes.Alipay.toString()){
			paymentService.changeCurrency(AirAsiaBDFWebMain.languageOption,AirAsiaBDFWebMain.paymentType);

		}
		
		if (withLogin)
		{
			/*
			waitTo("text_login_link search_a_by_text", 5);
			logger.info(1, "Click login link.");
			hoverAndPushOn("text_login_link search_a_by_text");
			
			login();
			
	  		String bookingNo = Global.getExcelData().get("FLIGHT_NAME").trim();
	  		wait(10);
	  		waitTo("id_select_flight search_component_by_id", 20);
	  		
	  		logger.info(1, "Select flight from the dropdown at the top part of the screen.");
	  		selectOptions("id_select_flight search_component_by_id", bookingNo);
	  		wait(10);
	  		
	  		navigateTo(Global.getGlobalsDevice().URL+"en/MYR/D7");

	  		wait(10);
	  		
	  		bookingNo = Global.getExcelData().get("FLIGHT_NAME").trim();
	  		waitTo("id_select_flight search_component_by_id", 20);
	  		
	  		logger.info(1, "Select flight from the dropdown at the top part of the screen.");
	  		selectOptions("id_select_flight search_component_by_id", bookingNo);
	  		wait(10);

	  		*/

	  		loginService.login(); //PROINTES-3268 JAVE
	  		
	  		String bookingNo = Global.getExcelData().get("FLIGHT_NAME").trim();
	  		wait(10);
	  		waitTo("bookingSelection search_component_by_id", 20);
	  		
	  		logger.info(1, "Select flight from the dropdown at the top part of the screen.");
	  		selectOptions("bookingSelection search_component_by_id", bookingNo);
	  		wait(10);
	  		
	  		
	  		//jave
	  
	  		String category;
//	  		if(language==LanguageOptions.English.toString()){
//	  			category = Global.getExcelData().get("CATEGORY").trim();
//	  		}else{
//	  			category = Global.getExcelData().get("CATEGORY_CHINESE").trim();
//	  		}
//	  		
//			logger.info(1, "Click product category from the menu.");
//			hoverAndPushOn(category + " menucategory");
//			
//			waitTo("productcatalog box", 5);
//			logger.info(1, "Click heart icon at the top right of the product to add to wishlist.");
//			hoverAndPushOn("class_wish_default_div heartdivclass");
//			verify("class_wish_added_div heartdivclass");
//			
//			logger.info(1, "Select product from the catalog.");
//			waitTo(category + " search_span_by_text", 10);
//			hoverAndPushOn("productgrid product");
//			wait(10);
			
			if(language==LanguageOptions.English.toString()){
				category = Global.getExcelData().get("CATEGORY").trim();
				hoverAndPushOn(category + " menucategory");
				wait(5);
				hoverAndPushOn("productgrid product");
			}else{
				hoverAndPushOn("cn_text_menu_name_anon select_menu_by_text");
				wait(5);
				hoverAndPushOn("cn_id_product_name_with_freegift_login search_p_by_text");
			}
			
			wait(5);
			logger.info(1, "Check product name, brand and price.");
			checkProductDetails();
			
			logger.info(1, "Check product delivery option.");
			checkDeliveryOptions();
			
			logger.info(1, "Check product description.");
			checkProductDescription();
			
			logger.info(1, "Check free gift (if any) of the product.");
			checkFreeGift();
			
			/**
			 * Add to wishlist
			 */
			logger.info(1, "Add product to wishlist.");
			addProductToWishlist();
			
			/**
			 * Click settings
			 */
			
			logger.info(1, "Click username at the top right of the page.");
			hoverAndPushOn("class_hi_user_span span");
			
			logger.info(1, "Verify items in the menu: Profile, Order, Wishlist and Logout");
			verify("text_settings_profile_a search_a_by_text");
			
			if(language==LanguageOptions.English.toString()){
				verify("text_settings_orders_a search_a_by_text");
				verify("text_settings_wishlist_a search_a_by_text");
				verify("text_settings_logout_a search_a_by_text");
				
				logger.info(1, "Click wishlist from the menu.");
				hoverAndPushOn("text_settings_wishlist_a search_a_by_text");
			}else{
				verify("cn_text_settings_orders_a search_a_by_text");
				verify("cn_text_settings_wishlist_a search_a_by_text");
				verify("cn_text_settings_logout_a search_a_by_text");
				
				logger.info(1, "Click wishlist from the menu.");
				hoverAndPushOn("cn_text_settings_wishlist_a search_a_by_text");
			}

			wait(10);
			
			String addToCartButtonXpath;
			logger.info(1, "Click add to cart button.");
			if(language==LanguageOptions.English.toString()){
				addToCartButtonXpath = "text_add_cart_span search_span_by_text";
			}else{
				addToCartButtonXpath = "cn_text_add_cart_span search_span_by_text";
			}
			wait(2);
			hoverAndPushOn(addToCartButtonXpath);

			
			verify("item_added_icon checkicon");
			wait(10);


			
			logger.info(1, "Click cart image on the top right of the page.");
			hoverAndPushOn("cart image");
			wait(10);		
			String checkOutBtnXpath;
			if(language==LanguageOptions.English.toString()){
				checkOutBtnXpath = "text_checkout_button search_button_by_text";
			}else{
				checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
			}

			//PROINTES-3277 JAVE START

			if(AirAsiaBDFWebMain.specialRequest) {
				logger.info(1, "Verify Special Request Function");
				hoverAndPushOn("text_special_request_p search_p_by_text");

				String specialRequest = Global.getExcelData().get("SPECIAL_REQUEST").trim();
				hoverAndPushOn("addmessage div");
				clearField("addmessage div");
				writeInto("addmessage div", specialRequest);
				wait(10);
			}
			  
			 //PROINTES-3277 JAVE END


			
			verify(checkOutBtnXpath);		
			verify("class_summary_total_div search_div_by_class");
			
			logger.info(1, "Check product details before checkout.");
			paymentService.checkProductBeforeCheckout(withLogin,language);
			wait(10);
			
			logger.info(1, "Click Checkout button.");
			
			hoverAndPushOn(checkOutBtnXpath);
			wait(10);
			
//			logger.info(1, "Input personal details in the form. ");
//			paymentService.inputPersonalDetails(language);		
			
//			logger.info(1, "Input credit card details. ");
			//inputCreditCardDetails();
			
			wait(10);	
			
		}
		else
		{
	  		//waitTo("id_select_flight search_component_by_id", 20);
			
			
			String category;
			
			if(language==LanguageOptions.English.toString()){
				category = Global.getExcelData().get("CATEGORY").trim();
			}else{
				category = Global.getExcelData().get("CATEGORY_CHINESE").trim();
			}	
			hoverAndPushOn(category + " menucategory");
			
			waitTo(category + " search_span_by_text", 10);
			hoverAndPushOn("productgrid product");
			wait(5);

			logger.info(1, "Check product name, brand and price.");
			checkProductDetails();
			
			logger.info(1, "Check product delivery option.");
			checkDeliveryOptions();
			
			logger.info(1, "Check product description.");
			checkProductDescription();
			
			if(language==LanguageOptions.English.toString())
			{
				logger.info(1, "Check free gift (if any) of the product.");
				checkFreeGift();
			}
	
			
			logger.info(1, "Click add to cart button.");
			String addToCartButtonXpath;
		
			if(language==LanguageOptions.English.toString()){
				addToCartButtonXpath = "text_add_cart_span search_span_by_text";
			}else{
				addToCartButtonXpath = "cn_text_add_cart_span search_span_by_text";
			}
			
			hoverAndPushOn(addToCartButtonXpath);
			
			
			wait(2);
			verify("item_added_icon checkicon");	
			
			logger.info(1, "Click cart image on the top right of the page.");
			hoverAndPushOn("cart image");
			wait(5);	
			
			String checkOutBtnXpath;
			
			if(language==LanguageOptions.English.toString()){
				checkOutBtnXpath = "text_checkout_button search_button_by_text";
			}else{
				checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
			}
			verify(checkOutBtnXpath);		
			verify("class_summary_total_div search_div_by_class");
			
			
			logger.info(1, "Check product details before checkout.");
			paymentService.checkProductBeforeCheckout(withLogin,language);
			
			logger.info(1, "Click Checkout button.");
			
			hoverAndPushOn(checkOutBtnXpath);
			wait(5);
			
//			logger.info(1, "Input personal details in the form. ");
//			paymentService.inputPersonalDetails(language);	
			
//			logger.info(1, "Input credit card details. ");
			//inputCreditCardDetails();
			
			//wait(10);	
			
		}
		//paymentService.inputCreditCardDetails(language);
		//paymentService.creditCardPayment();
		//paymentService.alipayPayment(language);
		//paymentService.orderVerification();

		paymentService.paymentMethod(AirAsiaBDFWebMain.paymentType, AirAsiaBDFWebMain.languageOption);
	
	}
	
	/**
	 * use for credit or debit card payment
	 */
//	private void creditCardPayment() throws ExecutionException {
//		wait(3);
//		hoverAndPushOn("card_agreement_terms checkbox");
//		wait(2);
//		
//		hoverAndPushOn("card_continue_pay button");
//		wait(20);	
//		verifyProductAfterPaying(); 
//		
//	}
	
	/**
	 * use for credit or debit card payment
	 */
//	private void alipayPayment() throws ExecutionException {
//		wait(3);
//		hoverAndPushOn("currency_arrow icon");
//		
//		wait(3);
//		hoverAndPushOn("cny_currency link");
//		
//		wait(3);
//		hoverAndPushOn("internet_banking div");
//		
//		wait(3);
//		hoverAndPushOn("alipay_icon radiobutton");
//		
//		wait(5);
//		hoverAndPushOn("alipay_agreement_terms checkbox");
//		
//		wait(5);
//		hoverAndPushOn("alipay_continue_pay button");
//		
//		wait(10);
//		hoverAndPushOn("alipay_confirmation_approve button");
//		//verifyProductAfterPaying(); 
//		
//	}
	
	/**
	 * use for verification of order
	 */
//	private void orderVerification() throws ExecutionException{
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
////		hoverAndPushOn("email_message link");
////		wait(5);
//		
//		
//		String latestEmailMessege = getProperty("email_message link", "text");
//		
//		if(latestEmailMessege.contains(orderNumber))
//		{
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
	/**
	 * This method clicks the add to wishlist button.
	 * @throws ExecutionException
	 */
	private void addProductToWishlist() throws ExecutionException 
	{
		hoverAndPushOn("text_add_wishlist_span search_span_by_text");
		verify("text_add_wishlist_added_span search_span_by_text");
	}

	/**
	 * This method verifies details of the product bought.
	 * @throws ExecutionException
	 */
//	private void verifyProductAfterPaying() throws ExecutionException 
//	{
//		verify("orderno div");
//		verify("purchasedate div");
//		orderNumber = getProperty("orderno div", "text").substring(7);
//		verify("class_product_name_summ_p search_p_by_class");
//		verify("class_brand_name_summ_p search_p_by_class");
//		verify("class_purchased_date_p search_p_by_class");
//		verify("class_qty_p search_p_by_class");
//		verify("class_price_p search_p_by_class");
//		verify("class_email_confirm search_p_by_class");
//		verify("class_suggestions search_div_by_class");
//	}

	/**
	 * This method inputs credit card details before payment.
	 * @throws ExecutionException
	 */
//	private void inputCreditCardDetails() throws ExecutionException 
//	{
//		hoverAndPushOn("text_credit_card_li search_li_by_text");
//		
//		String creditCard = Global.getExcelData().get("CREDIT_CARD").trim();
//		clearField("text_input_credit_card_placeholder search_input_by_placeholder");
//		writeInto("text_input_credit_card_placeholder search_input_by_placeholder", creditCard);
//		
//		selectOptions("ccmonth div", Global.getExcelData().get("CC_EXPIRE_MM").trim());
//		selectOptions("ccyear div", Global.getExcelData().get("CC_EXPIRE_YEAR").trim());
//		
//		clearField("text_input_name_card_placeholder search_input_by_placeholder");
//		writeInto("text_input_name_card_placeholder search_input_by_placeholder", Global.getExcelData().get("GIVEN_NAME").trim());
//		
//		clearField("text_input_cvv_placeholder search_input_by_placeholder");
//		writeInto("text_input_cvv_placeholder search_input_by_placeholder", Global.getExcelData().get("CC_CVV").trim());
//		
//		//hoverAndPushOn("text_cont_pay_button search_button_by_text");
//	}

//	/**
//	 * This method inputs personal details for checkout.
//	 * @throws ExecutionException
//	 */
//	private void inputPersonalDetails() throws ExecutionException 
//	{
//		hoverAndPushOn("id_same_flight_input input");
//		
//		clearField("id_fname_input input");
//		writeInto("id_fname_input input", Global.getExcelData().get("GIVEN_NAME").trim());	
//		
//		clearField("id_lname_input input");
//		writeInto("id_lname_input input", Global.getExcelData().get("LAST_NAME").trim());	
//		
//		clearField("id_email_input input");
//		writeInto("id_email_input input", Global.getExcelData().get("EMAIL").trim());	
//		
//		clearField("id_cnum_input input");
//		writeInto("id_cnum_input input", Global.getExcelData().get("Cnum").trim());	
//		
//		clearField("id_bsid_input input");
//		writeInto("id_bsid_input input", Global.getExcelData().get("MEMBER_ID").trim());
//		
//		hoverAndPushOn("text_proceed_pay_button search_button_by_text");
//	}

	/**
	 * This method checks the price, delivery options and flight before checkout.
	 * @throws ExecutionException
	 */
	

	/**
	 * Checks if product includes free gift.
	 * @throws ExecutionException
	 */
	private void checkFreeGift() throws ExecutionException 
	{
		String freeGiftData = Global.getExcelData().get("FREE_GIFT").trim();
		if (!freeGiftData.equals("N/A"))
		{
				
			String freeGiftPurchaseXpath;
			
			if(language==LanguageOptions.English.toString()){
				freeGiftPurchaseXpath = "text_free_gift_h5 search_h5_by_text";
			}else
{				freeGiftPurchaseXpath = "cn_text_free_gift_h5 search_h5_by_text";
			}
			
			hoverAndPushOn(freeGiftPurchaseXpath);
			
			wait(3);
			verify("freegift div");
			String freeGift =  getProperty("freegift div", "text");
			if (freeGiftData.trim().equalsIgnoreCase(freeGift.trim()))
			{
				reportValidation("Free gift item is valid.", true, true);
			}
			else
			{
				reportValidation("Free gift item is invalid.", false, true);
			}
		}
		else		{
			reportValidation("Product has no free gift.", true, true);
		}
	}

	/**
	 * This method checks product description.
	 * @throws ExecutionException
	 */
	private void checkProductDescription() throws ExecutionException 
	{
		String prodInfoXpath;
		if(language==LanguageOptions.English.toString()){
			prodInfoXpath = "text_product_info_h5 search_h5_by_text";
		}else{
			prodInfoXpath = "cn_text_product_info_h5 search_h5_by_text";
		}
		hoverAndPushOn(prodInfoXpath);
		wait(3);
		
		String productInfoData = Global.getExcelData().get("PRODUCT_INFO").trim();
		
		if (!"N/A".equalsIgnoreCase(productInfoData))
		{
			verify("product_information text");
			String productInfo =  getProperty("product_information text", "text");
			if (StringUtils.isNotBlank(productInfo))
			{
				reportValidation("Product info exists.", true, true);
			}
			else
			{
				reportValidation("Product info missing", false, true);
			}
		}
		else
		{
			reportValidation("No available product details.", true, true);
		}
	
	}

	/**
	 * This method checks the delivery options for the selected product.
	 * @throws ExecutionException
	 */
	private void checkDeliveryOptions() throws ExecutionException 
	{
		String deliveryOptionsXpath;
		
		if(language==LanguageOptions.English.toString()){
			deliveryOptionsXpath = "text_delivery_options_h5";
		}else{
			deliveryOptionsXpath = "cn_text_delivery_options_h5";
		}
		
		hoverAndPushOn(deliveryOptionsXpath + " search_h5_by_text");
		wait(3);
		
		verify("flightvalue span");
		verify("flighttypevalue span");
		
		String flightActual = getProperty("flightvalue span", "text").trim();
		String flightTypeActual = getProperty("flighttypevalue span", "text").trim();
		
		String flightInput = Global.getExcelData().get("FLIGHT_NUMBER").trim();
		String flightTypeInput;
		
		if(language==LanguageOptions.English.toString()){
			flightTypeInput = Global.getExcelData().get("FLIGHT_TYPE").trim();
		}else{
			flightTypeInput = Global.getExcelData().get("FLIGHT_TYPE_CHINESE").trim();
		}
		
		if (StringUtils.isNotBlank(flightInput) && 
				StringUtils.isNotBlank(flightTypeInput) &&
				flightInput.equalsIgnoreCase(flightActual) &&
				flightTypeInput.equalsIgnoreCase(flightTypeActual))
		{
			reportValidation("Flight and flight type are correct.", true, true);
		}
		else
		{
			reportValidation("Flight and flight type are incorrect.", false, true);
		}
	}

	/**
	 * This method displays the product details after selecting a product from the list.
	 * @throws ExecutionException
	 */
	private void checkProductDetails() throws ExecutionException 
	{
		
		String xpath;
		
		if(language==LanguageOptions.English.toString()){
			xpath = "text_instock_p search_p_by_text";
		}else
		{
			xpath = "cn_text_instock_p search_p_by_text";
		}
		if (exists(xpath, 5))
		{
			String currency = Global.getExcelData().get("CURRENCY").trim();
			verify("class_discounted_price_p search_p_by_class");
			if (exists("class_discounted_price_p search_p_by_class", 3))
			{
				verify(currency + " pdiscountedtext");
			}
			
			/*		
			verify("class_original_price_p search_p_by_class");
			if (exists("class_original_price_p search_p_by_class", 3))
			{
				verify(currency + " porigtext");
			}*/
			
			verify("class_brand_name_p search_p_by_class");
			verify("class_product_name_p search_p_by_class");
			
			String productInput;
			String brandInput;
			
			if(language==LanguageOptions.English.toString()){
				productInput = Global.getExcelData().get("PRODUCT").trim();
				brandInput = Global.getExcelData().get("BRAND").trim();
			}
			else{
				productInput = Global.getExcelData().get("PRODUCT_CHINESE").trim();
				brandInput = Global.getExcelData().get("BRAND_CHINESE").trim();
			}
			
			String brandName = getProperty("class_brand_name_p search_p_by_class", "text").trim();
			String productName = getProperty("class_product_name_p search_p_by_class", "text").trim();

			if (StringUtils.isNotBlank(productInput) && 
					StringUtils.isNotBlank(brandInput) &&
					productInput.equalsIgnoreCase(productName) &&
					brandInput.equalsIgnoreCase(brandName))
			{
				reportValidation("Brand name and product name are correct.", true, true);
			}
			else
			{
				reportValidation("Brand name and product name are incorrect.", false, true);
			}
		}
		else
		{
			reportValidation("Product not in stock.", false, true);
		}
		

	}
	
	/**
	 * This method is for putting user name and password to log in the system.
	 * @throws ExecutionException
	 */
	public void login() throws ExecutionException
	{
		
		waitTo("id_login_btn input", 5);
		String username = Global.getExcelData().get("USERNAME").trim();
		logger.info(1, "Input username.");
		clearField("id_userame_text input");
		writeInto("id_userame_text input", username);
		
		if (Global.getGlobalsDevice().DEVICE_NAME.equalsIgnoreCase("ie"))
		{
			writeInto("id_userame_text input", Keys.RETURN.toString());
		}
		
		String password = Global.getExcelData().get("PASSWORD").trim();
		logger.info(1, "Input password.");
		clearField("id_password_text input");
		writeInto("id_password_text input", password);
		
		logger.info(1, "Click login button.");
		if (Global.getGlobalsDevice().DEVICE_NAME.equalsIgnoreCase("ie"))
		{
			writeInto("id_userame_text input", Keys.RETURN.toString());
		}
		else
		{
			hoverAndPushOn("id_login_btn input");
		}
		wait(20);
		
		if (exists("id_login_btn input", 5))
		{
			reportError("Invalid login credentials",true,null,true);
		}
	}
		
	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}
	public void postExecute() throws ExecutionException
	{
//		navigateTo(Global.getGlobalsDevice().URL);
//		
//		wait(10);
//		hoverAndPushOn("cart image");
//		wait(10);
//		
//		if (!exists("text_no_items search_p_by_text", 15))
//		{
//			while (exists("cleacartlist icon", 10))
//			{
//				hoverAndPushOn("cleacartlist icon");
//				wait(5);
//			}
//		}
//		
//		if (withLogin)
//		{
//			hoverAndPushOn("class_hi_user_span span");
//			
//			hoverAndPushOn("text_settings_wishlist_a search_a_by_text");
//			
//			while (exists("clearwishlist icon", 10))
//			{
//				hoverAndPushOn("clearwishlist icon");
//				wait(5);
//			}
//			
//			hoverAndPushOn("class_hi_user_span span");
//			
//			hoverAndPushOn("text_settings_logout_a search_a_by_text");
//		}
//		
//		deviceOperation("cleardata", "");
	}

}
