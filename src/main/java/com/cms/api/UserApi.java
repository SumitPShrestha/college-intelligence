package com.cms.api;

import com.cms.dto.UserDTO;
import com.cms.model.User;
import com.cms.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amit on 6/16/16.
 */

@Service
public class UserApi implements IUserApi {

    @Autowired
    IUserDao userDao;


    @Override
    public User createAppUser(UserDTO dto) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public String deleteUser(Integer id) {
        userDao.delete(id);
        return "User with ID :" + id + "  deleted";
    }
}
