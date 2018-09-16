package com.noobsquad.farmshare;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    double lat,lng;
    Button btnMark;
    GoogleMap map;
    Polygon p,p1;
    TextView tvArea;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setActionBar(toolbar);

        btnMark = findViewById(R.id.btn_mark);
        tvArea = findViewById(R.id.tv_area);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.d(TAG, "onCreate: + "+lat+" "+lng);
        btnMark.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(100,100)).zoom(12).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //map.addMarker(new MarkerOptions().position(new LatLng(1,1)));

        PolygonOptions po = new PolygonOptions().add(new LatLng(1,1)).add(new LatLng(1,100)).add(new LatLng(100,100)).add(new LatLng(100,1));
        po.fillColor(Color.argb(55,21,93,243));
        po.strokeColor(Color.argb(255,56,123,186));
        p = map.addPolygon(po);
        PolygonOptions po1 = new PolygonOptions().add(new LatLng(1,1)).add(new LatLng(1,50)).add(new LatLng(50,50)).add(new LatLng(50,1));
        po1.fillColor(Color.argb(55,159,39,33));
        po1.strokeColor(Color.argb(255,159,39,33));
        p1 = map.addPolygon(po1);
        //p = map.addPolyline(new PolylineOptions().add(new LatLng(0.0,0.0)).add(new LatLng(100.0,100.0)));
        //p.setColor(Color.MAGENTA);
    }



}
