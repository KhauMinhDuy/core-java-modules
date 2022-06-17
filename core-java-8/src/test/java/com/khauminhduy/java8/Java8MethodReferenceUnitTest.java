package com.khauminhduy.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.khauminhduy.java_8_features.User;

public class Java8MethodReferenceUnitTest {

	private List<String> list;

	@Before
	public void init() {
		list = new ArrayList<>();
		list.add("One");
		list.add("OneAndOnly");
		list.add("Derek");
		list.add("Change");
		list.add("factory");
		list.add("justBefore");
		list.add("Italy");
		list.add("Italy");
		list.add("Thursday");
		list.add("");
		list.add("");
	}
	
	@Test
	public void checkStaticMethodReferences_whenWork_thenCorrect() {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		
		boolean isReal = users.stream().anyMatch(u -> User.isRealUser(u));
		boolean isRealRef = users.stream().anyMatch(User::isRealUser);
		
		assertTrue(isReal);
		assertTrue(isRealRef);
	}
	
	@Test
	public void checkInstanceMethodReferences_whenWork_thenCorrect() {
		User user = new User();
		boolean isLegalName = list.stream().anyMatch(user::isLegalName);
		assertTrue(isLegalName);
	}
	
	@Test
	public void checkParticularTypeReferences_whenWork_thenCorrect() {
		long count = list.stream().filter(String::isEmpty).count();
		assertEquals(2, count);
	}
	
	@Test
	public void checkConstructorReferences_whenWork_thenCorrect() {
		Stream<User> stream = list.stream().map(User::new);
		List<User> userList = stream.collect(Collectors.toList());
		assertEquals(list.size(), userList.size());
		assertTrue(userList.get(0) instanceof User);
	}

}
