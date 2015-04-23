package com.frequentis.techtalk.rest.server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Employee not found")
public class EmployeeNotFoundException extends RuntimeException {
}
