package com.noobsquad.farmshare.Models;

import android.support.v4.util.Pair;

import java.util.ArrayList;

public class Group {

    Integer groupId;
    ArrayList<Integer> landIds;
//    user, investement
    ArrayList<Pair<Integer, Double> > investements;
//    user, percentage in profit
    ArrayList<Pair<Integer, Double> > ownership;
    Integer groupOwnerId;
    Integer cropPlanId;
    Double totalArea;
    Double expectedYield;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public ArrayList<Integer> getLandIds() {
        return landIds;
    }

    public void setLandIds(ArrayList<Integer> landIds) {
        this.landIds = landIds;
    }

    public ArrayList<Pair<Integer, Double>> getInvestements() {
        return investements;
    }

    public void setInvestements(ArrayList<Pair<Integer, Double>> investements) {
        this.investements = investements;
    }

    public ArrayList<Pair<Integer, Double>> getOwnership() {
        return ownership;
    }

    public void setOwnership(ArrayList<Pair<Integer, Double>> ownership) {
        this.ownership = ownership;
    }

    public Integer getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(Integer groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public Integer getCropPlanId() {
        return cropPlanId;
    }

    public void setCropPlanId(Integer cropPlanId) {
        this.cropPlanId = cropPlanId;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public Double getExpectedYield() {
        return expectedYield;
    }

    public void setExpectedYield(Double expectedYield) {
        this.expectedYield = expectedYield;
    }
}
