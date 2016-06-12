package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_info")

public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_type", nullable = true)
    private String userType;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "fk_user_id")
    private User user;
    // @Column(name = "name", unique = true, nullable = false, length = 45)
    // private String name;
    // @Column(name = "gender")
    // private boolean isMale;
    // @Column(name = "dob")
    // @Temporal(TemporalType.DATE)
    // private Date dob;
    // @Column(name = "country")
    // private String country;
    // @Column(name = "p_address", nullable = false, length = 60)
    // private String parmanentAddress;
    // @Column(name = "t_address", nullable = false, length = 60)
    // private String temproryAddress;
    // @Column(name = "phone_no_home", nullable = true)
    // private long phoneNumberHome;
    // @Column(name = "phone_no_mobie", nullable = true)
    // private long phoneNumberMobile;
}
