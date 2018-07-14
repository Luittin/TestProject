package com.luit.testguideproject.DrawActivit;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MarkupHorizontalActivity implements MarkupActiviti {

    @Override
    public LinearLayout getMarkup(LinearLayout linearLayout, ImageButton imageButton, TextView textView) {
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(imageButton);
        linearLayout.addView(textView);
        return linearLayout;
    }
}
