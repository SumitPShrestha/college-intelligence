package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_info")

public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private int id;


    private String userType;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;

    private String middleName;

    private String lastName;

    @Column(name = "gender")
    private boolean isMale;


    @Temporal(TemporalType.DATE)
    private Date dob;

    private String country;

    private String streetAddress;

    private String district;

    private String zone;

    private long landlineNumber;

    private long mobileNumber;

    private String email;

}
