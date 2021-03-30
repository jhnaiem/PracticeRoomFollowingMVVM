package com.example.practiceroom.utils;

import android.content.Context;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Util {


    public static CircularProgressDrawable getProgressDrawable(Context context) {

        CircularProgressDrawable drawable = new CircularProgressDrawable(context);

        drawable.setStrokeWidth(10f);
        drawable.setCenterRadius(50f);
        return drawable;
    }



}
