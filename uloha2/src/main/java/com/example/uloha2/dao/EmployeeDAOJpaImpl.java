package com.example.uloha2.dao;

import com.example.uloha2.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // question...
//        TypedQuery<Employee> query =
//                entityManager.createQuery("from Employee", Employee.class);
//        List<Employee> employees = query.getResultList();
//        return employees;
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findById(Long id){
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee){
        return entityManager.merge(employee);
    }

    @Transactional // runs inside a transaction, ensuring that the entity deletion is committed (or rolled back) properly
    @Override
    public boolean deleteById(Long id) {
        Employee entity = entityManager.find(Employee.class, id);

        if (entity != null) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }
}
