package com.cms.dto;

import com.cms.model.TimeFrame;

/**
 * Created by amit on 7/25/16.
 */
public class ProgressDTO {

    private Integer id;


    private TimeFrame timeFrame;

    private String description;

    private int progressQty;

    private int goalQty;

    private int activityId;

    private String trainingCenter;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
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

    public int getProgressQty() {
        return progressQty;
    }

    public void setProgressQty(int progressQty) {
        this.progressQty = progressQty;
    }

    public int getGoalQty() {
        return goalQty;
    }

    public void setGoalQty(int goalQty) {
        this.goalQty = goalQty;
    }



    public String getTrainingCenter() {
        return trainingCenter;
    }

    public void setTrainingCenter(String trainingCenter) {
        this.trainingCenter = trainingCenter;
    }
}
