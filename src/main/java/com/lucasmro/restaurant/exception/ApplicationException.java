package com.lucasmro.restaurant.exception;

import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(String msg, Exception e) {
		super(msg, e);
	}
}
