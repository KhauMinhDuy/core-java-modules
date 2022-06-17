package com.khauminhduy.runnable;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.RandomUtils;

public class SimpleCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		return RandomUtils.nextInt(0, 100);
	}

}
