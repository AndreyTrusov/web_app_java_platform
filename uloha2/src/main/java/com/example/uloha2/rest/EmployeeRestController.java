package com.example.uloha2.rest;

import com.example.uloha2.entity.Employee;
import com.example.uloha2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0L); // This is correct for adding a new employee
        return employeeService.save(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee existingEmployee = employeeService.findById(id);
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable Long id){
        Employee existingEmployee = employeeService.findById(id);
        return employeeService.deleteById(id);
    }
}
