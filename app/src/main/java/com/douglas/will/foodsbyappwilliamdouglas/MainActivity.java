package com.douglas.will.foodsbyappwilliamdouglas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button startBtn = (Button)findViewById(R.id.activityBtn3);



        // When Start is Pressed
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ListActivity.class);
                startIntent.putExtra("com.douglas.will.foodsbyappwilliamdouglas.Tues","1");
                startActivity(startIntent);
            }
        });






    }
}
