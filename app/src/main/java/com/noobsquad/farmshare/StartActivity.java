package com.noobsquad.farmshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button btnMarkLand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnMarkLand = findViewById(R.id.btn_mark_land);
        btnMarkLand.setOnClickListener((View v)->{startActivity(new Intent(this,MapsActivity.class));});
    }
}
