package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class RiskEstimation {
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskEstimation() {

    }

    public RiskEstimation(String name) {
        this.name = name;
    }
}
