package com.example.uloha2.dao;

import com.example.uloha2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByOrderByLastNameAsc();
}
