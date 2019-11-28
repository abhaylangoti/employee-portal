package com.sg.employee.repository;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.employee.domain.Department;
import com.sg.employee.domain.Gender;
import com.sg.employee.entity.Employee;
import com.sg.employee.entity.EmployeeRepository;

@ExtendWith( SpringExtension.class )
@DataJpaTest
public class EmployeeRepositoryTest {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Test
    public void testSave() {
        Employee employee = new Employee();
        employee.setDateOfBirth( new Date(System.currentTimeMillis()) );
        employee.setDepartment( Department.HR );
        employee.setFirstName( "Rajesh" );
        employee.setGender( Gender.MALE );
        employee.setLastName( "Kumar" );
        employee =  employeeRepository.save( employee  );
        Assertions.assertThat( employee.getId() ).isNotNull();

    }
}
