package com.cms.service;

import com.cms.dto.ProjectDTO;
import com.cms.utility.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amit on 6/16/16.
 */

@Service
public class PriviligedService implements IAdminService {
    @Autowired
    IProjectWorkApi projectWorkApi;
    @Override
    public List<ProjectDTO> getAllProjectsByFiscalYear(String fiscalYear) {
        return ConvertUtils.convertToProjectDTOs(projectWorkApi.getAllProjectsByFiscalYear(fiscalYear));
    }

    @Override
    public String createOrEditProject(ProjectDTO dto) {
        return projectWorkApi.createOrEditProject(dto);
    }

    @Override
    public ProjectDTO getProject(Integer id) {
        return projectWorkApi.getProjectById(id);
    }

    @Override
    public String deleteProject(Integer id) {
        return null;
    }
}
