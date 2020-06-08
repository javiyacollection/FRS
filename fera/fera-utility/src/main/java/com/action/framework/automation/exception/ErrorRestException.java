package com.action.framework.automation.exception;

public class ErrorRestException extends Exception {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private String errorText;
	private Long errorCode;

	public ErrorRestException(String message, Long errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.errorText = message;
	}

	public ErrorRestException(String message) {
		super(message);
		this.errorText = message;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public Long getErrorCode() {
		return errorCode;
	}

}
