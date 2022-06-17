package com.khauminhduy.java_8_features;

import java.util.Optional;

import lombok.Setter;

@Setter
public class OptionalUser {

	private OptionalAddress address;
	
	public Optional<OptionalAddress> getAddress() {
		return Optional.of(this.address);
	}
	
}
