package com.credibanco.assessment.card.utils;

public enum StatusCodeEnum {
	
	C00("00","Exitoso"),
	C01("01","Fallido");
	
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
