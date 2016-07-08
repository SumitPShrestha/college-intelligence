package com.cms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "activity")

public class Activity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;


    private double budget;

    private String activityHead;

    private String expenseHead;
    private String unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getActivityHead() {
        return activityHead;
    }

    public void setActivityHead(String activityHead) {
        this.activityHead = activityHead;
    }

    public String getExpenseHead() {
        return expenseHead;
    }

    public void setExpenseHead(String expenseHead) {
        this.expenseHead = expenseHead;
    }




}
