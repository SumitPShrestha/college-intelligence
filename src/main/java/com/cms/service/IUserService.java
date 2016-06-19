package com.cms.service;

import com.cms.dto.UserDTO;

import java.util.List;

/**
 * Created by amit on 6/19/16.
 */
public interface IUserService {
    String createApplicationUser(UserDTO dto);

    List<UserDTO> getAllUsers();


    String deleteUser(Integer id);
}
