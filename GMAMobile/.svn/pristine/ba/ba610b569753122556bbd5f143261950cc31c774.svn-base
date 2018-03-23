package com.indracompany.gma.mobile.reusable;

import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.exception.ExecutionException;

public class NavigateMobileAppReusable extends CilantrumReusable{

	public void preExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void execute() throws ExecutionException {
		// TODO Auto-generated method stub
		wait(10);
		
		checkHomePage();
		
		swipe("vertical",70);
		wait(5);
		swipe("vertical",-90);
		
		checkYouScoop();
		
		checkFaqs();
		
		addFeedPicture();
		addFeedVideo();
//		checkContactUs();
		
	}
	

	public void checkHomePage() throws ExecutionException{
		//check menu
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			reportValidation("Main menu is displayed", true, true);
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//check main logo
		if(exists("resourceid_main_logo search_imageview_by_resourceid", 50)){
			reportValidation("Main logo is displayed", true, true);
		}else{
			reportError("Main logo is not displayed",true,null,false);
		}
		
		//check camera icon
		if(exists("resourceid_camera_icon search_button_by_resourceid", 50)){
			reportValidation("Camera icon is displayed", true, true);
		}else{
			reportError("Camera icon is not displayed",true,null,false);
		}
		
		//check image list
		if(exists("resourceid_home_image_list search_linearlayout_by_resourceid", 50)){
			reportValidation("Image list is displayed", true, true);
		}else{
			reportError("Image list is not displayed",true,null,false);
		}
	}
	
	public void checkYouScoop() throws ExecutionException{
		//click main menu
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//wait(2);
		//click what is youscoop menu
		if(exists("index_main_menu_whatisyouscoop search_relativelayout_by_index", 50)){
			pushOn("index_main_menu_whatisyouscoop search_relativelayout_by_index");
		}else{
			reportError("youscoops menu is not displayed",true,null,false);
		}
		
		//check if header is displayed
		if(exists("resourceid_youscooppage_header search_textview_by_resourceid",50)){
			reportValidation("Youscoop Header info is displayed", true, true);
		}else{
			reportError("Youscoop Header info is not displayed",true,null,false);
		}
		
		//check if info is displayed
		if(exists("resourceid_youscooppage_message_body search_textview_by_resourceid",50)){
			reportValidation("Image list is displayed", true, true);
		}else{
			reportError("Image list is not displayed",true,null,false);
		}
	}
	
	public void checkFaqs() throws ExecutionException{
		//click main menu
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//click faqs menu
		if(exists("index_main_menu_faqs search_relativelayout_by_index", 50)){
			pushOn("index_main_menu_faqs search_relativelayout_by_index");
		}else{
			reportError("Faqs menu is not displayed",true,null,false);
		}
		
		//check if header is displayed
		if(exists("index_faqspage_header search_textview_by_resourceid",50)){
			reportValidation("FAQS Header info is displayed", true, true);
		}else{
			reportError("FAQS Header info is not displayed",true,null,false);
		}
		
		//click faqs first dropdown value
		if(exists("index_faqspage_first_dropdown search_relativelayout_by_index", 50)){
			pushOn("index_faqspage_first_dropdown search_relativelayout_by_index");
		}else{
			reportError("Faqs selected value is not displayed",true,null,false);
		}
		
		//check if dropdown body message value is displayed
		if(exists("text_faqspage_first_dropdown_body_message search_textview_by_text",50)){
			reportValidation("Dropdown body message value is displayed", true, true);
		}else{
			reportError("Dropdown body message value is not displayed",true,null,false);
		}
		
		//click faqs first dropdown value
		if(exists("index_faqspage_first_dropdown search_relativelayout_by_index", 50)){
			pushOn("index_faqspage_first_dropdown search_relativelayout_by_index");
		}else{
			reportError("Faqs selected value is not displayed",true,null,false);
		}
		
		//click main menu
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//click feeds menu
		if(exists("index_main_menu_feeds search_relativelayout_by_index", 50)){
			pushOn("index_main_menu_feeds search_relativelayout_by_index");
		}else{
			reportError("Feeds menu is not displayed",true,null,false);
		}
		
		//wait(5);
	}
	
