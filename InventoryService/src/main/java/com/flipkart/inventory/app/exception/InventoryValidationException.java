package com.flipkart.inventory.app.exception;

public class InventoryValidationException extends RuntimeException{
	public InventoryValidationException(String message){
		super(message);
	}
}
