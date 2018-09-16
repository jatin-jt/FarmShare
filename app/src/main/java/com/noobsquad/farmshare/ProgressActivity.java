package com.noobsquad.farmshare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity {

    private static final String TAG = "ProgressActivity";

    TextView tvCrop;
    ListView lvSteps;
    Button btnAddProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tvCrop = findViewById(R.id.tv_crop);
        lvSteps = findViewById(R.id.lv_steps);
        btnAddProgress = findViewById(R.id.btn_addprogress);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");

        //2nd parameter is the progress
        StepsAdapter stepsAdapter = new StepsAdapter(arrayList, 5);
        lvSteps.setAdapter(stepsAdapter);

        btnAddProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgressActivity.this);
                LayoutInflater li = LayoutInflater.from(ProgressActivity.this);
                View vw = li.inflate(R.layout.dialog_progress,null);
                builder.setView(vw)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("sapling_level", 6);
                                EditText et = vw.findViewById(R.id.et_progress);
                                editor.putFloat("revenue",Float.parseFloat(et.getText().toString()));
                                editor.commit();
                                Log.d(TAG, "onClick: "+ sharedPref.getInt("sapling_level",5));
                                startActivity(new Intent(ProgressActivity.this, DistributionActivity.class));
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setTitle("Enter the net Profit");
                builder.create();
                builder.show();
            }
        });

    }

    class StepsAdapter extends BaseAdapter{
        class Holder{
            TextView tvStep;
        }

        ArrayList<String> arrayList;
        int progress;

        public StepsAdapter(ArrayList<String> arrayList, int progress) {
            this.arrayList = arrayList;
            this.progress = progress;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater li = LayoutInflater.from(ProgressActivity.this);
            Holder holder = new Holder();
            if (convertView == null) {
                convertView = li.inflate(R.layout.list_steps, null);
                holder.tvStep = convertView.findViewById(R.id.tv_step);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }


            holder.tvStep.setText(arrayList.get(position));
            if(position < progress)
                holder.tvStep.setTextColor(Color.GREEN);
            else
                holder.tvStep.setTextColor(Color.RED);

            return convertView;
        }
    }
}
