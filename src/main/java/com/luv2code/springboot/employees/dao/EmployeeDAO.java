package com.luv2code.springboot.employees.dao;

import com.luv2code.springboot.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

}
