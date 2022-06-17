package com.khauminhduy.java_8_features;

import java.util.Optional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

	private String name;

	private Address address;

	public User(String name) {
		this.name = name;
	}

	public static boolean isRealUser(User user) {
		return true;
	}

	public String getOrThrow() {
		String value = null;
		Optional<String> valueOpt = Optional.ofNullable(value);
		return valueOpt.orElseThrow(CustomException::new).toUpperCase();
	}

	public boolean isLegalName(String name) {
		return name.length() > 3 && name.length() < 16;
	}

}
