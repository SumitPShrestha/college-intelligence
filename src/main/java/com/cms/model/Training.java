package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "training")

public class Training implements Serializable {

    @Id
    @GeneratedValue
    private int id;


    private TrainingType memberType;


    private Date startDate;

    private Date endDate;

    private int duration;

    private double budgetAllocated;

    private int targetedMember;

    private int attendantMember;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    Member trainer;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tc_id")
    TrainingCenter trainingCenter;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> roles;




}
