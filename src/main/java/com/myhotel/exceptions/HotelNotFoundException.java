package com.myhotel.exceptions;

public class HotelNotFoundException extends RuntimeException {
	public HotelNotFoundException(String message) {
		super(message);
	}
}