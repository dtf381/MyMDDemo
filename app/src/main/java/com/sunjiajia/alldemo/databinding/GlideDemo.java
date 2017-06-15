package com.sunjiajia.alldemo.databinding;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by mk on 2017/2/11.
 */

public class GlideDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        setContentView(imageView);
        Glide.with(this).load("http://tupian.enterdesk.com/2013/xll/011/02/5/10.jpg").into(imageView);
    }
}
