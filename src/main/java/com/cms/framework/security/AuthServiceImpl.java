package com.cms.framework.security;

/**
 * Created by i82298 on 6/5/2016.
 */
public class AuthServiceImpl implements AuthService{

    AuthotizationDAO dao;

    public AuthServiceImpl() {
        this.dao =new AtuhorizationDAOImpl("Sumit") ;
    }

    @Override
    public User submitLogin(Login login) {
      return dao.findUserByUserName(login);
    }
}
