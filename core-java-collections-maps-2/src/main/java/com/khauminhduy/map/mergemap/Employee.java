package com.khauminhduy.map.mergemap;

import lombok.Data;

@Data
public class Employee implements Comparable<Employee> {

    private Long id;
    private String name;

    @Override
    public int compareTo(Employee employee) {
        return (int)(this.id - employee.getId());
    }

}
