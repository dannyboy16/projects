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


public class PwPTransactionReusable extends CilantrumReusable{
	
	private String xpath="";
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
		
		boolean withLogin = Boolean.valueOf(getParameter("withLogin"));
		
		if(AirAsiaBDFWebMain.paymentType==PaymentTypes.Alipay.toString()){
			paymentService.changeCurrency(AirAsiaBDFWebMain.languageOption,AirAsiaBDFWebMain.paymentType);

		}
		
		if(withLogin){
			//clickLoginSignup();
			//inputLoginDetails();
			//clickLoginButton();
			//clickLoginButton();
			//selectFlight();
			loginService.login(); //PROINTES-3268 JAVE
		}else{
			
		}
		
		wait(8);
		inputPwpProductOnSearchField();
		clickSearchButton();
		clickChosenPwpProduct();
		clickAddToCartButton();
		clickCartAndCheckProducts();
		purchaseWithPurchaseSection();
		
		wait(5);
		paymentService.checkProductBeforeCheckout(withLogin,language);
		
		logger.info(1, "Click Checkout button.");
		String checkOutBtnXpath;
		if(language==LanguageOptions.English.toString()){
			checkOutBtnXpath = "text_checkout_button search_button_by_text";
		}else{
			checkOutBtnXpath = "cn_text_checkout_button search_button_by_text";
		}
		wait(3);
		hoverAndPushOn(checkOutBtnXpath);
		wait(10);
		//paymentService.inputPersonalDetails(language);
		
		//paymentService.inputCreditCardDetails(language);
		//paymentService.creditCardPayment();
		//wait(5);
		//paymentService.alipayPayment(language);
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
		logger.info(1, "Verifying if size selected is correct");
		
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
		String value, valueSelected;
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
	 		the value that will be used to find the product tagged as pwp
	 * @throws ExecutionException
	 */
	public void inputPwpProductOnSearchField() throws ExecutionException{
		String input = "";
		String inputted = "";
		this.xpath = "class_div_row_small_only_portrait search_input_portrait";
		if(!exists(this.xpath,3)){			
			this.xpath = "class_div_medium_up_header search_input";
		}	
		input = Global.getExcelData().get("PRODUCT_SEARCH");
		writeInto(this.xpath, input);
		logger.info(1, "Writing product marked as pwp on the search field...");
		
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
		if(!exists(this.xpath,3)){
			this.xpath = "class_div_medium_up_header search_input_btn";
		}
		wait(3);
		hoverAndPushOn(this.xpath);	
		logger.info(1, "Clicking search button");
		
		this.xpath = "id_product_name_pwp search_p_by_text";
		if(exists(this.xpath,10)){
			reportValidation("Product was displayed under search result.", true, true);				
		}
		else{
			reportError("Product was not displayed under search result.",true,null,false);
		}
	}
	
	/**
	 * This method will click image of the chosen
	 * 		 product tagged as pwp from the list of products displayed
	 * @throws ExecutionException
	 */
	public void clickChosenPwpProduct() throws ExecutionException{

		this.xpath = "id_product_name_pwp search_p_by_text";
		wait(3);
		hoverAndPushOn(this.xpath);
		logger.info(1, "Clicking chosen product image with size preference...");
		
		this.xpath = "id_product_details_add_to_cart_button product_details_add_to_cart_btn";
		if(exists(this.xpath,10)){
			reportValidation("Product detail page is displayed.", true, true);

		}else{
			reportError("Product detail page is not displayed.",true,null,false);

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
		logger.info(1, "Clicking Cart icon...");
		logger.info(1, "Verifying if cart page is displayed...");
		logger.info(1, "Verifying if pwp item is included in the cart");
		
		this.xpath =  "text_cart_pwp_product search_h4_by_text";
		if(exists(this.xpath,20)){
			verify(this.xpath);
			reportValidation("Cart page is displayed.", true, true);
			reportValidation("PwP item is included in cart.", true, true);
		}else{
			reportValidation("PwP item is not included in cart.", true, true);
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
	
	/**
	 * This method will checks the purchase with purchase section and will
	 *  add the item to cart
	 */
	public void purchaseWithPurchaseSection() throws ExecutionException{
		this.xpath = "text_cart_pwp_product_added search_p_by_text";
		logger.info(1, "Verifying if purchase with purchase section is displayed...");
		if(exists(this.xpath,10)){
			reportValidation("Purchase with purchase section is displayed", true, true);
			this.xpath = "pwpproduct_addtocart button";	
			wait(5);
			hoverAndPushOn(this.xpath);	
			logger.info(1, "Clicking add to cart button of pwp item");
		}else{
			reportError("Purchase with purchase section is not displayed",true,null,false);
		}
		
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
		
		if(language==LanguageOptions.English.toString()){
			this.xpath = "text_cart_number_of_items_pwp search_h3_by_text";
		}else{
			this.xpath = "cn_text_cart_number_of_items_pwp search_h3_by_text";
		}
		
		logger.info(1, "Verifying if quantity is increased");
		if(exists(this.xpath,10)){
			verify(this.xpath);
			reportValidation("Item quantity is increased.", true, true);
		}else{
			reportError("Item quantity is not increased.",true,null,false);
		}
		
		this.xpath = "text_cart_total_price search_div_by_text";
		logger.info(1, "Verifying if pwp item is added to total amount");
		if(exists(this.xpath,15)){
			verify(this.xpath);
			reportValidation("Purchase with purchase item is added to total amount.", true, true);
		}else{
			reportError("Purchase with purchase item is not added to total amount.",true,null,false);
		}
	}

	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

}
