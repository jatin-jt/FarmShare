package com.noobsquad.farmshare;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.noobsquad.farmshare.Models.Expense;
import com.noobsquad.farmshare.Models.Investment;
import com.noobsquad.farmshare.Models.Land;
import com.noobsquad.farmshare.Models.LandListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistributionActivity extends AppCompatActivity {

    ListView lvDistributions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        double profit = sharedPreferences.getFloat("revenue", 100);

        lvDistributions = findViewById(R.id.lv_distributions);



        ArrayList<LandListItem> list = new ArrayList<>();
        list.add(new LandListItem(1,"Aditya Gupta", "15 Sep 2018", "+91955555532",100.44));
        list.add(new LandListItem(2,"Jatin Gupta", "15 Sep 2018", "+919876543210",98.8));
        list.add(new LandListItem(3,"Aanya Jindal", "15 Sep 2018", "+919123456789",208.8));
        list.add(new LandListItem(4,"Sushant Jain", "15 Sep 2018", "+91955555532",150.8));
        list.add(new LandListItem(5,"Vishal Wadhwa", "15 Sep 2018", "+91955555532",110.8));
        list.add(new LandListItem(6,"Sanyam Garg", "15 Sep 2018", "+919953239367",5000.8));
        ArrayList<Expense> arrayList1 = new ArrayList<>();
        arrayList1.add(new Expense("Fertilizers", "20th August, 2018", 4000.10));
        arrayList1.add(new Expense("Pesticides", "20th August, 2018", 1500.10));
        arrayList1.add(new Expense("Seeds", "15th August, 2018", 20000.10));
        arrayList1.add(new Expense("Equipment", "25th August, 2018", 2000.10));

        for (int i = 0; i < arrayList1.size(); i++) {
            profit -= arrayList1.get(i).getExpense();
        }

        ArrayList<Investment> arrayList = new ArrayList<>();
        arrayList.add(new Investment("Aditya Gupta", 10000.0));
        arrayList.add(new Investment("Jatin Gupta", 9000.00));
        arrayList.add(new Investment("Aanya Jindal", 12000.00));
        arrayList.add(new Investment("Sushant Jain", 8000.00));
        arrayList.add(new Investment("Vishal Wadhwa", 9000.00));
        arrayList.add(new Investment("Sanyam Garg", 11000.00));
        ArrayList<Investment> finList = getDistribution(arrayList, list, profit);
        DistributionAdapter distributionAdapter = new DistributionAdapter(finList);
        lvDistributions.setAdapter(distributionAdapter);
    }

    class DistributionAdapter extends BaseAdapter {

        class Holder{
            TextView tvLandOwner;
            TextView tvOwnerDistributuon;
        }

        ArrayList<Investment> arrayList;

        public DistributionAdapter(ArrayList<Investment> arrayList) {
            this.arrayList = arrayList;
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
            LayoutInflater li = LayoutInflater.from(DistributionActivity.this);
            Holder holder = new Holder();
            if (convertView == null) {
                convertView = li.inflate(R.layout.investement_item, null);
                holder.tvLandOwner = convertView.findViewById(R.id.tv_landowner);
                holder.tvOwnerDistributuon = convertView.findViewById(R.id.tv_owner_investement);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.tvLandOwner.setText(arrayList.get(position).getLandOwner());
            holder.tvOwnerDistributuon.setText(String.format("%.2f", arrayList.get(position).getOwnerInvestment()));

            return convertView;
        }
    }

    public ArrayList<Investment> getDistribution(ArrayList<Investment> investments, ArrayList<LandListItem> ownerships, Double profit)
    {
        ArrayList<Investment> list = new ArrayList<>();
        HashMap<String, Double> hashMap = new HashMap<>();
        for (int i = 0;i<investments.size();i++)
        {
            hashMap.put(investments.get(i).getLandOwner(), investments.get(i).getOwnerInvestment());
        }

        Double sum = 0.0;
        Double liquidityFactor = 0.3;
        for (int i=0;i<ownerships.size();i++)
        {
            if(hashMap.containsKey(ownerships.get(i).getOwnerName()))
            {
                Double val = hashMap.get(ownerships.get(i).getOwnerName());
                val += liquidityFactor*ownerships.get(i).getArea();
                sum += val;
                hashMap.remove(ownerships.get(i).getOwnerName());
                hashMap.put(ownerships.get(i).getOwnerName(), val);
            }
        }

        for (HashMap.Entry<String, Double> entry : hashMap.entrySet()) {
            list.add(new Investment(entry.getKey(), (entry.getValue()/sum)*profit));
        }

        return list;
    }
}
