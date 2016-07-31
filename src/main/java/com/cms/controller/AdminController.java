package com.cms.controller;

import com.cms.dto.*;
import com.cms.service.IAdminService;
import com.cms.service.IUserService;
import com.cms.service.RequestUrlToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@Secured("ROLE_ADMIN")

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = RequestUrlToken.CREATE_USER, method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody UserDTO dto)
            throws JsonProcessingException {
        String userId = userService.createOrEditApplicationUser(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_USER, method = RequestMethod.PUT)
    @ResponseBody
    public String saveUser(@RequestBody UserDTO dto)
            throws JsonProcessingException {
        String userId = userService.createOrEditApplicationUser(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  edited successfully");
        return val;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = RequestUrlToken.GET_USERS, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> showAllUsers(Principal current)
            throws JsonProcessingException {
        current.getName();
        List<UserDTO> ss = userService.getAllUsers();
        return ss;
    }
    @RequestMapping(value = RequestUrlToken.GET_USER, method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getSingleUser(@PathVariable Integer id)
            throws JsonProcessingException {

        return userService.getUser(id);
    }
    @RequestMapping(value = RequestUrlToken.DELETE_USER, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable Integer id)
            throws JsonProcessingException {

        String userId=userService.deleteUser(id);

        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId);
        return val;
    }
    @RequestMapping(value = RequestUrlToken.CREATE_PROJECT, method = RequestMethod.POST)
    @ResponseBody
    public String createProject(@RequestBody ProjectDTO dto)
            throws JsonProcessingException {
        String userId = adminService.createOrEditProject(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_PROJECT, method = RequestMethod.PUT)
    @ResponseBody
    public String editProject(@RequestBody ProjectDTO dto)
            throws JsonProcessingException {
        String userId = adminService.createOrEditProject(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  edited successfully");
        return val;
    }


    /*@RequestMapping(value = RequestUrlToken.GET_PROJECT, method = RequestMethod.GET)
    @ResponseBody
    public ProjectDTO getSingleProject(@PathVariable Integer id)
            throws JsonProcessingException {

        return adminService.getProject(id);
    }*/
    @RequestMapping(value = RequestUrlToken.DELETE_PROJECT, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteProject(@PathVariable Integer id)
            throws JsonProcessingException {

        String userId=adminService.deleteProject(id);

        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId);
        return val;
    }
    @RequestMapping(value = RequestUrlToken.CREATE_TC, method = RequestMethod.POST)
    @ResponseBody
    public String createTC(@RequestBody TrainingCenterDTO dto)
            throws JsonProcessingException {
        String userId = adminService.createOrEditTrainingCenter(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  created successfully");
        return val;
    }
    @RequestMapping(value = RequestUrlToken.UPDATE_TC, method = RequestMethod.PUT)
    @ResponseBody
    public String editTC(@RequestBody ProjectDTO dto)
            throws JsonProcessingException {
        String userId = adminService.createOrEditProject(dto);
        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId +"  edited successfully");
        return val;
    }


    /*@RequestMapping(value = RequestUrlToken.GET_PROJECT, method = RequestMethod.GET)
    @ResponseBody
    public ProjectDTO getSingleProject(@PathVariable Integer id)
            throws JsonProcessingException {

        return adminService.getProject(id);
    }*/
    @RequestMapping(value = RequestUrlToken.DELETE_TC, method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTC(@PathVariable Integer id)
            throws JsonProcessingException {

        String userId=adminService.deleteTrainingCenter(id);

        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId);
        return val;
    }
    @RequestMapping(value = RequestUrlToken.GET_TCS, method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingCenterDTO> showAllTrainingCenters()
            throws JsonProcessingException {
        List<TrainingCenterDTO> ss = adminService.getAllTrainingCenters();
        return ss;
    }
    @RequestMapping(value = RequestUrlToken.GET_PARENT_TCS, method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingCenterDTO> showAllParentTrainingCenters()
            throws JsonProcessingException {
        List<TrainingCenterDTO> ss = adminService.getAllParentTrainingCenters();
        return ss;
    }
    @RequestMapping(value = RequestUrlToken.GET_TC, method = RequestMethod.GET)
    @ResponseBody
    public TrainingCenterDTO getSingleTC(@PathVariable Integer id)
            throws JsonProcessingException {
      TrainingCenterDTO dto = adminService.getTrainingCenter(id);
        return dto;
    }
    @RequestMapping(value = RequestUrlToken.VIEW_REPORT, method = RequestMethod.GET)
    @ResponseBody
    public List<ReportDTO> getReport(@PathVariable String fiscalYear)
            throws JsonProcessingException {
        List<ReportDTO> dtos = adminService.getReportForProjectWork(fiscalYear);
        return dtos;
    }

    @RequestMapping(value = RequestUrlToken.VIEW_TRAINING_REPORT, method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingReportDTO> getTrainingReport(@PathVariable String fiscalYear)
            throws JsonProcessingException {
        List<TrainingReportDTO> dtos = adminService.getReportForTraining(fiscalYear);
        return dtos;
    }





}