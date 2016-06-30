package com.cms.api;

import com.cms.dto.UserDTO;
import com.cms.model.*;
import com.cms.repository.IRoleDao;
import com.cms.repository.IUserDao;
import com.cms.repository.IUserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by amit on 6/16/16.
 */

@Service

public class UserApi implements IUserApi {

    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;
    @Autowired
    IUserInfoDAO userInfoDao;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public User createOrEditAppUser(UserDTO dto) {
        if (dto.getId() != 0) {
            return editUser(dto);
        } else {
            return createUser(dto);
        }

//        User user = userDao.findOne(dto.getId());
//        if (user != null) {
//            user.setId(dto.getId());
//
//        } else {
//            user = new User();
//        }
//        user.setUsername(dto.getUsername());
//        user.setPassword(getEncodedPassword(dto.getPassword()));
//        user.setStatus(Status.APPROVED);
//
//        UserInfo  user.getUserInfo() = new UserInfo();
//
//         user.getUserInfo().setFirstName(dto.getFirstName());
//         user.getUserInfo().setLastName(dto.getLastName());
//         user.getUserInfo().setMiddleName(dto.getMiddleName() + "");
//         user.getUserInfo().setLandlineNumber(dto.getLandlineNumber());
//         user.getUserInfo().setMobileNumber(dto.getMobileNumber());
//         user.getUserInfo().setStreetAddress(dto.getStreetAddress());
//         user.getUserInfo().setVdc(dto.getVdcOrMunicipality());
//         user.getUserInfo().setZone(dto.getZone());
//         user.getUserInfo().setDistrict(dto.getDistrict());
//         user.getUserInfo().setCountry(dto.getCountry());
//         user.getUserInfo().setMale(dto.isMale());
//        user.setUserInfo( user.getUserInfo());
//        Set<String> roleSet = null;
//        Set<String> databaseRoleSet = null;
//        Set<String> allRoleSet = null;
//
//        if (dto.getId() != 0) {
//            databaseRoleSet = new HashSet<String>();
//            allRoleSet = new HashSet<String>();
//            for (Role hh : userDao.findOne(dto.getId()).getRoles()) {
//                databaseRoleSet.add(hh.getRole());
//                allRoleSet.add(hh.getRole());
//            }
//
//            roleSet = new HashSet<String>();
//            for (String rr : dto.getRoless()) {
//                roleSet.add(rr);
//                allRoleSet.add(rr);
//            }
//            for (String role : allRoleSet) {
//                if (!databaseRoleSet.contains(role)) {
//                    Role ttt = new Role(role);
//                    ttt.setUser(userDao.findOne(dto.getId()));
//
//                    roleDao.save(ttt);
//                }
//
//                if (!roleSet.contains(role)) {
//                    roleDao.delete(roleDao.findRoleByUserIdAndValue(role, dto.getId()));
//                }
//
//            }
//
//
//        } else {
//            Set<Role> rr = new HashSet<Role>();
//            for (String s : dto.getRoless()) {
//                Role ttt = new Role(s);
//                ttt.setUser(userDao.findOne(dto.getId()));
//
//            }
//            user.setRoles(rr);
//        }
//        userDao.save(user);
//
//         user.getUserInfo().setUser();

    }

    private User editUser(UserDTO dto) {
        User user = userDao.findOne(dto.getId());
        user.getUserInfo().setFirstName(dto.getFirstName());
        user.getUserInfo().setLastName(dto.getLastName());
        user.getUserInfo().setMiddleName(dto.getMiddleName() + "");
        user.getUserInfo().setLandlineNumber(dto.getLandlineNumber());
        user.getUserInfo().setMobileNumber(dto.getMobileNumber());
        user.getUserInfo().setStreetAddress(dto.getStreetAddress());
        user.getUserInfo().setVdc(dto.getVdcOrMunicipality());
        user.getUserInfo().setZone(dto.getZone());
        user.getUserInfo().setDistrict(dto.getDistrict());
        user.getUserInfo().setCountry(dto.getCountry());
        user.getUserInfo().setMale(dto.isMale());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");


        Date date = null;
        try {
            date = formatter.parse(dto.getDob());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        user.getUserInfo().setDob(date);
        user.getUserInfo().setUser(user);
        userInfoDao.save(user.getUserInfo());
        userDao.save(user);
        Set<String> roleSet = new HashSet<String>();
        ;
        Set<String> databaseRoleSet = new HashSet<String>();
        ;
        Set<String> allRoleSet = new HashSet<String>();
        ;


        for (Role hh : userDao.findOne(dto.getId()).getRoles()) {
            databaseRoleSet.add(hh.getRole());
            allRoleSet.add(hh.getRole());
        }

        for (String rr : dto.getRoless()) {
            roleSet.add(rr);
            allRoleSet.add(rr);
        }
        for (String role : allRoleSet) {
            if (role != null) {

                if (!databaseRoleSet.contains(role)) {
                    Role ttt = new Role(role);
                    ttt.setUser(userDao.findOne(dto.getId()));

                    roleDao.save(ttt);
                }

                if (!roleSet.contains(role)) {
                    Role rr = roleDao.findRoleByUserIdAndValue(role, dto.getId());

                    roleDao.delete(rr.getId());
                }
            }

        }


        return user;
    }

    private User createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(getEncodedPassword(dto.getPassword()));
        user.setStatus(Status.APPROVED);

        user.getUserInfo().setFirstName(dto.getFirstName());
        user.getUserInfo().setLastName(dto.getLastName());
        user.getUserInfo().setMiddleName(dto.getMiddleName() + "");
        user.getUserInfo().setLandlineNumber(dto.getLandlineNumber());
        user.getUserInfo().setMobileNumber(dto.getMobileNumber());
        user.getUserInfo().setStreetAddress(dto.getStreetAddress());
        user.getUserInfo().setVdc(dto.getVdcOrMunicipality());
        user.getUserInfo().setZone(dto.getZone());
        user.getUserInfo().setDistrict(dto.getDistrict());
        user.getUserInfo().setCountry(dto.getCountry());
        user.getUserInfo().setMale(dto.isMale());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");


        Date date = null;
        try {
            date = formatter.parse(dto.getDob());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(formatter.format(date));

        user.getUserInfo().setDob(date);
        user.setUserInfo(user.getUserInfo());


        //user.setRoles(roles);
        User x = userDao.save(user);
        user.getUserInfo().setUser(x);
        userInfoDao.save(user.getUserInfo());
        Set<Role> roles = new HashSet<Role>();
        for (String role : dto.getRoless()) {
            if (role != null) {
                Role r = new Role(role);

                r.setUser(x);
                roles.add(r);
            }
        }
        roleDao.save(roles);

        return x;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public String deleteUser(Integer userId) {
//        UserInfo ui = userInfoDao.getUserInfoByUserId(userId);
//        userInfoDao.delete(ui.getId());
//        List<Role> roless = roleDao.findRolesByUserId(userId);
//        roleDao.delete(roless);
        userDao.delete(userId);

        return "User with ID :" + userId + "  deleted";
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findOne(id);
    }

    public String getEncodedPassword(String password) {
        return encoder.encode(password);
    }
}
