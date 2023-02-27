package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CardOperationsDTO {
	@JsonProperty
	private String responseCode;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private String validationNum;
	
	@JsonProperty
	private String pan;
	
	public CardOperationsDTO() {
		
	}
			
	public CardOperationsDTO(String responseCode, String message,String validationNum, String pan) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.validationNum = validationNum;
		this.pan = pan;
	}
	
	public CardOperationsDTO(String responseCode, String message, String pan) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.pan = pan;
	}
	
	public CardOperationsDTO(String responseCode, String message) {
		super();
		this.responseCode = responseCode;
		this.message = message;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getValidationNum() {
		return validationNum;
	}

	public void setValidationNum(String validationNum) {
		this.validationNum = validationNum;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
