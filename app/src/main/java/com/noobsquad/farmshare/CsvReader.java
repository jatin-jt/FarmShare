package com.noobsquad.farmshare;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader {

    ArrayList<String[] > alldat = new ArrayList<>();
    ArrayList<String[] > price = new ArrayList<>();

    public ArrayList<ArrayList<String>> findit(Context context, String state, String district, String season){

        BufferedReader br = null;
        InputStream is;
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        state = state.toLowerCase();
        district = district.toLowerCase();
        season = season.toLowerCase();

        try {
            String sCurrentLine;
            is = context.getResources().openRawResource(R.raw.apy);
            br = new BufferedReader(new InputStreamReader(is));
            while ((sCurrentLine = br.readLine()) != null) {
                alldat.add(sCurrentLine.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        try {
            String sCurrentLine;
            is = context.getResources().openRawResource(R.raw.datafile);
            br = new BufferedReader(new InputStreamReader(is));
            sCurrentLine = br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                price.add(sCurrentLine.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Log.d("test",alldat.get(0)[2]);
        Log.d("test",Integer.toString(alldat.size()));

        int count = 0;
        for(String[] rec : alldat){
            if(count<50){
                if(rec[0].toLowerCase().trim().equals(state)){
                    if(rec[1].toLowerCase().trim().equals(district)){
                        //Log.d("test","distrcit mapped");
                        if(rec[2].trim().equals("2012")){
                            //Log.d("test","year mapped");
                            if(rec[3].toLowerCase().trim().equals(season)){
                                Log.d("test","season mapped");
                                ans.add(new ArrayList<String>(Arrays.asList(rec)));
                                count++;
                            }
                        }
                    }
                }
            }
        }

        Log.d("test",Integer.toString(count));
        Log.d("test",Integer.toString(price.size()));
        Log.d("test",price.get(198)[0].toLowerCase() + state);

        ArrayList<ArrayList<String>> f = new ArrayList<>();

        for(int i=0;i<ans.size();i++){
            ArrayList<String> rec2 = ans.get(i);
            for(String[] rec : price){
                if(rec[0].toLowerCase().trim().equals(state)){
                    if(rec[1].toLowerCase().trim().equals(district)){
                        Log.d("test",rec[3].toLowerCase().trim() + rec2.get(4).toLowerCase().trim());
                        if(rec[3].toLowerCase().trim().equals(rec2.get(4).toLowerCase().trim())){
                            Log.d("test",Integer.toString(rec2.size()));
                            rec2.add(Double.toString(1.0*Integer.parseInt(rec2.get(6))/Integer.parseInt(rec2.get(5))*Integer.parseInt(rec[8])));
                            Log.d("test",Integer.toString(rec2.size()));
                            f.add(rec2);
                            break;
                        }
                    }
                }
            }
        }

        return f;
    }

}
