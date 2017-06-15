package com.sunjiajia.alldemo.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by mk on 2017/2/6.
 */

public class UserTwo {
    public String imageUrl;

    public UserTwo(String imageUrl) {
        this.imageUrl = imageUrl;
    }

/*
     * 使用ImageLoader显示图片
     *
     * @param imageView
     * @param url*/

    @BindingAdapter({"imageUrl"})
    public static void imageLoader(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
