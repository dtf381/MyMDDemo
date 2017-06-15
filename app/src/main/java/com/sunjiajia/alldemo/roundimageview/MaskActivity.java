package com.sunjiajia.alldemo.roundimageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.widget.ImageView;

import com.sunjiajia.androidnewwidgetsdemo.R;


//5.0之下处理图片遮罩样子
public class MaskActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView iv = new ImageView(this);
		
		//创建并加载图片
	Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.author);
	Bitmap mask = BitmapFactory.decodeResource(getResources(), R.drawable.theme_mask_icon);
		source = Bitmap.createScaledBitmap(source,mask.getWidth(),mask.getHeight(),false);
	//创建一个可修改的位置以及一个在其中绘制的Canvas
//	Bitmap result = Bitmap.createBitmap(source.getWidth(),source.getHeight(),Config.ARGB_8888);
	Bitmap result = Bitmap.createBitmap(mask.getWidth(),mask.getHeight(),Config.ARGB_8888);
	Canvas canvas = new Canvas(result);
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	paint.setColor(Color.BLACK);
	
	canvas.drawBitmap(mask,0,0,paint);
	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	canvas.drawBitmap(source, 0, 0,paint);
	paint.setXfermode(null);
	
	iv.setImageBitmap(result);
	setContentView(iv);
	}

	/*
	图片压缩
	BitmapFactory.Options newOpts = new BitmapFactory.Options();
	// 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
	newOpts.inJustDecodeBounds = true;
	newOpts.inPreferredConfig = Config.RGB_565;
	// Get bitmap info, but notice that bitmap is null now
	Bitmap bitmap = BitmapFactory.decodeFile(imgPath,newOpts);

	newOpts.inJustDecodeBounds = false;
	int w = newOpts.outWidth;
	int h = newOpts.outHeight;
	// 想要缩放的目标尺寸
	float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
	float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
	// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
	int be = 1;//be=1表示不缩放
	if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
		be = (int) (newOpts.outWidth / ww);
	} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
		be = (int) (newOpts.outHeight / hh);
	}
	if (be <= 0) be = 1;


	// 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
	bitmap = BitmapFactory.decodeFile(imgPath, newOpts);*/
}
