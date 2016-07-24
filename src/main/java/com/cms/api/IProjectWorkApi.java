package com.cms.api;

import com.cms.dto.GoalDTO;
import com.cms.dto.ProjectDTO;
import com.cms.model.Activity;
import com.cms.model.Goal;
import com.cms.model.Project;
import com.cms.model.TrainingCenter;

import java.util.List;

/**
 * Created by amit on 7/10/16.
 */
public interface IProjectWorkApi {

    String createOrEditProject(ProjectDTO dto);

    List<Project> getAllProjectsByFiscalYear(String fiscalYear);

    String deleteProject(Integer id);

    ProjectDTO getProjectById(Integer id);

    List<Activity> getActivitiesByProjectId(Integer projectId);

    Project getProjectByProjectCode(String code);

    String createOrEditGoal(GoalDTO dto);

    List<Goal> findGoalsByActivityId(Integer activityId);

    String createOrEditActivity(Activity a);

    Activity getActivityById(Integer id);
}


