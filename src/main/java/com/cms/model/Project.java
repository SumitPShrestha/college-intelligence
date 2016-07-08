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
@Table(name = "activity")

public class Project implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;


    private String budgetSubHeadNumber;



    private String fiscalYear;

    private String aidOrganisation;

    private double budget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBudgetSubHeadNumber() {
        return budgetSubHeadNumber;
    }

    public void setBudgetSubHeadNumber(String budgetSubHeadNumber) {
        this.budgetSubHeadNumber = budgetSubHeadNumber;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getAidOrganisation() {
        return aidOrganisation;
    }

    public void setAidOrganisation(String aidOrganisation) {
        this.aidOrganisation = aidOrganisation;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
