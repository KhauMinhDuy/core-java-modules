package com.khauminhduy.future;

import java.util.concurrent.RecursiveTask;

public class FactorialSquareCalculator extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private final Integer n;

	public FactorialSquareCalculator(Integer n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		if (n <= 1) {
			return n;
		}

		FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);

		calculator.fork();

		return n * n + calculator.join();
	}

}
