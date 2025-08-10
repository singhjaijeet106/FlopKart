package com.flipkart.inventory.app.exception;

public class InventoryNotFoundException extends RuntimeException{
	public InventoryNotFoundException(String message){
		super(message);
	}
}
