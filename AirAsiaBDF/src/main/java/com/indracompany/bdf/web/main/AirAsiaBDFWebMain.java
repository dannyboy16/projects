package com.indracompany.bdf.web.main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import javax.swing.JCheckBox; //PROINTES-3268 JAVE

import org.apache.commons.io.IOUtils;
import org.apache.poi.util.SystemOutLogger;

import com.indracompany.bdf.confproduct.web.suite.ConfigProductsWebSuite;
import com.indracompany.bdf.endtoend.web.suite.EndToEndWebSuite;
import com.indracompany.bdf.web.model.TestSuites;
import com.indracompany.bdf.web.model.AccessTypes;
import com.indracompany.bdf.web.model.LanguageOptions; //PROINTES-3268 JAVE
import com.indracompany.bdf.web.model.PaymentTypes;
import com.indracompany.bdf.web.reusables.AddToWishListWebReusable;
import com.indracompany.bdf.feedback.customerservicesection.web.suite.FeedbackCustomerServiceSectionWebSuite;
import com.indracompany.bdf.feedback.mainsection.web.suite.FeedbackMainSectionWebSuite;
import com.indracompany.bdf.freegift.web.suite.FreeGiftWebSuite;
import com.indracompany.bdf.pwp.web.suite.PwPWebSuite;
import com.indracompany.bdf.web.reusables.ConfigProductsTransactionReusable;
import com.indracompany.bdf.web.reusables.EndToEndWebTransactionReusable;
import com.indracompany.bdf.web.reusables.FeedbackCustomerServiceSectionTransactionReusable;
import com.indracompany.bdf.web.reusables.FeedbackMainSectionTransactionReusable;
import com.indracompany.bdf.wishlist.web.suite.AddToWishListSuite;
import com.indracompany.bdf.web.reusables.FreeGiftTransactionReusable;
import com.indracompany.bdf.web.reusables.PwPTransactionReusable;

import indra.cilantrum.cilantrum.CilantrumMain;
import indra.cilantrum.cilantrum.Reusables;
import indra.cilantrum.util.Logger;
public class AirAsiaBDFWebMain extends CilantrumMain{

	public AirAsiaBDFWebMain(String path){	
		super(path);
	}
	
	//private enum TestSuites {EndToEnd,ConfigProducts,FreeGift,AddToWishList,PwP}
	//private enum AccessTypes{Login,Anonymous}
	
	static JFrame frame;
	static JPanel titlePanel;
	public static JTextArea textAreaLog;
	static JLabel labelTitle;
	static JScrollPane scrollPane;
	static JComboBox cbTestSuites;
	static JComboBox cbAccessTypes;
	static JLabel lblTestSuites;
	static JLabel lblAccessTypes;
	static JButton btnSubmit;
	static JLabel lblPaymentTypes;
	static JComboBox cbPaymentTypes;
	static JLabel lblSpecialRequest;
	static JCheckBox chkSpecialRequest;
	static JLabel lblLanguageOptions;
	static JComboBox cbLanguageOptions;
	static JLabel lblFeedbackEmail;
	static JCheckBox chkFeedbackEmail;
	
	
	static AirAsiaBDFWebMain execution;
	public static String testSuiteType;
	public static String accessType;
	public static String languageOption;
	public static String paymentType;
	public static boolean specialRequest;
	public static boolean feedbackEmail;
	
	public static void main(final String[] args) throws Exception{
//		String propPath = System.getProperty("user.dir") + File.separator + "resources/devices/Nexus7.properties";
//		FileInputStream in = new FileInputStream(propPath);
//		Properties props = new Properties();
//		props.load(in);
//		in.close();
//		
//		FileOutputStream out = new FileOutputStream(propPath);
//		props.setProperty("main_path", "C:/Users/jabilar.INDRA/AppData/Roaming/npm/node_modules/appium/lib/main.js");
//		props.store(out, null);
//		out.close();
		
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e) {
            e.printStackTrace();
        }
		
		frame = new JFrame("BDF Web Automation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(750, 380);
		frame.setSize(590, 650);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(220,220,220));
		frame.setResizable(false);
		
		///title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0,50,255));
		
		labelTitle= new JLabel("BDF Web Automation");
		labelTitle.setFont(new Font("Calibri", Font.PLAIN, 40));

