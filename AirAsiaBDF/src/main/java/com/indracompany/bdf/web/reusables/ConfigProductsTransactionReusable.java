package com.indracompany.bdf.web.reusables;

import org.apache.commons.lang3.StringUtils;

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

public class ConfigProductsTransactionReusable extends CilantrumReusable{
	final Logger logger = new Logger(getClass().getSimpleName());
	private String xpath = "";
	private String language;
	LanguageService languageService = new LanguageService();
	PaymentService paymentService = new PaymentService();
	LoginService loginService = new LoginService(); //PROINTES-3268 JAVE
	
	private boolean withLogin;
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
			
		}
		
		inputProductSizePrefOnSearchField();
		clickSearchButton();
		clickChosenSizePrefProduct();
		selectSizePreferences();
		clickAddToCartButton();
		clickHomeLogo();
		inputProductColorPrefOnSearchField();
		clickSearchButton();
		clickChosenColorPrefProduct();
		selectColorPreferences();
		clickAddToCartButton();
		clickCartAndCheckProducts();
		
		wait(10);
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
		/*
		paymentService.inputPersonalDetails(language);
		
		paymentService.inputCreditCardDetails(language);
		paymentService.creditCardPayment();
		wait(5);
		//paymentService.alipayPayment();
		//wait(5);
		paymentService.orderVerification();
		*/

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
		String value = null;
		
		value = Global.getExcelData().get("FLIGHT_NAME");
		this.xpath = "id_flight_select search_component_by_id";
		selectOptions(this.xpath,value);
		logger.info(1, "Selecting flight...");

		if(exists(this.xpath, 3)){
			reportValidation("Flight is selected.", true, true);
			

		}else{
			reportError("Flight is not selected.",true,null,false);
		

		}

	}
	
	/**
	 * This method will fill up the search textfield with 
	 		the value that will be used to find the product with size preference  
	 * @throws ExecutionException
	 */
	public void inputProductSizePrefOnSearchField() throws ExecutionException{
		String input = "";
		String inputted = "";
		this.xpath = "class_div_row_small_only_portrait search_input_portrait";
		if(!exists(this.xpath,3)){			
			this.xpath = "class_div_medium_up_header search_input";
		}	
		input = Global.getExcelData().get("PRODUCT_CAT_SIZE_PREF");
		Global.getReportManager().setScreenshot(true);
		writeInto(this.xpath, input);
		logger.info(1, "Writing product with size preference on the search field...");
		
		inputted = getProperty(this.xpath, "value");
		if(input.equals(inputted)){
			reportValidation("Value is inputted", true, true);
			
		}else{
			reportError("Value is not inputted",true,null,false);
		
		}
		
	}
	
	/**
	 * This method will click the search button
	 * @throws ExecutionException
	 */
	public void clickSearchButton() throws ExecutionException{

		this.xpath = "class_div_row_small_only_portrait search_input_btn_portrait";
		
		wait(5);
		if(!exists(this.xpath,3)){
			this.xpath = "class_div_medium_up_header search_input_btn";
		}
		wait(3);
		hoverAndPushOn(this.xpath);	
		logger.info(1, "Clicking search button");
		
		this.xpath = "id_product_name_size_pref search_p_by_text";
		if(exists(this.xpath,10)){
			reportValidation("Product was displayed under search result.", true, true);		
			
		}
		else{
			this.xpath = "id_product_name_color_pref search_p_by_text";
			if(exists(this.xpath,10)){
				reportValidation("Product was displayed under search result.", true, true);		

			}
			else
			{
				reportError("Product was not displayed under search result.",true,null,false);
				
			}
			
		}
	}
	
	/**
	 * This method will click image of the chosen
	 * 		 product with size preference from the list of products displayed
	 * @throws ExecutionException
	 */	
	public void clickChosenSizePrefProduct() throws ExecutionException{

		this.xpath = "id_product_name_size_pref search_p_by_text";
		wait(3);
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking chosen product image with size preference...");
		
		this.xpath = "id_product_size_select search_component_by_id";
		if(exists(this.xpath,10)){
			reportValidation("Product detail page is displayed.", true, true);
			

		}else{
			reportError("ProduCC_NUMBERct detail page is not displayed.",true,null,false);
		

		}
		
	}
	
	/**
	 * This method will select the chosen size value on the 
	  			size select option in the product details
	 * @throws ExecutionException
	 */
	
	public void selectSizePreferences() throws ExecutionException{

		String value = "";
		String valueSelected = "";
		value = Global.getExcelData().get("PRODUCT_CAT_SIZE_PREF_VALUE");
		this.xpath = "id_product_size_select search_component_by_id";
		wait(3);
		selectOptions(this.xpath,value);
		logger.info(1, "Selecting chosen size from size select option");
		
		wait(10);
		String selectedOptionPath = "size_selected_value text";
		valueSelected = getProperty(selectedOptionPath, "text");
		if(value.equals(valueSelected)){
			reportValidation("Size is selected..", true, true);
			

		}else{
			reportError("Size is not selected.",true,null,false);
			

		}
	}
	
	/**
	 * This method will click the add to cart button on the product details
	 * @throws ExecutionException
	 */
	public void clickAddToCartButton() throws ExecutionException{
		
		this.xpath = "id_product_details_add_to_cart_button product_details_add_to_cart_btn";
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking add to cart button...");
		
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
	 * This method will click the logo of the website and will
	 		direct us to the homepage
	 * @throws ExecutionException
	 */
	
	public void clickHomeLogo() throws ExecutionException{
		this.xpath = "class_menu_icon search_img_by_class";
		
		if(!exists(this.xpath,3))
		{
			this.xpath = "class_website_logo_img_div website_logo_img_div";
			wait(2);
			hoverAndPushOn(this.xpath );
		}
		else
		{
			wait(2);
			hoverAndPushOn(this.xpath );
			
			this.xpath  = "id_menu_choices_home menu_choices_link";
			wait(2);
			hoverAndPushOn(this.xpath );
		}
		logger.info(1, "Clicking home logo...");
			
		wait(5);
		reportValidation("Big Duty Free website is displayed.", true, true);
		
	}
	
	/**
	 * This method will fill up the search textfield with 
	 		the value that will be used to find the product with color preference  
	 * @throws ExecutionException
	 */
	public void inputProductColorPrefOnSearchField() throws ExecutionException{
		String input = "";
		String inputted = "";
		this.xpath = "class_div_row_small_only_portrait search_input_portrait";
		if(!exists(this.xpath,3)){			
			this.xpath = "class_div_medium_up_header search_input";
		}	
		
		clearField(this.xpath);
		wait(2);
		input = Global.getExcelData().get("PRODUCT_CAT_COLOR_PREF");
		writeInto(this.xpath, input);
		logger.info(1, "Writing product with color preference on the search field...");
		
		inputted = getProperty(this.xpath, "value");
		if(input.equals(inputted)){
			reportValidation("Value is inputted", true, true);
		

		}else{
			reportError("Value is not inputted",true,null,false);
		

		}
		
	}
	
	
	/**
	 * This method will click image of the chosen
	 * 		 product with color preference from the list of products displayed
	 * @throws ExecutionException
	 */
	public void clickChosenColorPrefProduct() throws ExecutionException{

		this.xpath = "id_product_name_color_pref search_p_by_text";
		wait(3);
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking chosen product image with color preference...");
		
		this.xpath = "id_product_pic_color_pref_color_button product_color_pref_color_btn";
		if(exists(this.xpath,10)){
			reportValidation("Product detail page is displayed.", true, true);
			
		}else{
			reportError("Product detail page is not displayed.",true,null,false);
	

		}
		
	}
	
	
	/**
	 * This method will click the button of the chosen color on the product details
	 * @throws ExecutionException
	 */
	public void selectColorPreferences() throws ExecutionException{

		this.xpath = "id_product_pic_color_pref_color_button product_color_pref_color_btn";
		wait(3);		
		if(exists(this.xpath,10)){
			hoverAndPushOn(this.xpath);
			logger.info(1, "Clicking the chosen color...");
			
			reportValidation("Color is selected.", true, true);
		
			
		}else{
			reportError("Color is not selected.",true,null,false);
		
		}
		
	}
	
	/**
	 * This method will click the cart icon and will direct us to the
	 		cart page and will also validate the products displayed
	 * @throws ExecutionException
	 */
	public void clickCartAndCheckProducts() throws ExecutionException{
		
		this.xpath = "class_cart_img_div cart_icon_img_portrait";
		
		if(!exists(this.xpath,3)){			
			this.xpath = "class_div_medium_up_header cart_icon_img";
		}
		wait(3);
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking cart icon");
		
		this.xpath = "text_cart_product_size_pref_name search_h4_by_text";
		
		logger.info(1, "Verifying if cart page is displayed");
		logger.info(1, "Verifying if items are displayed");
		logger.info(1, "Veryfying if size selected is correct");
		if(exists(xpath,5)){
			reportValidation("Cart page is displayed.", true, true);
			reportValidation("Items are displayed.", true, true);
			reportValidation("Size selected is correct.", true, true);
			this.xpath = "text_cart_product_color_pref_name search_h4_by_text";
			
			logger.info(1, "Veryfying if size selected is correct");
			if(exists(xpath,5)){							
				reportValidation("Color selected is correct.", true, true);
			
			}else{		
				reportError("Color selected is not correct.",true,null,false);
			
			}
		}else{
			reportError("Items are not displayed correctly.",true,null,false);
			reportError("Size selected is not correct.",true,null,false);
			
			
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
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	

}
