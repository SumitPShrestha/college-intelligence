package com.cms.repository;

import com.cms.model.Role;
import com.cms.model.Training;
import com.cms.model.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITrainingDAO extends JpaRepository<Training, Integer>,
        JpaSpecificationExecutor<Role> {

	/*
     * @Query("SELECT u FROM Role u WHERE LOWER(u.Rolename) = LOWER(:name)")
	 * Role retrieveByName(@Param("name") String name);
	 */
}
