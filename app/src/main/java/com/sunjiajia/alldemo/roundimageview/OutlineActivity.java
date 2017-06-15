package com.sunjiajia.alldemo.roundimageview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.sunjiajia.androidnewwidgetsdemo.R;


//Android 目前仅支持通过矩形，圆形和圆角矩形轮廓进行剪切，
public class OutlineActivity extends Activity{
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView iv = new ImageView(this);
		iv.setScaleType(ImageView.ScaleType.CENTER);
		
		//提高视图以建立可见阴影
		iv.setElevation(32f);
		
		iv.setImageResource(R.drawable.img_icon);
		
		//告诉视图使用其轮廓作为剪切遮罩
		iv.setClipToOutline(true);
		
		//为剪切和阴影提供圆形视图轮廓
		iv.setOutlineProvider(new ViewOutlineProvider() {
			
			@Override
			public void getOutline(View view, Outline outline) {
				// TODO Auto-generated method stub
			
				ImageView iv = (ImageView) view;
				int radius = iv.getDrawable().getIntrinsicHeight()/2;
				int centerX = (view.getRight() - view.getLeft())/2;
				int centerY = (view.getBottom() - view.getTop())/2;
				
				outline.setOval(centerX - radius,
						centerY - radius,
						centerX + radius,
						centerY + radius);
			}
		});
	
		setContentView(iv);
	}
	

}
