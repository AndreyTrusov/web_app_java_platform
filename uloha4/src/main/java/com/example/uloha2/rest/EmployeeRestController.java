package com.example.uloha2.rest;

import com.example.uloha2.entity.Employee;
import com.example.uloha2.entity.ResponseStatus;
import com.example.uloha2.service.EmployeeService;
import com.example.uloha2.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeServiceImpl employeeServiceImpl;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeService;
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/employee")
    public ResponseEntity<Object> findAll() {
        List<Employee> employees = employeeService.findAll();
        if (employees.isEmpty()) {
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.NO_CONTENT.value(), "No employees found", System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<Object> addEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errors = new StringBuilder();

            for ( ObjectError error : bindingResult.getAllErrors()) {
                errors.append(error.getDefaultMessage()).append("\n");
            }

            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.BAD_REQUEST.value(), errors.toString(), System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        employee.setId(0L);
        employeeService.save(employee);

        ResponseStatus successResponse = new ResponseStatus(HttpStatus.OK.value(),"Student added successfully", System.currentTimeMillis());
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.NOT_FOUND.value(),
                    "Employee not found", System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.append(error.getDefaultMessage()).append("\n");
            }
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.BAD_REQUEST.value(), errors.toString(), System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.NOT_FOUND.value(), "Employee not found", System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        ResponseStatus successResponse = new ResponseStatus(HttpStatus.OK.value(), "Employee updated successfully", System.currentTimeMillis());
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.NOT_FOUND.value(), "Employee not found", System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        boolean deleted = employeeService.deleteById(id);
        if (deleted) {
            ResponseStatus successResponse = new ResponseStatus(HttpStatus.OK.value(), "Employee deleted successfully", System.currentTimeMillis());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            ResponseStatus errorResponse = new ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete employee", System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/sorted")
    public List<Employee> getEmployeeSorted() {
        return employeeServiceImpl.gerSortedEmployee();
    }
}
