package com.flipkart.product.app.exception;

public class ProductNotFoundException extends RuntimeException{
		public ProductNotFoundException(String message) {
			super(message);
		}
}
