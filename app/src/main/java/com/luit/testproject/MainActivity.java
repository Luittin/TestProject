package com.luit.testproject;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public String path = "sampledata";

    public final int ACTIVITYID = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String path = getApplicationInfo().dataDir;

        //AssetManager assetManager = getApplicationContext().getAssets();

        LinearLayout linearLayoutout = (LinearLayout)findViewById(R.id.LinearLayout);


        String[] files = null;
        TextView text = new TextView(this);
        text.setText(path);

        linearLayoutout.addView(text);
        AssetManager myAssetManager = getApplicationContext().getAssets();

        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout linearLayout2 = null;
        Drawable d = null;
        InputStream ims = null;
        Bitmap bitmap = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("sampledata/Безымянный.jpg")));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }//process line

        } catch (IOException e) {
            e.printStackTrace();
        }

/*
            TextView textttt = new TextView(this);
            textttt.setText(builder.toString());
            linearLayoutout.addView(textttt);
*/

        //byte[] bytes = builder.toString().getBytes();
        //bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        ImageButton imageButton = new ImageButton(this);
        //imageButton.setImageBitmap(bitmap);
        //linearLayoutout.addView(imageButton);

        try {
            String list[] = myAssetManager.list(path);
            if (list != null) {
                String str = list[0].substring(list[0].lastIndexOf("."), list[0].length());
                TextView view = new TextView(this);
                view.setText(str);
                linearLayoutout.addView(view);
                if (str.equals(".png")) {
                    TextView view1 = new TextView(this);
                    view1.setText(list[0].substring(list[0].lastIndexOf("."), list[0].length()));
                    linearLayoutout.addView(view1);
                    for (int i = 0; i < list.length; ++i) {
                        if (i == list.length - 1) {
                            TextView view2 = new TextView(this);
                            view2.setText(""+list.length);
                            linearLayoutout.addView(view2);
                            linearLayoutout.addView(linearLayout1);
                            linearLayout1 = new LinearLayout(this);
                            linearLayout1.setOrientation(LinearLayout.VERTICAL);
                        }
                        linearLayout2 = new LinearLayout(this);
                        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);

                        ims = getAssets().open(list[i]);
                        d = Drawable.createFromStream(ims, null);
                        imageButton = new ImageButton(this);
                        imageButton.setImageDrawable(d);
                        imageButton.setId(ACTIVITYID + i);
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Передача имени файла как путь
                            }
                        });

                        String name = list[i].substring(0, list[i].lastIndexOf("."));
                        TextView textView = new TextView(this);
                        textView.setText(name);

                        ims.close();

                        //linearLayout2.addView(imageButton);
                        linearLayout2.addView(textView);

                        linearLayout1.addView(linearLayout2);
                    }
                    linearLayoutout.addView(linearLayout1);
                } else if (str.equals(".html")) {
                    ScrollView scrollView = new ScrollView(this);

                    TextView textView = new TextView(this);
//разобраться с путем
                    InputStream is = getAssets().open("sampledata/пнгпгнп/"+list[0]);
                    int size = is.available();
                    byte buffer[] = new byte[size];
                    is.read(buffer);
                    is.close();

                    String file = new String(buffer);

                    textView.setText(Html.fromHtml(file));

                    scrollView.addView(textView);

                    linearLayoutout.addView(scrollView);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}
