package com.homework.uloha5.service;

import com.homework.uloha5.entity.Employee;
import com.homework.uloha5.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> gerSortedEmployee(){
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee object cannot be null");
        }
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        boolean exists = employeeRepository.existsById(id);

        if (exists) {
            employeeRepository.deleteById(id);
            System.out.println("Employee with ID " + id + " deleted successfully.");
            return true;
        } else {
            System.out.println("Employee with ID " + id + " not found.");
            return false;
        }
    }
}
