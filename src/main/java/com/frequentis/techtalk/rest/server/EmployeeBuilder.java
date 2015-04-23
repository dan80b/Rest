package com.frequentis.techtalk.rest.server;

import com.frequentis.techtalk.rest.domain.Employee;

public class EmployeeBuilder {

    private Employee employee = new Employee();

    public EmployeeBuilder(int id) {
        this.employee.setId(id);
    }

    public EmployeeBuilder withName(String name) {
        this.employee.setName(name);
        return this;
    }

    public EmployeeBuilder withAge(int age) {
        this.employee.setAge(age);
        return this;
    }

    public Employee build() {
        return this.employee;
    }
}
