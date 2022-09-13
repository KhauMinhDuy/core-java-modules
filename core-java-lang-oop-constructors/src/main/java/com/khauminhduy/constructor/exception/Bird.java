package com.khauminhduy.constructor.exception;

public class Bird extends Animal {

	public Bird() throws InstantiationException {
		super();
	}

	public Bird(String id, int age) {
		super(id, age);
	}
	
}
