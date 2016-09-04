package edu.npu.hotelapp.exceptions;

public class DuplicateResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(String msg) {
		super(msg);
	}

}
