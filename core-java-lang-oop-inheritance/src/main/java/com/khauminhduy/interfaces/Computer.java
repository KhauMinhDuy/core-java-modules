package com.khauminhduy.interfaces;

public class Computer implements Electronic{

	@Override
	public int getElectricityUse() {
		return 1000;
	}
	
}
