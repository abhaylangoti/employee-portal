package com.sg.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.employee.domain.EmployeeMapper;
import com.sg.employee.domain.EmployeeVO;
import com.sg.employee.entity.Employee;
import com.sg.employee.entity.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl( EmployeeRepository repository, EmployeeMapper mapper ) {
        this.employeeRepository = repository;
        this.employeeMapper = mapper;
    }

    public EmployeeServiceImpl() {

    }

    public EmployeeVO register( EmployeeVO employeeVO ) {
        validateRequest(employeeVO);
        Employee employee = employeeMapper.mapToEmployee( employeeVO );
        employee = employeeRepository.save( employee );
        employeeVO = employeeMapper.mapToEmployeeVO( employee );
        return employeeVO;
    }

    private void validateRequest( EmployeeVO employeeVO ) {
        if(employeeVO.getFirstName() == null || employeeVO.getFirstName().isEmpty()) {
          throw new IllegalArgumentException( "firstName is missing" );  
        }
        
    }
}
