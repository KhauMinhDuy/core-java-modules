package com.khauminhduy.java_8_features;

import java.util.Optional;

import lombok.Setter;

@Setter
public class OptionalAddress {

	private String street;
	
	public Optional<String> getStreet() {
		return Optional.ofNullable(this.street);
	}
	
}
