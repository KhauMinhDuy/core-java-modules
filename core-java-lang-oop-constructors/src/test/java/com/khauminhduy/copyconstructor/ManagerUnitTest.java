package com.khauminhduy.copyconstructor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class ManagerUnitTest {
	
	@Test
	public void givenCopyConstructor_whenDeepCopy_thenDistinct() {
		Date startDate = new Date(123);
		Employee e1 = new Employee(1, "DK", startDate);
		Employee e2 = new Employee(e1);

		assertNotEquals(e1, e2);

		List<Employee> directReports = new ArrayList<>();
		directReports.add(e1);
		directReports.add(e2);

		Manager m1 = new Manager(1, "DK", startDate, directReports);
		Manager m2 = new Manager(m1);

		assertNotEquals(m1, m2);

		List<Employee> directReports1 = m1.getDirectReports();
		List<Employee> directReports2 = m2.getDirectReports();

		assertFalse(directReports1 == directReports2);		

		assertEquals(directReports1.size(), directReports2.size());
		assertArrayEquals(directReports1.toArray(), directReports2.toArray());

		directReports.clear();
		directReports1 = m1.getDirectReports();
		directReports2 = m2.getDirectReports();
		assertEquals(0, directReports1.size());
		assertEquals(2, directReports2.size());

	}

}
