package com.cms.service;

import com.cms.api.IProjectWorkApi;
import com.cms.api.ITrainingApi;
import com.cms.dto.ProjectDTO;
import com.cms.dto.TrainingCenterDTO;
import com.cms.model.Training;
import com.cms.model.TrainingCenter;
import com.cms.utility.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amit on 6/16/16.
 */

@Service
public class AdminService implements IAdminService {
    @Autowired
    IProjectWorkApi projectWorkApi;
    @Autowired
    ITrainingApi trainingApi;
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
        return projectWorkApi.deleteProject(id);
    }

    @Override
    public String createOrEditTrainingCenter(TrainingCenterDTO tc) {
        return trainingApi.createOrEditTrainingCenter(tc);
    }

    @Override
    public TrainingCenterDTO getTrainingCenter(Integer id) {
        return trainingApi.findTrainingCenterById(id);
    }

    @Override
    public String deleteTrainingCenter(Integer id) {
        return trainingApi.deleteTrainingCenter(id);
    }

    @Override
    public List<TrainingCenterDTO> getAllTrainingCenters() {
        return trainingApi.findAllTrainingCenter();
    }

    @Override
    public List<TrainingCenterDTO> getAllParentTrainingCenters() {
        return trainingApi.findAllParentTrainingCenter();
    }
}
