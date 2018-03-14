package com.douglas.will.foodsbyappwilliamdouglas;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    ListView myListView;
    String[] prices;
    String[] dec;
    ArrayList<HashMap<String, String>> formList;
    ArrayList<String> restaurantNames;
    ArrayList<String> days;
    ArrayList<String> orderBys;
    ArrayList<String> deliveryTimes;
    ArrayList<String> logoUrls;
    String geoText;
    int date;



    public String loadJSONFromAsset() {                     // makes the Json file into a long string!
        String json = null;
        try {
            InputStream is = this.getAssets().open("deliveries-sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }

        return json;
    }












    @Override
    protected void onCreate(Bundle savedInstanceState) {

        date=0;
        days=new ArrayList<String>();

        if(getIntent().hasExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues")){
            String ex = getIntent().getExtras().getString("com.douglas.will.foodsbyappwilliamdouglas.Tues");
            date= Integer.parseInt(ex);
        }



        Double lat=44.986656;
        Double lon=-93.258133;

        Geocoder geo= new Geocoder(this, Locale.getDefault());
        try{

            List<Address> addresses =geo.getFromLocation(lat, lon,1);
            String address =addresses.get(0).getAddressLine(0);
            String area =addresses.get(0).getLocality();
            String city =addresses.get(0).getAdminArea();
            String state=addresses.get(0).getSubLocality();


            geoText=(" "+city+" "+area+" "+address);




        } catch (IOException e) {
            e.printStackTrace();
        }


        try{
            JSONObject obj= new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("dropoffs");
            formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;
            restaurantNames =new ArrayList<String>();
            orderBys=new ArrayList<String>();
            deliveryTimes=new ArrayList<String>();
            logoUrls=new ArrayList<String>();

            for (int i = date-1; i <date; i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                JSONArray m_del = jo_inside.getJSONArray("deliveries");
                String dayOfWeek = jo_inside.getString("day");
                System.out.println(dayOfWeek);
                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("day", dayOfWeek);
                days.add(dayOfWeek);


                for (int a = 0; a < m_del.length(); a++) {
                    System.out.println(a);
                    JSONObject del_inside = m_del.getJSONObject(a);
                    String rName =del_inside.getString("restaurantName");
                    String orderTime =del_inside.getString("cutoff");
                    String dropTime =del_inside.getString("dropoff");
                    String logo =del_inside.getString("logoUrl");
                    System.out.println(rName);
                    restaurantNames.add(rName);
                    orderBys.add(orderTime);
                    deliveryTimes.add(dropTime);
                    logoUrls.add(logo);
                }


                formList.add(m_li);

            }
            System.out.println("loop done");







        }catch (JSONException e) {
            e.printStackTrace();}





        String[] nameOut=restaurantNames.toArray(new String[restaurantNames.size()]);
        String[] ordByOut=orderBys.toArray(new String[orderBys.size()]);
        String[] deliveryTimeOut=deliveryTimes.toArray(new String[deliveryTimes.size()]);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Resources res = getResources();
        myListView=(ListView) findViewById(R.id.myListView);


        ItemAdapter itemAdapter =new ItemAdapter(this, nameOut,deliveryTimeOut,ordByOut);
        myListView.setAdapter(itemAdapter);

        Button todayBtn = (Button)findViewById(R.id.todayBtn);
        Button tusBtn = (Button)findViewById(R.id.tusBtn);
        Button wedBtn = (Button)findViewById(R.id.wedBtn);
        Button thrsBtn = (Button)findViewById(R.id.thrsBtn);
        Button friBtn = (Button)findViewById(R.id.friBtn);
        TextView outText = (TextView) findViewById(R.id.geoText);
        outText.setText(geoText);
        //@android:color/holo_blue_light
        if(date==1){
            todayBtn.setBackgroundColor(getResources().getColor(R.color.highlightList));
        }
        if(date==2){
            tusBtn.setBackgroundColor(getResources().getColor(R.color.highlightList));
        }
        if(date==3){
            wedBtn.setBackgroundColor(getResources().getColor(R.color.highlightList));
        }
        if(date==4){
            thrsBtn.setBackgroundColor(getResources().getColor(R.color.highlightList));
        }
        if(date==5){
            friBtn.setBackgroundColor(getResources().getColor(R.color.highlightList));
        }



        // When TODAY is Pressed
        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","1");
                startActivity(startIntent);}
        });

        // When TUES is Pressed
        tusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","2");
                startActivity(startIntent);}
        });

        // When WED is Pressed
        wedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","3");
                startActivity(startIntent);}
        });
        thrsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","4");
                startActivity(startIntent);}
        });
        friBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","5");
                startActivity(startIntent);}
        });





    }
}