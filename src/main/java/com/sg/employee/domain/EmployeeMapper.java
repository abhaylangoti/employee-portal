package com.sg.employee.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sg.employee.entity.Employee;

@Component
public class EmployeeMapper {

    public Employee mapToEmployee( EmployeeVO employeeVO ) {
        Employee employee = new Employee();
        BeanUtils.copyProperties( employeeVO, employee );
        return employee;
    }

    public EmployeeVO mapToEmployeeVO( Employee employee ) {
        // TODO Auto-generated method stub
        return null;
    }

}
