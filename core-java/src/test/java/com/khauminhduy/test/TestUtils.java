package com.khauminhduy.test;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Properties;

import org.junit.Test;

public class TestUtils {

	@Test
	public void test() throws UnknownHostException {
		Properties properties = System.getProperties();
		System.out.println(properties);
		System.out.println(Inet4Address.getLocalHost().getHostAddress());
	}
	
}
