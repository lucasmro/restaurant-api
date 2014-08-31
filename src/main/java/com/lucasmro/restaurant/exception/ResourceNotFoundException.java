package com.lucasmro.restaurant.exception;

import java.io.Serializable;

public class ResourceNotFoundException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	public ResourceNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
}
