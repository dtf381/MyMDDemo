package com.sunjiajia.alldemo.whew.window_curtainview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class BaseTools {
	
	public static int getWindowWidth(Context context){
		WindowManager wm = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		int mScreenWidth = dm.widthPixels;
		return mScreenWidth;
	}
	
	public static int getWindowHeigh(Context context){
		WindowManager wm = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		int mScreenHeigh = dm.heightPixels;
		return mScreenHeigh;
	}
}
