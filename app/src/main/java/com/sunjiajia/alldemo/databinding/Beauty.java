package com.sunjiajia.alldemo.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by mk on 2017/2/7.
 */

public class Beauty {
    public String imageUrl;
    public String beautyNum;

    public Beauty(String beautyNum, String imageUrl) {
        this.beautyNum = beautyNum;
        this.imageUrl = imageUrl;
    }

    @BindingAdapter({"imageUrl"})
    public static void beautyImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
