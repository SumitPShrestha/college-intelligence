package com.cms.controller;

import com.cms.dto.*;
import com.cms.model.Activity;
import com.cms.model.Goal;
import com.cms.service.IPrivilegedService;
import com.cms.service.RequestUrlToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
/*@Secured("ROLE_ADMIN")*/

@RequestMapping("/privileged")
public class PrivilegedController {

    @Autowired
    private IPrivilegedService privilegedService;


    @RequestMapping(value = RequestUrlToken.GET_ACTIVITY_BY_PROJECT_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<Activity> showAllActivities(@PathVariable String code)  throws JsonProcessingException {
        List<Activity> pjs = privilegedService.getAllActivitiesByProjectCode(code);
        return pjs;
    }

    @RequestMapping(value = RequestUrlToken.CREATE_ACTIVITY, method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Activity activity)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditActivity(activity);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_ACTIVITY, method = RequestMethod.PUT)
    @ResponseBody
    public String saveActivity(@RequestBody TrainingDTO dto)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditTraining(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  updated successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.DELETE_ACTIVITY, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteActivity(@PathVariable Integer id)
            throws JsonProcessingException {
        String goalId = privilegedService.deleteTraining(id);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString("Training with "+goalId +"  deleted successfully");
        return val;
    }

    @RequestMapping(value = RequestUrlToken.GET_ACTIVITY, method = RequestMethod.GET)
    @ResponseBody
    public Activity getActivity(@PathVariable Integer id)
            throws JsonProcessingException {
        return  privilegedService.getActivity(id);
    }














    @RequestMapping(value = RequestUrlToken.CREATE_GOAL, method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody GoalDTO dto)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditGoal(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_GOAL, method = RequestMethod.PUT)
    @ResponseBody
    public String saveUser(@RequestBody GoalDTO dto)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditGoal(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  updated successfully");
        return val;
    }

    @RequestMapping(value = RequestUrlToken.GET_GOALS_BY_ACTIVITY_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<Goal> showAllGoals(@PathVariable Integer id)
            throws JsonProcessingException {
        List<Goal> ss = privilegedService.getAllGoalsByActivityId(id);
        return ss;
    }



    @RequestMapping(value = RequestUrlToken.GET_TRAININGS_BY_TC_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingDTO> showAllActivities(@PathVariable Integer id)  throws JsonProcessingException {
        List<TrainingDTO> dtos = privilegedService.getTrainingsByTrainingCenterId(id);
        return dtos;
    }
    @RequestMapping(value = RequestUrlToken.CREATE_TRAINING, method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody TrainingDTO dto)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditTraining(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_TRAINING, method = RequestMethod.PUT)
    @ResponseBody
    public String saveTraining(@RequestBody TrainingDTO dto)
            throws JsonProcessingException {
        String goalId = privilegedService.createOrEditTraining(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(goalId +"  updated successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.DELETE_TRAINING, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTraining(@PathVariable Integer id)
            throws JsonProcessingException {
        String goalId = privilegedService.deleteTraining(id);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString("Training with "+goalId +"  deleted successfully");
        return val;
    }

    @RequestMapping(value = RequestUrlToken.GET_TRAINING, method = RequestMethod.GET)
    @ResponseBody
    public TrainingDTO getTraining(@PathVariable Integer id)
            throws JsonProcessingException {
       TrainingDTO dto= privilegedService.getTraining(id);
        return dto;
    }

    @RequestMapping(value = RequestUrlToken.CREATE_MEMBER, method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody MemberDTO dto)
            throws JsonProcessingException {
        String memberId = privilegedService.createOrEditApplicationMember(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(memberId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_MEMBER, method = RequestMethod.PUT)
    @ResponseBody
    public String saveMember(@RequestBody MemberDTO dto)
            throws JsonProcessingException {
        String memberId = privilegedService.createOrEditApplicationMember(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(memberId +"  edited successfully");
        return val;
    }

    @RequestMapping(value = RequestUrlToken.GET_MEMBERS_BY_TRAINING_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<MemberDTO> showAllMembers(@PathVariable Integer trainingId)
            throws JsonProcessingException {
        List<MemberDTO> ss = privilegedService.getAllMembersByTrainingId(trainingId);
        return ss;
    }
    @RequestMapping(value = RequestUrlToken.GET_MEMBER, method = RequestMethod.GET)
    @ResponseBody
    public MemberDTO getSingleMember(@PathVariable Integer id)
            throws JsonProcessingException {

        return privilegedService.getMemberByMemberId(id);
    }
    @RequestMapping(value = RequestUrlToken.DELETE_MEMBER, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMember(@PathVariable Integer id)
            throws JsonProcessingException {

        String memberId=privilegedService.deleteMember(id);

        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(memberId);
        return val;
    }


}