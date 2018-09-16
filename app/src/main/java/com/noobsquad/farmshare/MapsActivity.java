package com.noobsquad.farmshare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.noobsquad.farmshare.Models.LandMappings;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private static final String TAG = "MapsActivity";
    ArrayList<LatLng> vertices;
    Boolean started = false;
    LocationManager locationManager;
    double lat,lng;
    Button btnMark;
    Location location;
    GoogleMap map;
    Polyline p;
    Handler handler = new Handler();
    DynamoDBMapper dynamoDBMapper;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setActionBar(toolbar);

        vertices = new ArrayList<>();
        btnMark = findViewById(R.id.btn_mark);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();

        }
        Log.d(TAG, "onCreate: + "+lat+" "+lng);
        btnMark.setOnClickListener((View v)-> startLogging());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat,lng)).zoom(17).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.addMarker(new MarkerOptions().position(new LatLng(1,1)));
        PolylineOptions po = new PolylineOptions().add(new LatLng(1,1)).add(new LatLng(50,50));
        p = map.addPolyline(po);
        //p = map.addPolyline(new PolylineOptions().add(new LatLng(0.0,0.0)).add(new LatLng(100.0,100.0)));
        //p.setColor(Color.MAGENTA);
    }

    Runnable tracker = new Runnable() {
        public void run() {
            vertices.add(new LatLng(lat,lng));
            p.setPoints(vertices);
            Log.d(TAG,  vertices.toString());
            handler.postDelayed(tracker,1000);
        }
    };

    public void startLogging()
    {
        if(!started) {
            Log.d(TAG, "startLogging: haha");
            handler.postDelayed(tracker,1000);
            btnMark.setText("Stop");
            started = true;
        } else {
            Log.d(TAG, "startLogging: lol");
            handler.removeCallbacks(tracker);
            PolygonOptions po = new PolygonOptions().addAll(vertices);
            map.addPolygon(po).setFillColor(Color.GREEN);
            LandMappings landMappings = new LandMappings();
            landMappings.setLandId("haha");
            landMappings.setPolygonCoordinates(vertices);
            dynamoDBMapper.save(landMappings);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
