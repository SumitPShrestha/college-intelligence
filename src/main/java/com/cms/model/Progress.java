package com.cms.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "progress")

public class Progress implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;


    private TimeFrame timeFrame;

    private String description;

    private int qty;

    private String unit;

    private String pCode;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "activity")
    private List<ActivityProgress> activityProgresses;


    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public List<ActivityProgress> getActivityProgresses() {
        return activityProgresses;
    }

    public void setActivityProgresses(List<ActivityProgress> activityProgresses) {
        this.activityProgresses = activityProgresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
