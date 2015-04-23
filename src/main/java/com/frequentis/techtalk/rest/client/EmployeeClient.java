package com.frequentis.techtalk.rest.client;

import com.frequentis.techtalk.rest.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeClient {

    private static final String SERVER = "http://localhost:8080/rest-tech-talk/rest";

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> list() {
        String url = SERVER + "/employee/list";
        Employee[] employees =  restTemplate.getForObject(url, Employee[].class);
        return Arrays.asList(employees);
    }

    public Employee get(int id) {
        String url = SERVER + "/employee/get/" + id;
        return restTemplate.getForObject(url, Employee.class);
    }

    public void create(Employee employee) {
        String url = SERVER + "/employee/create";
        restTemplate.postForObject(url, employee, Object.class);
    }

    public void update(Employee employee) {
        String url = SERVER + "/employee/update";
        restTemplate.put(url, employee);
    }

    public void delete(int id) {
        String url = SERVER + "/employee/delete/" + id;
        restTemplate.delete(url);
    }
}