		titlePanel.add(labelTitle);
		
		
		//console log
		textAreaLog = new JTextArea(50,50);
		textAreaLog.setEditable(false);
		textAreaLog.setLineWrap(true);
		scrollPane = new JScrollPane(textAreaLog);		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DefaultCaret caret = (DefaultCaret)textAreaLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		//testsuiteoption
		lblTestSuites = new JLabel("Test Suite");
		lblTestSuites.setFont(new Font("Calibri", Font.BOLD, 16));
		cbTestSuites = new JComboBox(TestSuites.values());    
		cbTestSuites.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		//accessTypeoption
		lblAccessTypes = new JLabel("Access Type");
		lblAccessTypes.setFont(new Font("Calibri", Font.BOLD, 16));
		cbAccessTypes = new JComboBox(AccessTypes.values());    
		cbAccessTypes.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		//SubmitButton
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(255,0,127));
		
		//PaymentTypes
		lblPaymentTypes = new JLabel("Payment Type");
		lblPaymentTypes.setFont(new Font("Calibri", Font.BOLD, 16));
		cbPaymentTypes = new JComboBox(PaymentTypes.values());    
		cbPaymentTypes.setFont(new Font("Calibri", Font.PLAIN, 16));

		//languageOption
		lblLanguageOptions = new JLabel("Language Option");
		lblLanguageOptions.setFont(new Font("Calibri", Font.BOLD, 16));
		cbLanguageOptions = new JComboBox(LanguageOptions.values());    
		cbLanguageOptions.setFont(new Font("Calibri", Font.PLAIN, 16));
				
		//specialRequestCheckBox
		lblSpecialRequest = new JLabel("Special Request");
		lblSpecialRequest.setFont(new Font("Calibri", Font.BOLD, 16));
		chkSpecialRequest = new JCheckBox("");
		chkSpecialRequest.setFont(new Font("Calibri", Font.BOLD, 16));
		
		//feedbackEmailCheckbox
		lblFeedbackEmail = new JLabel("Feedback Email");
		lblFeedbackEmail.setFont(new Font("Calibri", Font.BOLD, 16));
		chkFeedbackEmail = new JCheckBox("");
		chkFeedbackEmail.setFont(new Font("Calibri", Font.BOLD, 16));
		chkFeedbackEmail.setEnabled(false);
			
		//Setting of bounds
		titlePanel.setBounds(0,0,600,50);
		scrollPane.setBounds(20,420,543,180);
		lblTestSuites.setBounds(20,70,350,40); 
		cbTestSuites.setBounds(200,70,363,40); 
		lblAccessTypes.setBounds(20,120,350,40); 
		cbAccessTypes.setBounds(200,120,363,40); 
		lblPaymentTypes.setBounds(20,170,363,40);
		cbPaymentTypes.setBounds(200,170,363,40);
		lblLanguageOptions.setBounds(20,220,350,40);
		cbLanguageOptions.setBounds(200,220,363,40);
		lblSpecialRequest.setBounds(20,270,350,40);
		chkSpecialRequest.setBounds(200,278,20,20);
		lblFeedbackEmail.setBounds(20,320,350,40);
		chkFeedbackEmail.setBounds(200,328,20,20);
		btnSubmit.setBounds(20,370,543,40);

		//adding to frame
		frame.add(titlePanel);
		frame.add(scrollPane);    
		frame.add(lblTestSuites);  
		frame.add(cbTestSuites);  
		frame.add(lblAccessTypes);  
		frame.add(cbAccessTypes);   
		frame.add(btnSubmit); 	
		frame.add(lblPaymentTypes);	
		frame.add(cbPaymentTypes);
		frame.add(lblLanguageOptions);
		frame.add(cbLanguageOptions);
		frame.add(lblSpecialRequest);
		frame.add(chkSpecialRequest);
		frame.add(lblFeedbackEmail);
		frame.add(chkFeedbackEmail);

	    frame.setVisible(true);
	    	    
	    String path = System.getProperty("user.dir") + File.separator + "resources/cilantrum.properties";
	    execution = new AirAsiaBDFWebMain(path);

		execution.loadReusables();
		textAreaLog.append("Devices that can be used are configured at " + System.getProperty("user.dir") + File.separator + "resources\\cilantrum.properties\\devices" + "\n\n");
		textAreaLog.append("Application configuration is located at " + System.getProperty("user.dir") + File.separator + "resources\\cilantrum.properties" + "\n\n");	
		btnSubmit.addActionListener(new ActionListener() {	 
	        public void actionPerformed(ActionEvent e)
	        {
	        	
	        	testSuiteType = cbTestSuites.getSelectedItem().toString();
	        	accessType = cbAccessTypes.getSelectedItem().toString();
	        	specialRequest = chkSpecialRequest.isSelected();
	        	languageOption = cbLanguageOptions.getSelectedItem().toString();
	        	paymentType = cbPaymentTypes.getSelectedItem().toString();
	        	feedbackEmail = chkFeedbackEmail.isSelected();
	        	
	        	textAreaLog.append("Automation testing for " + testSuiteType + "-" + accessType + " is started." + "\n\n");
	        	textAreaLog.update(textAreaLog.getGraphics());
	        	
	        	try 
	        	{
		        	execution.loadSuites();
		        	execution.execute();
		        	
		        	textAreaLog.append("Automation testing for " + testSuiteType + "-" + accessType + " is finished." + "\n\n");
		        	textAreaLog.append("Log reports are successfully generated at"+ System.getProperty("user.dir") + File.separator + "logs" + "\n\n");
		        	textAreaLog.append("Screenshots of the steps are successfully generated at"+ System.getProperty("user.dir") + File.separator + "testReport\\img" + "\n\n");
		        	textAreaLog.update(textAreaLog.getGraphics());
	        	} 
	        	catch (Exception er) 
	        	{
	        		textAreaLog.append("Error encountered during the automation test." + "\n\n");
		        	textAreaLog.update(textAreaLog.getGraphics());

	        	}  	
	        	try 
	        	{
		        	textAreaLog.append("Application will automatically refresh after 10 secods."+ "\n\n");
		        	textAreaLog.update(textAreaLog.getGraphics());
	        	    Thread.sleep(10000);
	        	} 
	        	catch (InterruptedException err) 
	        	{
	        	   err.printStackTrace();
	        	}
	        	
	        	try {
					Runtime.getRuntime().exec("java -jar BDFWebTest.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	System.exit(0);
	        	
	        }
	    });    

		//Action Listener for TestSuite drop down
	    cbTestSuites.addActionListener(new ActionListener() {	 
	        public void actionPerformed(ActionEvent e)
	        {        	
	        	if(cbTestSuites.getSelectedItem().toString()==TestSuites.FeedbackMainSection.toString()){
	        		cbPaymentTypes.setSelectedIndex(PaymentTypes.valueOf(PaymentTypes.CreditCard.toString()).ordinal());
	        		cbPaymentTypes.setEnabled(false);
	        		chkSpecialRequest.setEnabled(true);
	        		chkFeedbackEmail.setEnabled(false);	        			        		

	        	}else if(cbTestSuites.getSelectedItem().toString()==TestSuites.FeedbackCustomerServiceSection.toString()){
	        		cbPaymentTypes.setSelectedIndex(PaymentTypes.valueOf(PaymentTypes.CreditCard.toString()).ordinal());
	        		cbPaymentTypes.setEnabled(false);
	        		chkSpecialRequest.setEnabled(false);
	        		chkFeedbackEmail.setEnabled(true);
	        		
	        	}else{
	        		cbPaymentTypes.setEnabled(true);
	        		chkSpecialRequest.setEnabled(true);
	        		chkFeedbackEmail.setEnabled(false);
	        	}

	        }
	        	
	    });    
	    
	    
	 
	 
//		AirAsiaBDFWebMain execution = new AirAsiaBDFWebMain();
		
//		execution.loadReusables();
//		
//		execution.loadSuites();
//
//		execution.execute();
	    	    
	}
	
	
	public void loadReusables() {
//		if(AirAsiaBDFWebMain.testSuiteType==TestSuites.ConfigProducts.toString()){
//			Reusables.load("ConfigProductsTransaction", new ConfigProductsTransactionReusable());
//		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.FreeGift.toString()){
//			Reusables.load("FreeGiftTransactionReusable", new FreeGiftTransactionReusable());
//		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.AddToWishList.toString()){
//			Reusables.load("PwPTransactionReusable", new PwPTransactionReusable());
//		}sa
		Reusables.load("EndToEndWebTransaction", new EndToEndWebTransactionReusable());
		Reusables.load("ConfigProductsTransaction", new ConfigProductsTransactionReusable());
		Reusables.load("FreeGiftTransactionReusable", new FreeGiftTransactionReusable());
		Reusables.load("AddToWishListWebReusable", new AddToWishListWebReusable());
		Reusables.load("PwPTransactionReusable", new PwPTransactionReusable());
		Reusables.load("FeedbackMainSectionTransactionReusable", new FeedbackMainSectionTransactionReusable());
		Reusables.load("FeedbackCustomerServiceSectionTransactionReusable", new FeedbackCustomerServiceSectionTransactionReusable());
	}

	public void loadSuites() {
		if(AirAsiaBDFWebMain.testSuiteType==TestSuites.ConfigProducts.toString()){
			loadSuite(new ConfigProductsWebSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.FreeGift.toString()){
			loadSuite(new FreeGiftWebSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.PwP.toString()){
			loadSuite(new PwPWebSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.EndToEnd.toString()){
			loadSuite(new EndToEndWebSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.AddToWishList.toString()){
			loadSuite(new AddToWishListSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.FeedbackMainSection.toString()){
			loadSuite(new FeedbackMainSectionWebSuite());
		}else if(AirAsiaBDFWebMain.testSuiteType==TestSuites.FeedbackCustomerServiceSection.toString()){
			loadSuite(new FeedbackCustomerServiceSectionWebSuite());
		}
		//loadSuite(new FeedbackMainSectionWebSuite());
		//loadSuite(new FeedbackCustomerServiceSectionWebSuite());
		//loadSuite(new EndToEndWebSuite());
		//loadSuite(new ConfigProductsWebSuite());
		//loadSuite(new FreeGiftWebSuite());
		//loadSuite(new AddToWishListSuite());
		//loadSuite(new PwPWebSuite());
		//load other suite i.e conf product, pwp, wish list, free gift
	}

	
	
}