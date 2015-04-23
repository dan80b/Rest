package com.frequentis.techtalk.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frequentis.techtalk.rest.domain.Employee;
import com.frequentis.techtalk.rest.server.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class EmployeeControllerTest {

    private static final String REQUEST_LIST = "/employee/list";
    private static final String REQUEST_EMPLOYEE_BY_ID = "/employee/{id}";
    private static final String REQUEST_EMPLOYEE = "/employee";

    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void list() throws Exception {

        List<Employee> expected = new ArrayList<>(EmployeeService.EMPLOYEES.values());
        when(service.list()).thenReturn(expected);

        this.mockMvc.perform(get(REQUEST_LIST).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(expected.get(0).getName()))
                .andExpect(jsonPath("$[0].age").value(expected.get(0).getAge()))
                .andExpect(jsonPath("$[1].id").value(expected.get(1).getId()))
                .andExpect(jsonPath("$[1].name").value(expected.get(1).getName()))
                .andExpect(jsonPath("$[1].age").value(expected.get(1).getAge()));
    }

    @Test
    public void getFound() throws Exception {

        int id = 1;
        Employee expected = EmployeeService.EMPLOYEES.get(id);
        when(service.get(id)).thenReturn(expected);

        this.mockMvc.perform(get(REQUEST_EMPLOYEE_BY_ID, id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.age").value(expected.getAge()));

        verify(service).get(id);
    }

    @Test
    public void getNotFound() throws Exception {
        int id = 8;
        doThrow(EmployeeNotFoundException.class).when(service).get(id);
        this.mockMvc.perform(get(REQUEST_EMPLOYEE_BY_ID, id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(service).get(id);
    }

    @Test
    public void create() throws Exception {

        Employee employee = new EmployeeBuilder(0).withName("new employee").withAge(20).build();
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, employee);

        this.mockMvc.perform(post(REQUEST_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
                .content(writer.toString().getBytes())).andExpect(status().isCreated());

        verify(service).create(employee);
    }

    @Test
    public void update() throws Exception {

        int id = 1;
        Employee employee = EmployeeService.EMPLOYEES.get(id);
        employee.setAge(25);

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, employee);

        this.mockMvc.perform(put(REQUEST_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
                .content(writer.toString().getBytes())).andExpect(status().isOk());

        verify(service).update(employee);
    }

    @Test
    public void deleteFound() throws Exception {
        int id = 1;
        this.mockMvc.perform(delete(REQUEST_EMPLOYEE_BY_ID, id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).delete(id);
    }
}
