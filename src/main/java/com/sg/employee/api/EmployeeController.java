package com.sg.employee.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.employee.domain.EmployeeVO;

@RestController
public class EmployeeController {

    @PostMapping(value = "/employee/register", consumes = "application/json", produces = "application/json")
    public @ResponseBody EmployeeVO registerEmployee(@RequestBody EmployeeVO employeeVO) {
        return employeeVO;
    }
}
