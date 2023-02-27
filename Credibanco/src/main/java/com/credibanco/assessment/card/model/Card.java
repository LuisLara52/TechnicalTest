package com.credibanco.assessment.card.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarjeta")
public class Card {
	@Id
	@Column(name="pan")
	private String pan;
	
	@Column(name="titular")
	private String titular;
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="validation_num")
	private int validationNum;
	
	@Column(name="estado")
	private String estado;
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

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

	public int getValidationNum() {
		return validationNum;
	}

	public void setValidationNum(int validationNum) {
		this.validationNum = validationNum;
	}
	
	
}
