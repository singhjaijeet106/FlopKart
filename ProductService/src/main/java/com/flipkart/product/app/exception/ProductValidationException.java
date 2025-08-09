package com.flipkart.product.app.exception;

public class ProductValidationException  extends RuntimeException {
    
    public ProductValidationException(String message) {
        super(message);
    }
}