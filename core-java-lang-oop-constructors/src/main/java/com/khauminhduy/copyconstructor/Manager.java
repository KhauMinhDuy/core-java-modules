package com.khauminhduy.copyconstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Manager extends Employee {

	@Getter
	private List<Employee> directReports;

	public Manager(int id, String name, Date startDate, List<Employee> directReports) {
		super(id, name, startDate);
		this.directReports = directReports;
	}

	public Manager(Manager manager) {
		super(manager.id, manager.name, manager.startDate);
		this.directReports = manager.directReports.stream().collect(Collectors.toList());
	}
	
	@Override
	public Employee copy() {
		return new Manager(this);
	}

}
