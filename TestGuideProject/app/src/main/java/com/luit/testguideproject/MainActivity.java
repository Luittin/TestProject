package com.luit.testguideproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.luit.testguideproject.DrawActivit.DrawActivitMain;

public class MainActivity extends AppCompatActivity {

    public String path = "sampledata";

    private int count = 0;

    public final int ACTIVITYID = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutout = (LinearLayout)findViewById(R.id.LinearLayout);

        DrawActivitMain activitMain = new DrawActivitMain(path,this,count,ACTIVITYID);
        linearLayoutout.addView(activitMain.createActivity());


    }
}
