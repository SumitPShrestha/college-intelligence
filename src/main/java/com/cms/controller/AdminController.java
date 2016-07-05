package com.cms.controller;

import com.cms.dto.UserDTO;
import com.cms.service.IUserService;
import com.cms.service.RequestUrlToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
/*@Secured("ROLE_ADMIN")*/

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

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

    @RequestMapping(value = RequestUrlToken.GET_USERS, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> showAllUsers()
            throws JsonProcessingException {
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
    public String deleteExam(@PathVariable Integer id)
            throws JsonProcessingException {

        String userId=userService.deleteUser(id);

        ObjectMapper mapper = new ObjectMapper();
        String val = mapper.writeValueAsString(userId);
        return val;
    }

}