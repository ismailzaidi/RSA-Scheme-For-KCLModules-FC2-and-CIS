package com.rsa.encryptionscheme;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {

	private BigInteger p, q, message, e;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button calculateButton;

	@FXML
	private TextField textfieldE;

	@FXML
	private TextField textfieldMessage;

	@FXML
	private TextField textfieldP;

	@FXML
	private TextField textfieldQ;

	@FXML
	private WebView webview;

	@FXML
	void performCalculation(ActionEvent event) {
		boolean verifyP = textfieldP.getText() != null
				&& !textfieldP.getText().trim().isEmpty();
		boolean verifyQ = textfieldQ.getText() != null
				&& !textfieldQ.getText().trim().isEmpty();
		boolean verifyM = textfieldMessage.getText() != null
				&& !textfieldMessage.getText().trim().isEmpty();
		boolean verifyE = textfieldMessage.getText() != null;
		boolean global = verifyP && verifyQ && verifyM;
		String messageStr = textfieldMessage.getText();

		if (global) {
			p = new BigInteger(textfieldP.getText());
			q = new BigInteger(textfieldQ.getText());
			System.out.println(messageStr.contains("\\w+"));
			if(messageStr.matches("[A-Za-z]+")){
				System.out.println("Original String:  "+messageStr);
				byte[] strByte = messageStr.getBytes();
				message =new BigInteger(strByte);
				
				byte[] decryptByte = message.toByteArray();
				String original = decryptByte.toString();
				System.out.println("Original String:  "+original);
			}else{
				message = new BigInteger(textfieldMessage.getText());
			}
			e = new BigInteger("0");
			if (verifyE) {
				e = new BigInteger(textfieldE.getText());
			}
			RSA scheme = new RSA(p, q, message, e);
			
			String Content = new HTMLGenerator(scheme).Generate();
			
			WebEngine engine = webview.getEngine();
			engine.loadContent(Content);

		}
		System.out.println("Working");
	}

	@FXML
	void initialize() {
		assert calculateButton != null : "fx:id=\"calculateButton\" was not injected: check your FXML file 'view.fxml'.";
		assert textfieldE != null : "fx:id=\"textfieldE\" was not injected: check your FXML file 'view.fxml'.";
		assert textfieldMessage != null : "fx:id=\"textfieldMessage\" was not injected: check your FXML file 'view.fxml'.";
		assert textfieldP != null : "fx:id=\"textfieldP\" was not injected: check your FXML file 'view.fxml'.";
		assert textfieldQ != null : "fx:id=\"textfieldQ\" was not injected: check your FXML file 'view.fxml'.";
		assert webview != null : "fx:id=\"webview\" was not injected: check your FXML file 'view.fxml'.";
		
		textfieldP.setText("47");
		textfieldQ.setText("71");
		textfieldE.setText("79");
		textfieldMessage.setText("688");
	}

}
