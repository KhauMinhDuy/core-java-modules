package com.khauminhduy.callable;

import java.util.concurrent.Callable;

public class FactorialTask implements Callable<Integer> {

	private int number;

	public FactorialTask(int number) {
		this.number = number;
	}
	
	@Override
	public Integer call() throws Exception {
		int fact = 1;
		if(number < 0) {
			throw new IllegalArgumentException("Number must be positive");
		}
		for(int count = number; count > 1; count--) {
			fact *= count;
		}
		return fact;
	}

}
