package com.noobsquad.farmshare.Models;

import java.util.ArrayList;

public class CropPlan {

    Integer cropPlanId;
    String crop;
    ArrayList<String> steps;
    Integer progress;

    public CropPlan(Integer cropPlanId, String crop, ArrayList<String> steps, Integer progress) {
        this.cropPlanId = cropPlanId;
        this.crop = crop;
        this.steps = steps;
        this.progress = progress;
    }

    public Integer getCropPlanId() {
        return cropPlanId;
    }

    public void setCropPlanId(Integer cropPlanId) {
        this.cropPlanId = cropPlanId;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
