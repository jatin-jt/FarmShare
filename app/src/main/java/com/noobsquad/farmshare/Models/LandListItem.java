package com.noobsquad.farmshare.Models;

import java.util.ArrayList;

public class LandListItem {

    Integer landId;
    Integer groupId;
    Integer userId;
    //    change this
    ArrayList<Integer> vertexes;
    String ownerName;
    String date;
    double area;
    String contact;

    public LandListItem(int landid, String ownerName, String date, String contact, double area){
        this.landId = landid;
        this.ownerName = ownerName;
        this.date = date;
        this.contact = contact;
        this.area = area;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getVertexes() {
        return vertexes;
    }

    public void setVertexes(ArrayList<Integer> vertexes) {
        this.vertexes = vertexes;
    }

    public String getOwnerName(){return ownerName;}

    public String getDate(){return date;}

    public double getArea(){return area;}

    public String getContact(){return contact;}

}
