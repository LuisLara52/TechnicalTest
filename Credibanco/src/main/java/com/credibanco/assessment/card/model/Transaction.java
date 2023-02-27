package com.credibanco.assessment.card.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transacciones")
public class Transaction {

	@Id
	@Column(name = "reference_number")
	private String referenceNumber;

	@Column(name = "total_compra")
	private double total_compra;

	@Column(name = "direccion_compra")
	private String direccion_compra;

	@Column(name = "estado")
	private String estado;

	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "pan")
	private String pan;
	

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getReference_number() {
		return referenceNumber;
	}

	public void setReference_number(String reference_number) {
		this.referenceNumber = reference_number;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}
