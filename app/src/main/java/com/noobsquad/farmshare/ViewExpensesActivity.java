package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.noobsquad.farmshare.Models.Expense;

import java.util.ArrayList;

public class ViewExpensesActivity extends AppCompatActivity {

    ListView lvExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        lvExpenses = findViewById(R.id.lv_expenses);
        ArrayList<Expense> arrayList = new ArrayList<>();
        arrayList.add(new Expense("Fertilizers", "20th August, 2018", 4000.10));
        arrayList.add(new Expense("Pesticides", "20th August, 2018", 1500.10));
        arrayList.add(new Expense("Seeds", "15th August, 2018", 20000.10));
        arrayList.add(new Expense("Equipment", "25th August, 2018", 2000.10));

        ExpensesAdapter expensesAdapter = new ExpensesAdapter(arrayList);
        lvExpenses.setAdapter(expensesAdapter);

    }

    class ExpensesAdapter extends BaseAdapter{

        class Holder{
            TextView tvExpenseName;
            TextView tvExpenseDate;
            TextView tvExpense;
        }

        ArrayList<Expense> arrayList;

        public ExpensesAdapter(ArrayList<Expense> arrayList) {
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
            LayoutInflater li = LayoutInflater.from(ViewExpensesActivity.this);
            Holder holder = new Holder();
            if (convertView == null) {
                convertView = li.inflate(R.layout.expenses_item, null);
                holder.tvExpense = convertView.findViewById(R.id.tv_expense);
                holder.tvExpenseDate = convertView.findViewById(R.id.tv_expense_date);
                holder.tvExpenseName = convertView.findViewById(R.id.tv_expense_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.tvExpense.setText("₹ " + arrayList.get(position).getExpense().toString());
            holder.tvExpenseDate.setText(arrayList.get(position).getExpenseDate());
            holder.tvExpenseName.setText(arrayList.get(position).getExpenseName());

            return convertView;
        }
    }

}
