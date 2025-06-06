package com.luv2code.springboot.employees.service;

import com.luv2code.springboot.employees.dao.EmployeeRepository;
import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null ;
        if(result.isPresent()){
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find Employee Id -"+theId);
        }
        return theEmployee ;
    }

    @Override
    @Transactional
    public Employee save(EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(0,employeeRequest);
        return employeeRepository.save(theEmployee);
    }

    @Override
    @Transactional
    public Employee update(long id, EmployeeRequest theEmployeeRequest) {
        Employee theEmployee = convertToEmployee(id,theEmployeeRequest);
        return employeeRepository.save(theEmployee);
    }

    @Override
    @Transactional
    public void delete(long theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id,employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }


}
