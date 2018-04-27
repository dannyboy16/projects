package com.indracompany.bdf.endtoend.web.suite;

import com.indracompany.bdf.web.main.AirAsiaBDFWebMain;
import com.indracompany.bdf.web.model.AccessTypes;

import indra.cilantrum.cilantrum.CilantrumSuite;
import indra.cilantrum.exception.ExecutionException;

public class EndToEndWebSuite extends CilantrumSuite {

	public void preExecute() throws ExecutionException 
	{
		// TODO Auto-generated method stub
	}

	public void postExecute() throws ExecutionException 
	{
		// TODO Auto-generated method stub
	}
	
	public void loadTests() 
	{
		if(AirAsiaBDFWebMain.accessType == AccessTypes.Login.toString()){
			loadTest("EndToEndWebLoginTest");
		}else{
			loadTest("EndToEndWebAnonymousTest");
		}	
	}

}
