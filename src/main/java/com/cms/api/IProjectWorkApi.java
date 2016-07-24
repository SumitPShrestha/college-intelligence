package com.cms.api;

import com.cms.dto.ProjectDTO;
import com.cms.model.Project;

import java.util.List;

/**
 * Created by amit on 7/10/16.
 */
public interface IProjectWorkApi {

    String createOrEditProject(ProjectDTO dto);
    List<Project> getAllProjectsByFiscalYear(String fiscalYear);

    String deleteProject(Integer id);

    ProjectDTO getProjectById(Integer id);
}
