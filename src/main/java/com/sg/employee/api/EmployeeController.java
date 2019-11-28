package com.sg.employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.employee.domain.EmployeeVO;
import com.sg.employee.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee/register", consumes = "application/json", produces = "application/json")
    public @ResponseBody EmployeeVO registerEmployee(@RequestBody EmployeeVO employeeVO) {
        employeeVO = employeeService.register(employeeVO);
        return employeeVO;
    }
}
