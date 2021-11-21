package com.tipsol.springbootjpa.commondto;

import lombok.Data;

@Data
public class ErrorInfo {
	private String code;
	private Object messages;

	public ErrorInfo(String code, Object messages) {
		this.code = code;
		this.messages = messages;
	}
	
	
	
}
