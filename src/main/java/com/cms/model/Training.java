package com.cms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainingType getMemberType() {
        return memberType;
    }

    public void setMemberType(TrainingType memberType) {
        this.memberType = memberType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getBudgetAllocated() {
        return budgetAllocated;
    }

    public void setBudgetAllocated(double budgetAllocated) {
        this.budgetAllocated = budgetAllocated;
    }

    public int getTargetedMember() {
        return targetedMember;
    }

    public void setTargetedMember(int targetedMember) {
        this.targetedMember = targetedMember;
    }

    public int getAttendantMember() {
        return attendantMember;
    }

    public void setAttendantMember(int attendantMember) {
        this.attendantMember = attendantMember;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public TrainingCenter getTrainingCenter() {
        return trainingCenter;
    }

    public void setTrainingCenter(TrainingCenter trainingCenter) {
        this.trainingCenter = trainingCenter;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
