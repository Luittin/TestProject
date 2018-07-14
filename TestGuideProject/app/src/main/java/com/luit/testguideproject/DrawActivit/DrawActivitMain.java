package com.luit.testguideproject.DrawActivit;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.luit.testguideproject.ContentActivity;
import com.luit.testguideproject.MenuActivity;
import com.luit.testguideproject.PngAndroid;

import net.ellerton.japng.error.PngException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class DrawActivitMain{

    public String path;

    private Context context;

    private int count;

    public int activityID;

    public HashMap<Integer,String> map = new HashMap<>();

    public DrawActivitMain(String path, Context context, int coun, int activityID) {
        this.path = path;
        this.context = context;
        this.count = count;
        this.activityID = activityID;
    }

    public LinearLayout createActivity(){

        LinearLayout linearLayoutout = new LinearLayout(context);

        AssetManager myAssetManager = context.getApplicationContext().getAssets();

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout linearLayout2 = null;
        InputStream ims = null;

        try {
            String list[] = myAssetManager.list(path);
            for(int i = 0; i < list.length; i++){
                if(list[i].lastIndexOf(".png") > 0){
                    if(count == 3){
                        linearLayoutout.addView(linearLayout1);
                        linearLayout1 = new LinearLayout(context);
                        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                        count = 0;
                    }

                    ims = context.getAssets().open(path + "/" + list[i]);
                    Drawable drawable1 = PngAndroid.readDrawable(context, ims);
                    ImageButton iv = new ImageButton(context);
                    iv.setImageDrawable(drawable1);
                    iv.setId(activityID);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            GGGGG(v);
                        }
                    });
                    ims.close();

                    String name = list[i].substring(0, list[i].lastIndexOf("."));
                    TextView textView = new TextView(context);
                    textView.setText(name);

                    map.put(activityID,name);
                    activityID++;

                    linearLayout2 = new LinearLayout(context);
                    linearLayout2.setOrientation(LinearLayout.VERTICAL);

                    linearLayout2.addView(iv);

                    linearLayout2.addView(textView);

                    linearLayout1.addView(linearLayout2);

                    count++;
                }
                if(list[i].lastIndexOf(".html") > 0){
                    linearLayoutout.addView(getHtml(path+list[0]));
                }
            }
            if(count > 0 && count <= 3) {
                linearLayoutout.addView(linearLayout1);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PngException e) {
            e.printStackTrace();
        }

        return linearLayoutout;
    }

    public ScrollView getHtml(String path) throws IOException {
        ScrollView scrollView = new ScrollView(context);

        TextView textView = new TextView(context);

        InputStream is = context.getAssets().open(path);
        int size = is.available();
        byte buffer[] = new byte[size];
        is.read(buffer);
        is.close();

        String file = new String(buffer);

        textView.setText(Html.fromHtml(file));

        scrollView.addView(textView);

        return scrollView;
    }


    private void GGGGG(View v){
        ImageButton imageButton = (ImageButton) v;
        int Id = imageButton.getId();
        String nextPuth = path + "/" + map.get(Id);

        Log.v("1111111111111111111111","Listener");

        Intent intent = null;

        if(Id >= 1000 && Id < 2000){
            intent = new Intent(context, MenuActivity.class);
            Log.v(nextPuth,"Listener");
            intent.putExtra("Path", nextPuth);
        }
        else if(Id >= 2000 && Id < 3000)
        {
            intent = new Intent(context, ContentActivity.class);

            intent.putExtra("Path", nextPuth);
        }

        context.startActivity(intent);
    }
}
