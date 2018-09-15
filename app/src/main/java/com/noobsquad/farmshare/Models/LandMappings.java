package com.noobsquad.farmshare.Models;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

@DynamoDBTable(tableName = "farmshare-mobilehub-624960505-landList")
public class LandMappings {
    private String _landId;
    private ArrayList<LatLng> polygonCoordinates;

    @DynamoDBHashKey(attributeName = "landId")
    @DynamoDBAttribute(attributeName = "landId")
    public String getLandId() {
        return _landId;
    }

    public void setLandId(final String _landId) {
        this._landId = _landId;
    }

    @DynamoDBAttribute(attributeName = "latlnglist")
    public ArrayList<LatLng> getPolygonCo0rdinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(ArrayList<LatLng> polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }
}