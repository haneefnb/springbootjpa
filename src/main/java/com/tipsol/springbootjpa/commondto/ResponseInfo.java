package com.tipsol.springbootjpa.commondto;

import lombok.Data;

@Data
public class ResponseInfo {
	private String responseType;
	private String message;
	private Object reponseBody;
	
	public ResponseInfo(String responseType, String message, Object reponseBody) {
		this.responseType = responseType;
		this.message = message;
		this.reponseBody = reponseBody;
	}
	
	
}
