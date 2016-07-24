package com.cms.api;

import com.cms.dto.GoalDTO;
import com.cms.dto.ProjectDTO;
import com.cms.model.*;
import com.cms.repository.IActivityDao;
import com.cms.repository.IGoalActivity;
import com.cms.repository.IGoalDAO;
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
    @Autowired
    IActivityDao activityDAO;
    @Autowired
    IGoalDAO goalDAO;
    @Autowired
    IGoalActivity goalActivityDAO;
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

    @Override
    public List<Activity> getActivitiesByProjectId(Integer projectId) {
        return activityDAO.findActivitiesByProjectId(projectId);
    }

    @Override
    public Project getProjectByProjectCode(String code) {
        return projectDAO.findProjectByProjectCode(code);
    }

    @Override
    public String createOrEditGoal(GoalDTO dto) {
        Goal goal=goalDAO.save(dto.getGoal());
        GoalActivity ga= new GoalActivity();
        ga.setGoal(goal);
        Activity activity=activityDAO.findOne(dto.getActivityId());
        ga.setActivity(activity);
        goalActivityDAO.save(ga);
        return  goal.getId().toString();


    }

    @Override
    public List<Goal> findGoalsByActivityId(Integer activityId) {
        return goalActivityDAO.getAllGoalsByActivityId(activityId);
    }

    @Override
    public String createOrEditActivity(Activity a) {
      /*  Activity activity= projectDAO.findOne(a.getId());
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
        return projectCode;*/
        return null;
    }

    @Override
    public Activity getActivityById(Integer id) {
        return activityDAO.findOne(id);
    }


}
