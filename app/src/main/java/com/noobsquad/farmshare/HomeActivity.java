package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    ImageView ivProgress;
    TextView daysToHarvest, expectedYield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        daysToHarvest = findViewById(R.id.tv_daystoharvest);
        expectedYield = findViewById(R.id.tv_expectedyield);
        ivProgress = findViewById(R.id.iv_progress);


    }
}
