package com.cms.service;

import com.cms.dto.ProjectDTO;
import com.cms.dto.TrainingCenterDTO;
import com.cms.dto.UserDTO;
import com.cms.model.TrainingCenter;

import java.util.List;

/**
 * Created by amit on 6/16/16.
 */
public interface IAdminService {
    List<ProjectDTO> getAllProjectsByFiscalYear(String fiscalYear);

    String createOrEditProject(ProjectDTO dto);

    ProjectDTO getProject(Integer id);

    String deleteProject(Integer id);

    String createOrEditTrainingCenter(TrainingCenterDTO dto);

    TrainingCenterDTO getTrainingCenter(Integer id);

    String deleteTrainingCenter(Integer id);

    List<TrainingCenterDTO> getAllTrainingCenters();

    List<TrainingCenterDTO> getAllParentTrainingCenters();

}
