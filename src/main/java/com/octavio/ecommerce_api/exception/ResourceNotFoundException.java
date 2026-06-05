package com.octavio.ecommerce_api.exception;

public class ResourceNotFoundException
        extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
