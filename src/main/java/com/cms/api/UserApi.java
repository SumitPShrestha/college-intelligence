package com.cms.api;

import com.cms.dto.UserDTO;
import com.cms.model.Status;
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
    public User createOrEditAppUser(UserDTO dto) {
        User user= userDao.findOne(dto.getId());
        if(user!=null){
          user.setId(dto.getId());
        }
        else{
            user= new User();
        }
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setStatus(Status.APPROVED);

        return  userDao.save(user);
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

    @Override
    public User getUserById(Integer id) {
        return userDao.findOne(id);
    }
}
