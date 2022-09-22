package com.khauminhduy.cipher;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.Test;

public class AvailableCiphersUnitTest {
	
	private static final Logger logger = Logger.getLogger(AvailableCiphersUnitTest.class.getName());

	@Test
	public void whenGetServices_thenGetAllCipherAlgorithms() {
		for(Provider provider : Security.getProviders()) {
			for(Provider.Service service : provider.getServices()) {
				logger.info(service.getAlgorithm());
			}
		}
	}

	@Test
	public void whenGetServicesWithFilter_thenGetAllCompatibleCipherAlgorithms() {
		List<String> algorithms = Arrays.stream(Security.getProviders())
			.flatMap(provider -> provider.getServices().stream())
			.filter(service -> "Cipher".equals(service.getType()))
			.map(Provider.Service::getAlgorithm)
			.collect(Collectors.toList());
		algorithms.forEach(logger::info);
	}

}
