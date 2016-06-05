package com.cms.framework.security;

/**
 * Created by i82298 on 6/5/2016.
 */
public interface AuthotizationDAO {


    User findUserByUserName(Login login);
}

