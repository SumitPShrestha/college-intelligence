package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "enable", nullable = true)
    private boolean enabled;
    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> roles;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public User() {
    }

    public User(final String username, final String password,
                final boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(final String username, final String password,
                final boolean enabled, final Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
