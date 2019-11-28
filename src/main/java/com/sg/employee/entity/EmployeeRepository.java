package com.sg.employee.entity;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository< Employee, String > {

}
