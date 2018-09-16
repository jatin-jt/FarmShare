package com.noobsquad.farmshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupDetailsActivity extends AppCompatActivity {

    ImageView tvLandsList, tvInvestments, tvExpenses, tvCropPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tvLandsList = findViewById(R.id.tv_landslist);
        tvCropPlans = findViewById(R.id.tv_cropplans);
        tvExpenses = findViewById(R.id.tv_expenses);
        tvInvestments = findViewById(R.id.tv_investements);

        tvLandsList.setOnClickListener((View v)->startActivity(new Intent(this,GroupLandList.class)));
        tvExpenses.setOnClickListener((View v)->startActivity(new Intent(this,ViewExpensesActivity.class)));
        tvInvestments.setOnClickListener((View v)->startActivity(new Intent(this,ViewInvestmentActivity.class)));
        tvCropPlans.setOnClickListener((View v)->startActivity(new Intent(this, CropPlanActivity.class)));
    }
}
