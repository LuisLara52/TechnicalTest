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
	private String pan;
	
	@JsonProperty
	private String validationNum;
	
	public CardOperationsDTO(String responseCode, String message, String pan, String validationNum) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.pan = pan;
		this.validationNum = validationNum;
	}
	
	public CardOperationsDTO() {
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
