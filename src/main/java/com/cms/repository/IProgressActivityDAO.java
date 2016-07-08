package com.cms.repository;

import com.cms.model.Progress;
import com.cms.model.ProgressActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProgressActivityDAO extends JpaRepository<ProgressActivity, Integer>,
        JpaSpecificationExecutor<ProgressActivity> {





	/*
     * @Query("SELECT u FROM Role u WHERE LOWER(u.Rolename) = LOWER(:name)")
	 * Role retrieveByName(@Param("name") String name);
	 */
}
