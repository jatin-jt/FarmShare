package com.noobsquad.farmshare.Models;

public class Investment {

    String landOwner;
    Double ownerInvestment;

    public Investment(String landOwner, Double ownerInvestment) {
        this.landOwner = landOwner;
        this.ownerInvestment = ownerInvestment;
    }

    public String getLandOwner() {
        return landOwner;
    }

    public void setLandOwner(String landOwner) {
        this.landOwner = landOwner;
    }

    public Double getOwnerInvestment() {
        return ownerInvestment;
    }

    public void setOwnerInvestment(Double ownerInvestment) {
        this.ownerInvestment = ownerInvestment;
    }
}
