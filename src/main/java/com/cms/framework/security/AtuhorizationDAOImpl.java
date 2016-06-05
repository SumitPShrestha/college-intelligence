package com.cms.framework.security;

import com.cms.framework.core.BasicDAO;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i82298 on 6/5/2016.
 */
public class AtuhorizationDAOImpl extends BasicDAO implements AuthotizationDAO {
    public AtuhorizationDAOImpl(String dataSource) {
        super(dataSource);
    }

    @Override
    public User findUserByUserName(Login login) {
        String sql = buildQueryForUser(login);
        final User user = new User();
        this.simpleTemplate.getJdbcOperations().query(sql, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                user.setId(resultSet.getInt("ID"));
                user.setUserName(resultSet.getString("USERNAME"));
                user.setFullName(resultSet.getString("FULLNAME"));

            }
        });

        String sqlFK = "SELECT ROLE FROM CMS_USER_ROLE WHERE USER_ID = " + user.getId();
        this.simpleTemplate.getJdbcOperations().query(sqlFK, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setAuthority(resultSet.getString("AUTHORITY"));
                    roles.add(role);
                }
                user.setRoles(roles);


            }
        });


        return user;
    }

    private String buildQueryForUser(Login login) {
        String sql = "SELECT * FROM CMS_USER WHERE USERNAME = " + login.getUserName() + " AND PASSWORD = " + login.getPassword();
        return sql;

    }
}
