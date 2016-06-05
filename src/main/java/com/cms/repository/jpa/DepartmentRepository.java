package com.cms.repository.jpa;

import com.cms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nirmal on 3/22/2016.
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
