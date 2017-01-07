package com.java.sravan.WS_RSMessenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String errorDocumentaion;
//As this bean has to be converted to json for reponse this constructor is needed
	public ErrorMessage() {
	}

	public ErrorMessage(String errorMessage, int errorCode, String errorDocumentaion) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorDocumentaion = errorDocumentaion;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDocumentaion() {
		return errorDocumentaion;
	}

	public void setErrorDocumentaion(String errorDocumentaion) {
		this.errorDocumentaion = errorDocumentaion;
	}




}
