package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionDTO {

	@JsonProperty
	private String reference_number;
	
	@JsonProperty
	private double total_compra;
	
	@JsonProperty
	private String direccion_compra;
	
	@JsonProperty
	private String estado;
	
	@JsonProperty
	private String fecha;

	public String getReference_number() {
		return reference_number;
	}

	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}

	public double getTotal_compra() {
		return total_compra;
	}

	public void setTotal_compra(double total_compra) {
		this.total_compra = total_compra;
	}

	public String getDireccion_compra() {
		return direccion_compra;
	}

	public void setDireccion_compra(String direccion_compra) {
		this.direccion_compra = direccion_compra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
