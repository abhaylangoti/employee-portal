package com.sg.employee.api;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.employee.domain.Department;
import com.sg.employee.domain.EmployeeVO;
import com.sg.employee.domain.Gender;

@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT )
public class EmployeeIntegrationTest {

    @LocalServerPort
    int serverPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCreateEmployeeSuccess() {
        EmployeeVO employeeVO = new EmployeeVO();
        String firstName = "Rajesh";
        employeeVO.setFirstName( firstName );
        long currentTimeMillis = System.currentTimeMillis();
        employeeVO.setDateOfBirth( new Date( currentTimeMillis ) );
        Department department = Department.HR;
        employeeVO.setDepartment( department );
        Gender gender = Gender.FEMALE;
        employeeVO.setGender( gender );
        String lastName = "Kumar";
        employeeVO.setLastName( lastName );

        ResponseEntity< EmployeeVO > response = testRestTemplate.postForEntity( "http://localhost:" + serverPort
                + "/employee/register", employeeVO, EmployeeVO.class );
        EmployeeVO employeeResponse = response.getBody();
        
        Assertions.assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );

        Assertions.assertThat( employeeResponse.getFirstName() ).isEqualTo( firstName );
        Assertions.assertThat( employeeResponse.getLastName() ).isEqualTo( lastName );
        Assertions.assertThat( employeeResponse.getDateOfBirth().getTime() ).isEqualTo( currentTimeMillis );
        Assertions.assertThat( employeeResponse.getDepartment() ).isEqualTo( department );
        Assertions.assertThat( employeeResponse.getGender() ).isEqualTo( gender);
        Assertions.assertThat( employeeResponse.getId() ).isNotNull();

    }
}
