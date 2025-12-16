package com.callmextrm.INVENTORY.exception;

public class Discontinued extends RuntimeException {
    public Discontinued(String message) {
        super(message);
    }
}
