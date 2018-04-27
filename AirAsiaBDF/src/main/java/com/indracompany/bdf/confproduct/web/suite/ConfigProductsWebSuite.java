package com.indracompany.bdf.confproduct.web.suite;

import javax.swing.JOptionPane;

import indra.cilantrum.cilantrum.CilantrumSuite;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.exception.ExecutionException;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.AccessTypes;
import com.indracompany.bdf.web.reusables.ConfigProductsTransactionReusable;

public class ConfigProductsWebSuite extends CilantrumSuite{
	
	public void loadTests() {
		if(AirAsiaBDFWebMain.accessType == AccessTypes.Login.toString()){
			loadTest("ConfigProductsWebLoginTest");
		}else{
			loadTest("ConfigProductsWebAnonymousTest");
		}	
		
	}

	public void postExecute() throws ExecutionException {
		
	}

	public void preExecute() throws ExecutionException {
		
	}
	
}