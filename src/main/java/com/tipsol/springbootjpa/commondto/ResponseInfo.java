package com.tipsol.springbootjpa.commondto;

import lombok.Data;

@Data
public class ResponseInfo {
	private ResponseType responseType;
	private String message;
	private Object reponseBody;
	
	public ResponseInfo(ResponseType responseType, String message, Object reponseBody) {
		this.responseType = responseType;
		this.message = message;
		this.reponseBody = reponseBody;
	}
	
	
}
