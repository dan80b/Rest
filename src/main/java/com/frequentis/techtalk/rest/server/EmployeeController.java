package com.frequentis.techtalk.rest.server;

import com.frequentis.techtalk.rest.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Employee> list() {
        return employeeService.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee get(@PathVariable("id") int id) {
        return employeeService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee) {
        this.employeeService.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@RequestBody Employee employee) {
        this.employeeService.update(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") int id) {
        this.employeeService.delete(id);
    }
}
