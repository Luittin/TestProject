package com.luit.testguideproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.luit.testguideproject.DrawActivit.DrawActivitMain;

public class ContentActivity extends AppCompatActivity {

    public String path = "";

    private int count = 0;

    public final int ACTIVITYID = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        path = getIntent().getStringExtra("Path");

        LinearLayout linearLayoutout = (LinearLayout)findViewById(R.id.LineralLayoutContent);

        DrawActivitMain activitMain = new DrawActivitMain(path,this,count,ACTIVITYID);
        linearLayoutout.addView(activitMain.createActivity());
    }
}
