package com.luit.testguideproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.luit.testguideproject.DrawActivit.DrawActivitMain;

public class MenuActivity extends AppCompatActivity {

    public String path = "";

    private int count = 0;

    public final int ACTIVITYID = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        path = getIntent().getStringExtra("Path");

        LinearLayout linearLayoutout = (LinearLayout)findViewById(R.id.LinerealLayoutMenu);

        DrawActivitMain activitMain = new DrawActivitMain(path,this,count,ACTIVITYID);
        linearLayoutout.addView(activitMain.createActivity());
    }
}
