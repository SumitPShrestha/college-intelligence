package com.cms.service;

import com.cms.dto.ProjectDTO;
import com.cms.dto.UserDTO;

import java.util.List;

/**
 * Created by amit on 6/16/16.
 */
public interface IPrivilegedService {
    List<ProjectDTO> getAllProjectsByFiscalYear(String fiscalYear);

    String createOrEditProject(ProjectDTO dto);

    ProjectDTO getProject(Integer id);

    String deleteProject(Integer id);
}
