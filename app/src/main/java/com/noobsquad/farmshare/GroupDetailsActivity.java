package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class GroupDetailsActivity extends AppCompatActivity {

    TextView tvLandslist, tvInvestments, tvExpenses, tvCropPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);


        tvLandslist = findViewById(R.id.tv_landslist);
        tvCropPlans = findViewById(R.id.tv_cropplans);
        tvExpenses = findViewById(R.id.tv_expenses);
        tvInvestments = findViewById(R.id.tv_investements);



    }
}
