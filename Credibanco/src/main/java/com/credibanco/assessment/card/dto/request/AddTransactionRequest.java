package com.credibanco.assessment.card.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTransactionRequest {
	
	@JsonProperty
	@NotNull
	@Length(min= 6, max=6, message="the reference number size must be 6 characters")
	private String reference_number;
	
	@JsonProperty
	@NotNull
	private String pan;
	
	@JsonProperty
	@NotNull
	private double total_compra;
	
	@JsonProperty
	private String direccion_compra;

	public String getReference_number() {
		return reference_number;
	}

	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getTotal_compra() {
		return total_compra;
	}

	public void setTotal_compra(Double total_compra) {
		this.total_compra = total_compra;
	}

	public String getDireccion_compra() {
		return direccion_compra;
	}

	public void setDireccion_compra(String direccion_compra) {
		this.direccion_compra = direccion_compra;
	}
	
}
