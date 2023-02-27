package com.credibanco.assessment.card.utils;

public enum StatusCodeEnum {
	
	C00("00","Exitoso"),
	C01("01","Fallido"),
	CU00("00","Éxito"),
	CU01("01","Tarjeta no existe"),
	CU02("02","Número de validación inválido"),
	CD00("00","Se ha eliminado la tarjeta"),
	CD01("01","No se ha eliminado la tarjeta"),
	CC00("00","Tarjeta encontrada"),
	CC01("01","No se ha encontrado la tarjeta"),
	T00("00","Compra exitosa"),
	T01("01","Tarjeta no existe"),
	T02("02","Tarjeta no enrolada"),
	TC00("00","Compra anulada"),
	TC01("01","Numero de referencia inválido"),
	TC02("01","No se puede anular transacción");
	
	private String code;
	private String description;
	
	StatusCodeEnum (String code, String description) {
		this.code=code;
		this.description=description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
}
