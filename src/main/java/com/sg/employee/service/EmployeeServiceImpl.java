package com.sg.employee.service;

import org.springframework.stereotype.Service;

import com.sg.employee.domain.EmployeeVO;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

    public EmployeeVO register( EmployeeVO employeeVO ) {
        return employeeVO;
    }
}
