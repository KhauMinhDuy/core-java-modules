package com.khauminhduy.interfaces;

public interface Electronic {

	String LED = "LED";

	int getElectricityUse();

	static boolean isEnergyEfficient(String electronicType) {
		if (electronicType.equals(LED)) {
			return true;
		}
		return false;
	}

	default void printDescription() {
		System.out.println("Electronic Description");
	}

}
