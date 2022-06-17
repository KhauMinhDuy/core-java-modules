package com.khauminhduy.java8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.khauminhduy.java_8_features.Vehicle;
import com.khauminhduy.java_8_features.VehicleImpl;

public class Java8DefaultStaticIntefaceMethodsUnitTest {

	@Test
	public void callStaticInterfaceMethdosMethods_whenExpectedResults_thenCorrect() {
		Vehicle vehicle = new VehicleImpl();
		String overview = vehicle.getOverview();
		long[] startPosition = vehicle.startPosition();
		
		assertEquals("ATV made by N&F Vehicles", overview);
		assertEquals(23, startPosition[0]);
		assertEquals(15, startPosition[1]);
		
		String producer = Vehicle.producer();
		assertEquals("N&F Vehicles", producer);
	}
	
}
