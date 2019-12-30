package com.shopstore.app.CustomException;

public class ZomatoApiCallException extends RuntimeException {

    public ZomatoApiCallException() {

    }

    public ZomatoApiCallException(String msg) {
        super(msg);
    }
}
