package com.luv2code.springboot.employees.service;

import com.luv2code.springboot.employees.dao.EmployeeDAO;
import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(long theId) {
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional
    public Employee save(EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(0,employeeRequest);
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public Employee update(long id, EmployeeRequest theEmployeeRequest) {
        Employee theEmployee = convertToEmployee(id,theEmployeeRequest);
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public void delete(long theId) {
        employeeDAO.delete(theId);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id,employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }


}
