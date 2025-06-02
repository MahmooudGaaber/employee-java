package com.luv2code.springboot.employees.dao;

import com.luv2code.springboot.employees.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl (EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
       // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);

        //execute it and get result
//        List<Employee> employees = theQuery.getResultList();

        //return result
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(long theId) {
//        Employee employee = entityManager.find(Employee.class,theId);
        return entityManager.find(Employee.class,theId);
    }

    @Override
    public Employee save(Employee theEmployee) {
        return entityManager.merge(theEmployee);
    }

    @Override
    public void delete(long theId ) {
        Employee theEmployee = entityManager.find(Employee.class , theId);
        if(theEmployee != null){
            entityManager.remove(theEmployee);
        }
    }
}
