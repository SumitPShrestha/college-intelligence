package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "member")

public class Member implements Serializable {

    @Id
    @GeneratedValue
    private int id;


    private MemberType memberType;



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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_id")
    private Training  training;

}
