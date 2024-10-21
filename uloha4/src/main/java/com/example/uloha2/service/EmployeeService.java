package com.example.uloha2.service;

import com.example.uloha2.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> gerSortedEmployee();

    List<Employee> findAll();

    Employee findById(Long id);

    Employee save(Employee employee);

    boolean deleteById(Long id);
}
