package com.cms.service;

import org.springframework.stereotype.Service;

/**
 * Created by amit on 6/16/16.
 */


public class RequestUrlToken {

    public static final String CREATE_USER = "/user";
    public static final String UPDATE_USER = "/user/{id}";

    public static final String GET_USER = "/user{id}";
    public static final String DELETE_USER = "/user{id}";
    public static final String USERS = "/users";

}
