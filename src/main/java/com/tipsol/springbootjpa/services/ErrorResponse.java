package com.tipsol.springbootjpa.services;

import lombok.Data;

@Data
public class ErrorResponse {
	private String code;
	private String description;
	public ErrorResponse(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	
}
