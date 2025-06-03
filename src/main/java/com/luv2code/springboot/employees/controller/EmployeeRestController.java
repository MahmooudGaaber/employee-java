package com.luv2code.springboot.employees.controller;
import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import com.luv2code.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Rest API Endpoints" , description = "Operations related to employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }


    @Operation(summary = "Get All Employees" , description = "Retrieve List Of Employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @Operation(summary = "Get Single Employee" , description = "Retrieve Single Employee")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable @Min(value = 1) long id){
        return employeeService.findById(id);
    }

    @Operation(summary = "Create New Employee" , description = "Create New Employee")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest theEmployee){
        return  employeeService.save(theEmployee);
    }

    @Operation(summary = "Update Employee" , description = "Update Employee Details")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Employee updateEmployee (
            @PathVariable @Min(value = 1) long id,
            @Valid @RequestBody EmployeeRequest theEmployee
            ){
        return employeeService.update(id, theEmployee);
    }

    @Operation(summary = "Delete Employee" , description = "Delete Employee Details")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long id){
        employeeService.delete(id);
    }


}




