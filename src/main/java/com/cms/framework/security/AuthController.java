package com.cms.framework.security;

import com.cms.model.Department;
import com.cms.repository.dao.EmployeesDao;
import com.cms.service.DepartmentService;
import com.cms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nirmal on 3/22/2016.
 */
@RestController
public class AuthController {

    AuthService service ;

    public AuthController() {
        service = new AuthServiceImpl();
    }
    @RequestMapping(value = "/auth/login" ,method = RequestMethod.POST)
    public User submitLogin(Login login) {
        return service.submitLogin(login);

    }
}
