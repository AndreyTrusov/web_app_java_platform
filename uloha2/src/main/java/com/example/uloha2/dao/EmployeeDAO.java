package com.example.uloha2.dao;

import com.example.uloha2.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(Long id);

    Employee save(Employee employee);

    boolean deleteById(Long id);

}
