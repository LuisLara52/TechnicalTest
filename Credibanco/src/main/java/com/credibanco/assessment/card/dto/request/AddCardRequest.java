package com.credibanco.assessment.card.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddCardRequest {

	@JsonProperty
	@NotNull
	@Size(min = 16, max = 19, message = "The PAN must have between 16 and 19 characters")
	private String pan;
	
	@JsonProperty
	private String titular;
	
	@JsonProperty
	@Size(min = 10, max = 15, message = "The 'cedula' must have between 10 and 15 characters")
	private String cedula;
	
	@JsonProperty
	private String tipo;
	
	@JsonProperty
	@Length(min = 10, max = 10, message = "The telefono size must have 10 characters")
	private String telefono;

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
