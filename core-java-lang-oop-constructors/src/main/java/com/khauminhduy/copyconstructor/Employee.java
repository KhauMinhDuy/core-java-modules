package com.khauminhduy.copyconstructor;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Employee {
	
	protected int id;
	protected String name;

	@Getter
	protected Date startDate;

	public Employee(Employee employee) {
		this.id = employee.id;
		this.name = employee.name;
		this.startDate = new Date(employee.getStartDate().getTime());
	}

	public Employee copy() {
		return new Employee(this);
	}

}
