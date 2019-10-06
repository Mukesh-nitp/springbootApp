package com.salaj.app.salajwebapp.CustomException;

public class BookingException extends RuntimeException {

    public BookingException() {

    }

    public BookingException(String msg) {
        super(msg);
    }
}
