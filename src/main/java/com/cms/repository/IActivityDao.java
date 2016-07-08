package com.cms.repository;

import com.cms.model.Activity;
import com.cms.model.Goal;
import com.cms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IActivityDao extends JpaRepository<Activity, Integer>,
        JpaSpecificationExecutor<Activity> {

	/*
     * @Query("SELECT u FROM Role u WHERE LOWER(u.Rolename) = LOWER(:name)")
	 * Role retrieveByName(@Param("name") String name);
	 */
}
