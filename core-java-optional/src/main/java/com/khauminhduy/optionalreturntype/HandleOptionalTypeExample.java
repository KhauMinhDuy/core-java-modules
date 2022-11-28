package com.khauminhduy.optionalreturntype;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HandleOptionalTypeExample {

	static Map<String, User> users = new HashMap<>();

	static {
		User user = new User(1, "DuyKhau");
		users.put(user.getFirstName(), user);
	}

	public static Optional<User> findUserByName(String name) {
		User user = users.get(name);
		return Optional.ofNullable(user);
	}

	public static void changeUserName(String oldFirstName, String newFirstName) {
		Optional<User> userOpt = findUserByName(oldFirstName);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setFirstName(newFirstName);
			System.out.println("user with name " + oldFirstName + " is changed to " + user.getFirstName());
		} else {
			System.out.println("user with name " + oldFirstName + " is not found.");
		}
	}

	public static void main(String[] args) {
		changeUserName("DuyKhau", "DuyKhau-new");
		changeUserName("user", "user-new");
	}

}
