package com.example.b2c.net.util;

public class NetException extends Exception {
	private static final long serialVersionUID = 1L;

	public NetException() {
		super();
	}

	public NetException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NetException(String detailMessage) {
		super(detailMessage);
	}

	public NetException(Throwable throwable) {
		super(throwable);
	}
}
