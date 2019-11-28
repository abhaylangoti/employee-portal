package com.sg.employee.domain;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sg.employee.entity.Employee;

@ExtendWith( MockitoExtension.class )
public class EmployeeMapperTest {
    
    EmployeeMapper mapper = new EmployeeMapper();
    
    @Test
    public void testMapToEmployee() {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setDateOfBirth( new Date(System.currentTimeMillis()) );
        employeeVO.setDepartment( Department.HR );
        employeeVO.setGender( Gender.MALE );
        employeeVO.setFirstName( "firstName" );
        employeeVO.setLastName( "lastName" );
        Employee employee = mapper.mapToEmployee( employeeVO  );
        Assertions.assertThat( employee.getFirstName() ).isEqualTo( employeeVO.getFirstName() );
        Assertions.assertThat( employee.getLastName() ).isEqualTo( employeeVO.getLastName() );
        Assertions.assertThat( employee.getDateOfBirth() ).isEqualTo( employeeVO.getDateOfBirth() );
        Assertions.assertThat( employee.getDepartment() ).isEqualTo( employeeVO.getDepartment() );
    }

}
