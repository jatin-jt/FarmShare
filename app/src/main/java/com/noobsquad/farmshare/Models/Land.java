package com.noobsquad.farmshare.Models;

import java.util.ArrayList;

public class Land {

    Integer landId;
    Integer groupId;
    Integer userId;
//    change this
    ArrayList<Integer> vertexes;

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
}
