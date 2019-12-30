package com.shopstore.app.CustomException;

public class BookingException extends RuntimeException {

    public BookingException() {

    }

    public BookingException(String msg) {
        super(msg);
    }
}
