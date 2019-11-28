package com.sg.employee.domain;

import java.util.Date;

public class EmployeeVO {
    
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Department department;
    private Date dateOfBirth;
    
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender( Gender gender ) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment( Department department ) {
        this.department = department;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

}
