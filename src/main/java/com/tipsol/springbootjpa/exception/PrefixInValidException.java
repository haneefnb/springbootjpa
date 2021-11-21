package com.tipsol.springbootjpa.exception;

public class PrefixInValidException extends Throwable{
	private static final long serialVersionUID = 1L;

	public PrefixInValidException(String message) {
		super(message);
	}
}