	public void addFeedPicture() throws ExecutionException{
		//click camera icon
		if(exists("resourceid_camera_icon search_button_by_resourceid", 50)){
			pushOn("resourceid_camera_icon search_button_by_resourceid");
		}else{
			reportError("Camera icon is not displayed",true,null,false);
		}
		
		//click photo icon
		if(exists("resourceid_addfeedpage_photo_icon search_imageview_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_photo_icon search_imageview_by_resourceid");
		}else{
			reportError("Photo icon is not displayed",true,null,false);
		}
		
		//click use camera button
		if(exists("resourceid_addfeedpage_usecamera_button search_button_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_usecamera_button search_button_by_resourceid");
		}else{
			reportError("Use camera button is not displayed",true,null,false);
		}
		
		//click camera submit
		if(exists("camera icon", 50)){
			pushOn("camera icon");
		}else{
			reportError("Camera submit is not displayed",true,null,false);
		}
		
		//click ok
		if(exists("resourceid_addfeedpage_ok_textview search_textview_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_ok_textview search_textview_by_resourceid");
		}else{
			reportError("Ok textview is not displayed",true,null,false);
		}
//		
//		//check if image is displayed
//		if(exists("resourceid_addfeedpage_displayed_image search_imageview_by_resourceid",50)){
//			reportValidation("Selected image is displayed", true, true);
//		}else{
//			reportError("Selected image is not displayed",true,null,false);
//		}
		
		//wait(2);
		//write to details edittext
		if(exists("resourceid_addfeedpage_details_edittext search_edittext_by_resourceid",50)){
			writeInto("resourceid_addfeedpage_details_edittext search_edittext_by_resourceid", Global.getExcelData().get("ADD_FEED_DETAILS_VALUE"));
		}else{
			reportError("Details EditText is not displayed",true,null,false);
		}
		
		//write to Contact Details edittext
		if(exists("resourceid_addfeedpage_contactdetails_edittext search_edittext_by_resourceid",50)){
			writeInto("resourceid_addfeedpage_contactdetails_edittext search_edittext_by_resourceid", Global.getExcelData().get("ADD_FEED_CONTACT_DETAILS_VALUE"));
		}else{
			reportError("Contact details EditText is not displayed",true,null,false);
		}
		//wait(2);
		//click back button
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//wait(5);
		
	}
	
	public void addFeedVideo() throws ExecutionException{
		//click camera icon
		if(exists("resourceid_camera_icon search_button_by_resourceid", 50)){
			pushOn("resourceid_camera_icon search_button_by_resourceid");
		}else{
			reportError("Camera icon is not displayed",true,null,false);
		}
		
		//click video icon
		if(exists("resourceid_addfeedpage_video_icon search_imageview_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_video_icon search_imageview_by_resourceid");
		}else{
			reportError("Video icon is not displayed",true,null,false);
		}
		
		//click use camera button
		if(exists("resourceid_addfeedpage_usecamera_button search_button_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_usecamera_button search_button_by_resourceid");
		}else{
			reportError("Use camera button is not displayed",true,null,false);
		}
		
		//click start vid
		if(exists("startvid icon", 50)){
			pushOn("startvid icon");
		}else{
			reportError("Start vid icon is not displayed",true,null,false);
		}
		
		//wait(3);
		//click Stop vid
		if(exists("stopvid icon", 50)){
			pushOn("stopvid icon");
		}else{
			reportError("Stop vid icon is not displayed",true,null,false);
		}
				
		//click ok
		if(exists("resourceid_addfeedpage_ok_textview search_textview_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_ok_textview search_textview_by_resourceid");
		}else{
			reportError("Ok textview is not displayed",true,null,false);
		}
		
		//click play video
		if(exists("resourceid_addfeedpage_play_selected_video search_imageview_by_resourceid", 50)){
			pushOn("resourceid_addfeedpage_play_selected_video search_imageview_by_resourceid");
		}else{
			reportError("Play video is not displayed",true,null,false);
		}
		
		//write to details edittext
		if(exists("resourceid_addfeedpage_details_edittext search_edittext_by_resourceid",50)){
			writeInto("resourceid_addfeedpage_details_edittext search_edittext_by_resourceid", Global.getExcelData().get("ADD_FEED_DETAILS_VALUE"));
		}else{
			reportError("Details EditText is not displayed",true,null,false);
		}
		
		//write to Contact Details edittext
		if(exists("resourceid_addfeedpage_contactdetails_edittext search_edittext_by_resourceid",50)){
			writeInto("resourceid_addfeedpage_contactdetails_edittext search_edittext_by_resourceid", Global.getExcelData().get("ADD_FEED_CONTACT_DETAILS_VALUE"));
		}else{
			reportError("Contact details EditText is not displayed",true,null,false);
		}
		
		wait(2);
		//click back button
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		//wait(5);
	}
	
	public void checkContactUs() throws ExecutionException{
		//click main menu
		if(exists("resourceid_main_menu search_imageview_by_resourceid", 50)){
			pushOn("resourceid_main_menu search_imageview_by_resourceid");
		}else{
			reportError("Main menu is not displayed",true,null,false);
		}
		
		wait(2);
		
		//click contact us menu
		if(exists("index_main_menu_contactus search_relativelayout_by_index", 50)){
			pushOn("index_main_menu_contactus search_relativelayout_by_index");
		}else{
			reportError("Contact us menu is not displayed",true,null,false);
		}
		
		//click youscoop email
		if(exists("resourceid_contactus_youscoopemail_button search_button_by_resourceid", 50)){
			pushOn("resourceid_contactus_youscoopemail_button search_button_by_resourceid");
		}else{
			reportError("Youscoop email is not displayed",true,null,false);
		}
	}
	
	public void postExecute() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadParameters() {
		// TODO Auto-generated method stub
		
	}

}
