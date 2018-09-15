package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.noobsquad.farmshare.Models.LandListItem;
import com.ramotion.foldingcell.FoldingCell;
import com.ramotion.foldingcell.views.FoldingCellView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GroupLandList extends AppCompatActivity {

    ArrayList<LandListItem> list;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_land_list);
        lv = (ListView)findViewById(R.id.group_land_list);

        list = new ArrayList<>();
        list.add(new LandListItem(8976,"Aditya Gupta", "23 Aug 2018", "+91955555532",45.44));
        list.add(new LandListItem(8976,"sssssa Gupta", "23 Aug 2018", "+91955555532",99.8));

        lv.setAdapter(new GenericAdapter<LandListItem>(this, list) {
        @Override
        public View getMyView(int position, View convertView, ViewGroup parent, LandListItem pojo) {
            final LinearLayout itemView = (LinearLayout) getLayoutInflater().inflate(R.layout.group_land_list_item, null);

            TextView name1 = itemView.findViewById(R.id.land_owner);
            TextView name2 = itemView.findViewById(R.id.land_owner_unfolded);
            TextView landid1 = itemView.findViewById(R.id.land_id);
            TextView landid2 = itemView.findViewById(R.id.land_id_unfolded);
            TextView contact = itemView.findViewById(R.id.land_owner_contact);
            TextView area = itemView.findViewById(R.id.land_area);
            TextView date = itemView.findViewById(R.id.land_date_added);

            name1.setText(pojo.getOwnerName());
            name2.setText(pojo.getOwnerName());
            landid1.setText("LandId: " + String.format(Integer.toString(pojo.getLandId())));
            landid2.setText("LandId: " + String.format(Integer.toString(pojo.getLandId())));
            contact.setText("contact: " + pojo.getContact());
            area.setText("Area: " + String.format(Double.toString(pojo.getArea())));
            date.setText("DateAdded: " + pojo.getDate());

            final FoldingCell fc = itemView.findViewById(R.id.folding_cell);
            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });
            return itemView;
            }
            });
    }
}
