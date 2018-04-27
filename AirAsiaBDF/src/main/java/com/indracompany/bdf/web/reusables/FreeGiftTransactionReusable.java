package com.indracompany.bdf.web.reusables;

import com.indra.company.bdf.service.LanguageService;
import com.indra.company.bdf.service.PaymentService;
import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;
import com.indracompany.bdf.web.model.PaymentTypes;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.exception.ExecutionException;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain; //PROINTES-3277 JAVE 
import com.indra.company.bdf.service.LoginService; //PROINTES-3268 JAVE

public class FreeGiftTransactionReusable extends CilantrumReusable{
	private String xpath = "";
	private boolean withLogin;
	private String language;
	PaymentService paymentService = new PaymentService();
	LanguageService languageService = new LanguageService();
	LoginService loginService = new LoginService(); //PROINTES-3268 JAVE
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
		
		if(AirAsiaBDFWebMain.paymentType==PaymentTypes.Alipay.toString()){
			paymentService.changeCurrency(AirAsiaBDFWebMain.languageOption,AirAsiaBDFWebMain.paymentType);

		}
		
		if(withLogin){
			//clickLoginSignup();
			//inputLoginDetails();
			//clickLoginButton();	
			//selectFlight();
			loginService.login(); //PROINTES-3268 JAVE
			wait(15);
			selectCategory("login");
			selectChosenProduct("login");
			clickFreeGiftOption("login");
		}else{
			selectCategory("anonymous");
			selectChosenProduct("anonymous");
			clickFreeGiftOption("anonymous");
		}
		clickProductInformation();
		clickDeliveryOptions();
		clickAddToCartButton();
		//loginService.selectFlight();
		if(withLogin){
			clickCartAndCheckProducts("login");
		}else{
			clickCartAndCheckProducts("anonymous");
		}
		
	
		//navigateTo("http://stgbigdutyfree.s3-website-ap-southeast-1.amazonaws.com/#/en/MYR/D7/cart");
		//wait(10);
		//loginService.selectFlight();
		
		wait(10);
//		loginService.selectFlight();
//		wait(20);
		paymentService.checkProductBeforeCheckout(withLogin,language);
		
		logger.info(1, "Click Checkout button.");

		String checkOutBtnXpath;
		if(language==LanguageOptions.English.toString()){
			checkOutBtnXpath = "text_checkout_button search_button_by_text";
		}else{
			checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
		}
		hoverAndPushOn(checkOutBtnXpath);
		wait(10);
		//paymentService.inputPersonalDetails(language);
		
		//paymentService.inputCreditCardDetails(language);
		//paymentService.creditCardPayment();
		//wait(5);
		//paymentService.alipayPayment(language);
		//paymentService.fpxPayment(language);
		//wait(5);
		//paymentService.orderVerification();
		
