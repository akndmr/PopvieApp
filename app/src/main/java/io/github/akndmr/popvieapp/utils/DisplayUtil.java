package io.github.akndmr.popvieapp.utils;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.akndmr.popvieapp.R;

/**
 * Created by AKIN on 25.02.2018.
 */

public class DisplayUtil {

    // Adjusts number of columns of GridLayout based on screen density
    // Reference: https://stackoverflow.com/a/38472370
    public static int calculateNoOfColumns(Context context, int itemWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / itemWidthDp);
    }

    // Sets a custom font for ActionBar title
    // Reference: https://acomputerengineer.wordpress.com/2016/08/21/use-custom-font-in-title-in-toolbaraction-bar-in-android/
    public static void setCustomActionBarTitle(Context c, ActionBar actionBar) {
        TextView tv = new TextView(c);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(c.getString(R.string.app_name));
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = Typeface.createFromAsset(c.getAssets(), "fonts/pacifico.otf");
        tv.setTypeface(tf);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(tv);
    }


 /*   public static int calculateNoOfColumns(Context context, int itemWidthPixels) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float itemWidthDp = convertPixelsToDp(itemWidthPixels, context);
        int noOfColumns = (int) (dpWidth / itemWidthDp);
        return noOfColumns;
    }*/
}
