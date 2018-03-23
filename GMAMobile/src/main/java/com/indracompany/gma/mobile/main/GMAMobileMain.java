package com.indracompany.gma.mobile.main;

import indra.cilantrum.cilantrum.CilantrumMain;
import indra.cilantrum.cilantrum.Reusables;

import java.io.File;

import com.indracompany.gma.mobile.navigate.suite.NavigateMobileWebSuite;
import com.indracompany.gma.mobile.reusable.NavigateMobileWebReusable;

public class GMAMobileMain extends CilantrumMain{

	public GMAMobileMain(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		GMAMobileMain gmaMobileMain = new GMAMobileMain(System.getProperty("user.dir") + File.separator + "resources/cilantrumWebMobile.properties");
		gmaMobileMain.loadSuites();
		gmaMobileMain.loadReusables();
		gmaMobileMain.execute();
	}
	
	public void loadReusables() {
		Reusables.load("NavigateMobileWebReusable", new NavigateMobileWebReusable());
		//Reusables.load("NavigateMobileAppReusable", new NavigateMobileAppReusable());
		
	}

	public void loadSuites() {	
		loadSuite(new NavigateMobileWebSuite());
		//loadSuite(new NavigateMobileAppSuite());
		
	}
	
}
