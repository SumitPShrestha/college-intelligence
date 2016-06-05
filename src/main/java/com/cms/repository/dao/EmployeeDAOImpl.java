package com.cms.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;


@Repository
public class EmployeeDAOImpl implements EmployeesDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public int getRecordCount() {
        Map<String, Object> paramsMap = Collections.emptyMap();
        return jdbcTemplate.queryForObject("select count(*) from employees", paramsMap, Integer.class);
    }
}
