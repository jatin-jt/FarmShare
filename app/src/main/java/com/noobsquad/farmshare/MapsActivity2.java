package com.noobsquad.farmshare;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback{

    private static final String TAG = "MapsActivity";
    GoogleMap map;
    Polygon p,p1;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setActionBar(toolbar);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(29.336635, 76.837133)).zoom(15).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //map.addMarker(new MarkerOptions().position(new LatLng(1,1)));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        PolygonOptions po = new PolygonOptions().add(new LatLng(29.336635, 76.837133)).add(new LatLng(29.333352, 76.837121)).add(new LatLng(29.333330, 76.841979)).add(new LatLng(29.336523, 76.842021));
        po.fillColor(Color.argb(55,21,93,243));
        po.strokeColor(Color.argb(255,56,123,186));
        p = map.addPolygon(po);
        PolygonOptions po1 = new PolygonOptions().add(new LatLng(29.336585, 76.839939)).add(new LatLng(29.334950, 76.839928)).add(new LatLng(29.334995, 76.841331)).add(new LatLng(29.336564, 76.841304));
        po1.fillColor(Color.argb(55,159,39,33));
        po1.strokeColor(Color.argb(255,159,39,33));
        p1 = map.addPolygon(po1);
        //p = map.addPolyline(new PolylineOptions().add(new LatLng(0.0,0.0)).add(new LatLng(100.0,100.0)));
        //p.setColor(Color.MAGENTA);
    }



}
