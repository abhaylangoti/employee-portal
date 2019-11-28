package com.sg.employee.service;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sg.employee.domain.Department;
import com.sg.employee.domain.EmployeeVO;
import com.sg.employee.domain.Gender;
import com.sg.employee.entity.Employee;

@ExtendWith( MockitoExtension.class )
public class EmployeeServiceTest {

    private EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    EmployeeMapper employeeMapper;

    @BeforeEach
    public void setup() {
        employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);
    }

    @Test
    public void testRegisterEmployeeSuccess() {
        String firstName = "Rajesh";
        long currentTimeMillis = System.currentTimeMillis();
        Department department = Department.HR;
        Gender gender = Gender.FEMALE;
        String lastName = "Kumar";

        EmployeeVO employeeVO = createEmployeeObject( firstName, currentTimeMillis, department, gender, lastName );

        BDDMockito.given( employeeMapper.mapToEmployee( ArgumentMatchers.any( EmployeeVO.class ) ) ).willReturn(
                new Employee() );
        BDDMockito.given( employeeMapper.mapToEmployeeVO( ArgumentMatchers.any( Employee.class ) ) ).willReturn(
                employeeVO );
        BDDMockito.given( employeeRepository.save( ArgumentMatchers.any( Employee.class ) ) ).willReturn(
                new Employee() );
        
        employeeVO = employeeService.register( employeeVO );

        Assertions.assertThat( employeeVO.getFirstName() ).isEqualTo( firstName );
        Assertions.assertThat( employeeVO.getLastName() ).isEqualTo( lastName );
        Assertions.assertThat( employeeVO.getDepartment() ).isEqualTo( department );
        Assertions.assertThat( employeeVO.getGender() ).isEqualTo( gender );

    }

    private EmployeeVO createEmployeeObject( String firstName, long currentTimeMillis, Department department,
            Gender gender, String lastName ) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setFirstName( firstName );
        employeeVO.setDateOfBirth( new Date( currentTimeMillis ) );
        employeeVO.setDepartment( department );
        employeeVO.setGender( gender );
        employeeVO.setLastName( lastName );
        return employeeVO;
    }
}
