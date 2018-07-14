package com.luit.testproject.DrawActivit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DrawActivitMain extends View {

    private Context context;
    private String path;

    public DrawActivitMain(Context context, String path) {
        super(context);
        this.context = context;
        this.path = path + "//sampledata";
    }

    @Override
    protected void onDraw(Canvas canvas) {

        TextView text = new TextView(context);
        text.setText(path);

        File file = new File(path);

        if(file.isDirectory()) {
            String[] files = file.list();

            TableRow tableRow = new TableRow(context);

            ScrollView scrollView = new ScrollView(context);

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout linearLayout1 = new LinearLayout(context);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);

            for (int i = 0; i < files.length; i++) {
                if(new File(files[i]).isDirectory()) {
                    if ((i + 1) % 3 == 0 || (i == files.length - 1)) {
                        linearLayout.addView(linearLayout1);
                        linearLayout1 = new LinearLayout(context);
                        linearLayout1.setOrientation(LinearLayout.VERTICAL);
                    }
                    LinearLayout linearLayout2 = new LinearLayout(context);
                    linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
                    ImageButton imageView = new ImageButton(context);
                    Bitmap bitmap = BitmapFactory.decodeFile(files[i] + ".png");
                    imageView.setImageBitmap(bitmap);
                    String name = new File(files[i]).getName();
                    linearLayout2.addView(imageView);
                    TextView textView = new TextView(context);
                    textView.setText(name);
                    linearLayout2.addView(textView);

                    linearLayout1.addView(linearLayout2);
                }
            }
        }
        else
        {
            ScrollView scrollView = new ScrollView(context);

            TextView textView = new TextView(context);
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String lines = "";
                while ((lines = reader.readLine()) != null){
                    builder.append(lines);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            textView.setText(Html.fromHtml(builder.toString()));

            scrollView.addView(textView);

        }

    }
}
