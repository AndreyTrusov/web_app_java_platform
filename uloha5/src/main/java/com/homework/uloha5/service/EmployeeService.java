package com.homework.uloha5.service;

import com.homework.uloha5.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> gerSortedEmployee();

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    boolean deleteById(Long id);
}
