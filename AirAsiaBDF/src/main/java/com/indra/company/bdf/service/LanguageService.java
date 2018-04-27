package com.indra.company.bdf.service;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.LanguageOptions;

import indra.cilantrum.cilantrum.CilantrumReusable;
import indra.cilantrum.cilantrum.Cilantrum.Global;
import indra.cilantrum.exception.ExecutionException;

public class LanguageService extends CilantrumReusable{
	
	String language = AirAsiaBDFWebMain.languageOption;
	
	public void selectLanguage() throws ExecutionException 
	{
		hoverAndPushOn("language_arrow icon");
		wait(3);
		hoverAndPushOn("zh_language link");
		wait(5);
	}
	
	public String languageTranslation(String translation) {
		
		if(language==LanguageOptions.English.toString()){
			return translation;
		}else if(language==LanguageOptions.Chinese.toString()) {
			return "cn_" + translation;
		}else {
			return null;
		}
	}
	
	public String languageHeader(String header) {

		if(language==LanguageOptions.English.toString()) {
			return Global.getExcelData().get(header).trim();
		}else if(language==LanguageOptions.Chinese.toString()){
			return Global.getExcelData().get(header + "_CHINESE").trim();
		}else {
			return null;
		}		
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

	@Override
	protected void loadParameters() {
		// TODO Auto-generated method stub
		
	}
}
