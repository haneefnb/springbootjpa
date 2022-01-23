package com.tipsol.springbootjpa.commondto;

import lombok.Data;

@Data
public class SuccessResponse {
	private String code;
	private String message;
	public SuccessResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
