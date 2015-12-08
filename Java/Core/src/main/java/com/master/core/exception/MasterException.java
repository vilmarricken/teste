package com.master.core.exception;

public class MasterException extends Exception {

	private static final long serialVersionUID = -864526479777165767L;

	public MasterException() {
		super();
	}

	public MasterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MasterException(String message, Throwable cause) {
		super(message, cause);
	}

	public MasterException(String message) {
		super(message);
	}

	public MasterException(Throwable cause) {
		super(cause);
	}

}
