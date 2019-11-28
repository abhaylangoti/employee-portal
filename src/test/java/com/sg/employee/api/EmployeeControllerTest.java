package com.sg.employee.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.employee.domain.Department;
import com.sg.employee.domain.EmployeeVO;
import com.sg.employee.domain.Gender;
import com.sg.employee.service.EmployeeService;

@ExtendWith( SpringExtension.class )
@WebMvcTest( controllers = { EmployeeController.class } )
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;

    @Test
    public void testRegisterEmployeeSuccessStatus() throws Exception {
        String firstName = "Rajesh";
        long currentTimeMillis = System.currentTimeMillis();
        Department department = Department.HR;
        Gender gender = Gender.FEMALE;
        String lastName = "Kumar";

        EmployeeVO employeeVO = createEmployeeObject( firstName, currentTimeMillis, department, gender, lastName );
        org.mockito.BDDMockito.given( employeeService.register( ArgumentMatchers.any( EmployeeVO.class ) ) )
                .willReturn( employeeVO );

        mockMvc.perform(
                MockMvcRequestBuilders.post( "/employee/register" ).content( asJsonString( employeeVO ) )
                        .contentType( MediaType.APPLICATION_JSON ).accept( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isOk() ).andExpect( jsonPath( "firstName" ).value( firstName ) )
                .andExpect( jsonPath( "lastName" ).value( lastName ) )
                .andExpect( jsonPath( "gender" ).value( gender.toString() ) )
                .andExpect( jsonPath( "department" ).value( department.toString() ) );
    }

    @Test
    public void testRegisterEmployeeSuccessVerifyId() throws Exception {

        String firstName = "Rajesh";
        long currentTimeMillis = System.currentTimeMillis();
        Department department = Department.HR;
        Gender gender = Gender.FEMALE;
        String lastName = "Kumar";

        EmployeeVO employeeVO = createEmployeeObject( firstName, currentTimeMillis, department, gender, lastName );
        EmployeeVO employeeVOResponse = createEmployeeObject( firstName, currentTimeMillis, department, gender,
                lastName );
        employeeVOResponse.setId( 123L);
        org.mockito.BDDMockito.given( employeeService.register( ArgumentMatchers.any( EmployeeVO.class ) ) )
                .willReturn( employeeVOResponse );
        mockMvc.perform(
                MockMvcRequestBuilders.post( "/employee/register" ).content( asJsonString( employeeVO ) )
                        .contentType( MediaType.APPLICATION_JSON ).accept( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isOk() ).andExpect( jsonPath( "id" ).isNotEmpty() );

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

    private static String asJsonString( final Object obj ) {
        try {
            return new ObjectMapper().writeValueAsString( obj );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

}
