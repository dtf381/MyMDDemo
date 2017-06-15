package com.sunjiajia.alldemo.roundimageview;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.sunjiajia.androidnewwidgetsdemo.R;


public class ShaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RoundedCornerImageView iv = new RoundedCornerImageView(this);
		Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.img_icon);
		iv.setImage(source);
		setContentView(iv);
	}
}
