package com.khauminhduy.interfaces.multiinheritance;

public interface Transform {
	
	void transform();

	default void printSpecs() {
		System.out.println("Transform Specification");
	}

}
