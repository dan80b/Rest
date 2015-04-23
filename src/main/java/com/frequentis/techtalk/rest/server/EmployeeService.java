package com.frequentis.techtalk.rest.server;

import com.frequentis.techtalk.rest.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    public static Map<Integer, Employee> EMPLOYEES = new HashMap<>();
    private static int NEXT_ID = 3;

    static {
        EMPLOYEES.put(1, new EmployeeBuilder(1).withName("Dan").withAge(34).build());
        EMPLOYEES.put(2, new EmployeeBuilder(2).withName("Dezso").withAge(25).build());
    }

    public List<Employee> list() {
        return new ArrayList<>(EMPLOYEES.values());
    }

    public Employee get(int id) {
        if (EMPLOYEES.get(id) == null) {
            throw new EmployeeNotFoundException();
        }
        return EMPLOYEES.get(id);
    }

    public void create(Employee employee) {
        int nextId = NEXT_ID++;
        employee.setId(nextId);
        EMPLOYEES.put(nextId, employee);
    }

    public void update(Employee employee) {
        EMPLOYEES.put(employee.getId(), employee);
    }

    public void delete(int id) {
        if (EMPLOYEES.get(id) == null) {
            throw new EmployeeNotFoundException();
        }
        EMPLOYEES.remove(id);
    }
}
