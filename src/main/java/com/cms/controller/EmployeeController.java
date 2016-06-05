package com.cms.controller;

import com.cms.model.Department;
import com.cms.repository.dao.EmployeesDao;
import com.cms.service.DepartmentService;
import com.cms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nirmal on 3/22/2016.
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeesDao employeesDao;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/employees")
    public List<Department> getEmployee() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        System.out.println("allDepartments.size() = " + allDepartments.size());
        return allDepartments;

    }
}
