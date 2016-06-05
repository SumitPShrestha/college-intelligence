package com.cms.service;

import com.cms.model.Department;
import com.cms.repository.jpa.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nirmal on 3/22/2016.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class DepartmentService {


    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
