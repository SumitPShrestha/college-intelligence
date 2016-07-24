package com.cms.api;

import com.cms.dto.ProjectDTO;
import com.cms.model.Project;
import com.cms.repository.IProjectDAO;
import com.cms.utility.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amit on 7/10/16.
 */
@Service
public class ProjectWorkApi implements IProjectWorkApi {

    @Autowired
    IProjectDAO projectDAO;
    @Override
    public String createOrEditProject(ProjectDTO dto) {

        Project project= projectDAO.findOne(dto.getId());
        if(project==null){
            project= new Project();
        }
        project.setAidOrganisation(dto.getAidOrganisation());
        project.setBudget(dto.getBudget());
        project.setBudgetSubHeadNumber(dto.getBudgetSubHeadNumber());
        project.setProjectCode(dto.getProjectCode());
        project.setFiscalYear(dto.getFiscalYear());
        project=projectDAO.save(project);
        String projectCode=project.getProjectCode();
        return projectCode;
    }

    @Override
    public List<Project> getAllProjectsByFiscalYear(String fiscalYear) {
        return projectDAO.findProjectsByFiscalYear(fiscalYear);
    }

    @Override
    public String deleteProject(Integer id) {
        projectDAO.delete(id);
        return id.toString();
    }

    @Override
    public ProjectDTO getProjectById(Integer id) {
        return ConvertUtils.convertToProjectDTO(projectDAO.findOne(id));
    }
}
