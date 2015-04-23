package com.frequentis.techtalk.rest.client;

import com.frequentis.techtalk.rest.domain.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class RestApp {

    public static void main(String... args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("rest-client.xml");
        EmployeeClient client = (EmployeeClient) context.getBean("employeeClient");

        System.out.println("LIST EMPLOYEES");
        List<Employee> list = client.list();
        for (Employee e : list) {
            printEmployee(e);
        }

        System.out.println();
        System.out.println("EMPLOYEE with id = 1");
        Employee employee = client.get(1);
        printEmployee(employee);

        System.out.println();
        System.out.println("CREATE NEW EMPLOYEE");
        Employee newEmployee = new Employee();
        newEmployee.setName("Barni");
        newEmployee.setAge(24);
        client.create(newEmployee);

        System.out.println();
        System.out.println("LIST EMPLOYEES");
        list = client.list();
        for (Employee e : list) {
            printEmployee(e);
        }

        System.out.println();
        System.out.println("UPDATE EMPLOYEE with id = 1");
        employee.setAge(15);
        client.update(employee);

        System.out.println();
        System.out.println("LIST EMPLOYEES");
        list = client.list();
        for (Employee e : list) {
            printEmployee(e);
        }

        System.out.println();
        System.out.println("UPDATE EMPLOYEE with id = 3");
        client.delete(3);

        System.out.println();
        System.out.println("LIST EMPLOYEES");
        list = client.list();
        for (Employee e : list) {
            printEmployee(e);
        }
    }

    private static void printEmployee(Employee employee) {
        System.out.println("Id: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge());
    }
}
