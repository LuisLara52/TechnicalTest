package com.credibanco.assessment.card.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationsCardRequest {

	@JsonProperty
	@NotNull
	@Size(min = 16, max = 19, message = "The PAN must have between 16 and 19 characters")
	private String pan;
	
	@JsonProperty
	@NotNull
	@Max(value=100, message="The validation num must be less than 100")
	private int validation_num;
	

	public int getValidation_num() {
		return validation_num;
	}

	public void setValidation_num(int validation_num) {
		this.validation_num = validation_num;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
}
