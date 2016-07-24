package com.cms.repository;

import com.cms.model.ActivityProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProgressActivityDAO extends JpaRepository<ActivityProgress, Integer>,
        JpaSpecificationExecutor<ActivityProgress> {





	/*
     * @Query("SELECT u FROM Role u WHERE LOWER(u.Rolename) = LOWER(:name)")
	 * Role retrieveByName(@Param("name") String name);
	 */
}