		paymentService.paymentMethod(AirAsiaBDFWebMain.paymentType, AirAsiaBDFWebMain.languageOption);
	}
	
	/**
	 * This method will click the login/signup button and will direct us to the login page.
	 * @throws ExecutionException
	 */
	public void clickLoginSignup() throws ExecutionException{
		this.xpath = "class_login_span_link search_link_by_span_class";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking login/signup button...");
		
		this.xpath = "id_username_textfield search_component_by_id";
		if(exists(this.xpath,3)){
			reportValidation("SSO login page is displayed.", true, true);				
		}else{
			reportError("SSO login page is not displayed.",true,null,false);
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
		this.xpath = "id_username_textfield search_component_by_id";
		writeInto(this.xpath, userInput);
		wait(3);
		userInputted = getProperty(this.xpath, "value");
		
		this.xpath = "id_password_textfield search_component_by_id";
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
		this.xpath = "id_login_button search_component_by_id";
		pushOn(this.xpath);
		logger.info(1, "Clicking login button...");
	
		this.xpath = "id_flight_select search_component_by_id";
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
	
		wait(10);
		String value, valueSelected;
		value = Global.getExcelData().get("FLIGHT_NAME");
		this.xpath = "id_flight_select search_component_by_id";
		selectOptions(this.xpath,value);
		logger.info(1, "Selecting flight...");

		if(exists(this.xpath, 30)){
			reportValidation("D7 Flight is selected.", true, true);

		}else{
			reportError("D7 Flight is not selected.",true,null,false);

		}

	}
	
	/**
	 * This method will click the gifts and accessories menu
	 * @throws ExecutionException
	 */
	public void selectCategory(String userType) throws ExecutionException{
//		wait(10);
//		this.xpath ="class_menu_icon search_img_by_class";
//		hoverAndPushOn(this.xpath);
//		wait(2);
//		
		wait(10);
		if(userType == "anonymous"){
			if(language==LanguageOptions.English.toString()){
				this.xpath = "text_menu_name_anon select_menu_by_text";
			}else{
				this.xpath = "cn_text_menu_name_anon select_menu_by_text";
			}
		}else if(userType == "login"){	
			if(language==LanguageOptions.English.toString()){
				this.xpath = "text_menu_name_login select_menu_by_text";
			}else{
				this.xpath = "cn_text_menu_name_anon select_menu_by_text";
			}
		}
		
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking chosen category...");
		
		if(userType == "anonymous"){
			if(language==LanguageOptions.English.toString()){
				this.xpath = "id_product_name_with_freegift search_p_by_text";
			}else{
				this.xpath = "cn_id_product_name_with_freegift search_p_by_text";
			}
		}else if(userType == "login"){
			if(language==LanguageOptions.English.toString()){
				this.xpath = "id_product_name_with_freegift_login search_p_by_text";
			}else{
				this.xpath = "cn_id_product_name_with_freegift_login search_p_by_text";
			}
		}
		wait(10);
		if(exists(this.xpath, 30)){
			reportValidation("Product category page is displayed.", true, true);

		}else{
			reportError("Product category page is not displayed.",true,null,false);

		}
	}
	
	/**
	 * This method will click image of the chosen product
	 * @throws ExecutionException
	 */
	public void selectChosenProduct(String userType) throws ExecutionException{
		String expectedCurrency, displayedCurrency;
		expectedCurrency = Global.getExcelData().get("CURRENCY");
		
		if(userType == "anonymous"){		
			if(language==LanguageOptions.English.toString()){
				this.xpath = "id_product_name_with_freegift search_p_by_text";
			}else{
				this.xpath = "cn_id_product_name_with_freegift search_p_by_text";
			}
		}
		else if(userType == "login"){

			if(language==LanguageOptions.English.toString()){
				this.xpath = "id_product_name_with_freegift_login search_p_by_text";
			}else{
				this.xpath = "cn_id_product_name_with_freegift_login search_p_by_text";
			}
		}
		
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking image of the chosen product...");
		
		this.xpath = "product_price text";
		displayedCurrency = getProperty(this.xpath, "text");
		
		if(displayedCurrency.contains(expectedCurrency)){
			reportValidation("Product price is in MYR Currency.", true, true);

		}else{
			reportError("Product price is not in MYR Currency..",true,null,false);

		}
	}
	
	/**
	 * This method will click arrow icon of the delivery option 
	 * @throws ExecutionException
	 */
	public void clickDeliveryOptions() throws ExecutionException{
		String expectedFlightNumber, displayedFlightNumber, expectedFlightType, displayedFlightType;
		expectedFlightNumber = Global.getExcelData().get("FLIGHT_NUMBER");
		
		if(language==LanguageOptions.English.toString()){
			expectedFlightType = Global.getExcelData().get("FLIGHT_TYPE");
		}else{
			expectedFlightType = Global.getExcelData().get("FLIGHT_TYPE_CHINESE");
		}
		
		this.xpath = "class_product_dropdown_option_delivery_options product_dropdown_option";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking the arrow icon of delivery option");
		
		this.xpath = "product_flight_number text";
		displayedFlightNumber = getProperty(this.xpath, "text");
		
		if(displayedFlightNumber.equals(expectedFlightNumber)){
			reportValidation("The flight number displayed is " + displayedFlightNumber, true, true);
			this.xpath = "product_flight_type text";
			displayedFlightType = getProperty(this.xpath, "text");
			
			if(displayedFlightType.equals(expectedFlightType)){
				reportValidation("The flight type displayed is " + displayedFlightType, true, true);
			}else{
				reportError("The flight type is not displayed or not equal to the input data",true,null,false);
			}

		}
		else{
			reportError("The flight numberis not displayed or not equal to the input data",true,null,false);
		}
	}
	
	/**
	 * This method will click arrow icon of the product information 
	 * @throws ExecutionException
	 */
	public void clickProductInformation() throws ExecutionException{
	
		String displayedProductDetails;
		
		this.xpath = "class_product_dropdown_option_product_info product_dropdown_option";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking the arrow icon of product information");
		
		if(language==LanguageOptions.English.toString()){
			this.xpath = "product_information text";
		}else{
			this.xpath = "cn_product_information text";
		}
		
		displayedProductDetails = getProperty(this.xpath, "text");
		if(displayedProductDetails != ""){
			reportValidation("Product details are displayed.", true, true);
		}else{
			reportError("Product details are not displayed.",true,null,false);
		}
	}
	
	/**
	 * This method will click arrow icon of the free gift option
	 * @throws ExecutionException
	 */
	public void clickFreeGiftOption(String userType) throws ExecutionException{
		this.xpath = "class_product_dropdown_option_free_gift product_dropdown_option";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking the arrow icon of FreeGift");
		
		if(userType=="anonymous"){
			this.xpath = "text_free_gift_product_name search_div_by_text";
		}else if(userType=="login"){			
			if(language==LanguageOptions.English.toString()){
				this.xpath = "text_free_gift_product_name_login search_div_by_text";
			}else{
				this.xpath = "cn_text_free_gift_product_name_login search_div_by_text";
			}
		}
		
		if(exists(this.xpath,30)){
			reportValidation("Free gift item is displayed.", true, true);
		}else{
			reportError("Free gift item is not displayed.",true,null,false);
		}
	}
	
	/**
	 * This method will click the add to cart button on the product details
	 * @throws ExecutionException
	 */
	public void clickAddToCartButton() throws ExecutionException{
		wait(5);
		this.xpath = "id_product_details_add_to_cart_button product_details_add_to_cart_btn";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking add to cart button");
		
		wait(5);
		this.xpath = "loading_icon loadingicon";
		this.xpath = "item_added_icon checkicon";
		if(exists(this.xpath,10)){
			reportValidation("loading icon is displayed", true, true);
			
			this.xpath = "item_added_icon checkicon";
			if(exists(this.xpath,10)){
				reportValidation("added item prompt is displayed", true, true);

			}else{
				reportError("added item prompt is not displayed.",true,null,false);

			}
		}else
		{
			reportError("loading icon is not displayed.",true,null,false);
		
		}
	}
	
	/**
	 * This method will click the cart icon and will direct us to the
	 		cart page and will also validate the products displayed
	 * @throws ExecutionException
	 */
	public void clickCartAndCheckProducts(String userType) throws ExecutionException{
		
		this.xpath = "class_cart_img_div cart_icon_img_portrait";
		if(!exists(this.xpath,3)){			
			this.xpath = "class_div_medium_up_header cart_icon_img";
		}
		wait(3);
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking cart icon");
		
		wait(5);
		
//		loginService.selectFlight();
//		wait(30);
		
		if(language==LanguageOptions.English.toString()){
			this.xpath = "text_cart_number_of_items search_h3_by_text";
		}else{
			this.xpath = "cn_text_cart_number_of_items search_h3_by_text";
		}

		logger.info(1, "Verifying if Cart page is displayed");
		logger.info(1, "Verifying if the quantity is 2");
		if(exists(this.xpath,20)){
			reportValidation("Cart page is displayed.", true, true);
			verify(this.xpath);
			reportValidation("Item quantity is 2", true, true);
			
			if(userType=="anonymous"){
				this.xpath =  "text_cart_free_gift_product search_h4_by_text";
			}else if(userType=="login"){
				this.xpath =  "text_cart_free_gift_product_login search_h4_by_text";
			}
			
			logger.info(1, "Verifying if free gift item is inclueded in cart");
			if(exists(this.xpath,20)){
				verify(this.xpath);
				reportValidation("Free gift item is included in cart.", true, true);
			}else{
				reportError("Free gift item is not included in cart.", true,null, false);
			}
		}
		else{
			reportError("Cart page did not displayed correctly",true,null,false);
			reportError("Item quantity is not 2",true,null,false);
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
		
	}
	
	public void postExecute() throws ExecutionException {
	
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	

}
