package com.luv2code.springboot.employees.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {

    @NotBlank(message = " First Name is mandatory")
    @Size(min = 2 , max = 50 , message = "First Name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 2 , max = 50 , message = "Last Name must be between 2 and 50 characters")
    private String lastName ;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    public EmployeeRequest(String firstName, String email, String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



