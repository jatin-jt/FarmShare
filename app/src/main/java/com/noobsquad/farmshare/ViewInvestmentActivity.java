package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.noobsquad.farmshare.Models.Investment;

import java.util.ArrayList;

public class ViewInvestmentActivity extends AppCompatActivity {

    ListView lvInvestements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_investment);

        lvInvestements = findViewById(R.id.lv_investments);
        ArrayList<Investment> arrayList = new ArrayList<>();
        arrayList.add(new Investment("Aditya Gupta", 10000.0));
        arrayList.add(new Investment("Jatin Gupta", 9000.00));
        arrayList.add(new Investment("Aanya Jindal", 12000.00));
        arrayList.add(new Investment("Sushant Jain", 8000.00));
        arrayList.add(new Investment("Vishal Wadhwa", 9000.00));
        arrayList.add(new Investment("Sanyam Garg", 11000.00));

        InvestmentAdapater investmentAdapater = new InvestmentAdapater(arrayList);
        lvInvestements.setAdapter(investmentAdapater);
    }

    class InvestmentAdapater extends BaseAdapter{

        class Holder{
            TextView tvLandOwner;
            TextView tvOwnerInvestment;
        }

        ArrayList<Investment> arrayList;

        public InvestmentAdapater(ArrayList<Investment> arrayList) {
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
            LayoutInflater li = LayoutInflater.from(ViewInvestmentActivity.this);
            Holder holder = new Holder();
            if (convertView == null) {
                convertView = li.inflate(R.layout.investement_item, null);
                holder.tvLandOwner = convertView.findViewById(R.id.tv_landowner);
                holder.tvOwnerInvestment = convertView.findViewById(R.id.tv_owner_investement);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.tvLandOwner.setText(arrayList.get(position).getLandOwner());
            holder.tvOwnerInvestment.setText(arrayList.get(position).getOwnerInvestment().toString());

            return convertView;
        }
    }
}
