package com.sunjiajia.alldemo.roundimageview;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.sunjiajia.androidnewwidgetsdemo.R;


//在运行Android 5.0 及其更高版本的设备，Android 框架支持通过动态阴影表明视图的提高
//（通过elevation和translationZ属性）。为使该功能正常运作，框架必须了解视图的可是边界。在简单的
//示例中，可以在内部进行此处理。但如果应用任意遮罩，则还必须使用匹配的ViewOutlineProvider
//指示在何处产生阴影
public class MasksActivity extends Activity{
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView iv = new ImageView(this);
		iv.setScaleType(ImageView.ScaleType.CENTER);
		//创建并加载图片（通常是不可变得）
		Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.img_icon);
		Bitmap mask = BitmapFactory.decodeResource(getResources(), R.drawable.img_mask);
		
		//创建可变位置，并且在其中绘制Canvas
		final Bitmap result = Bitmap.createBitmap(source.getWidth(),source.getHeight(),Config.ARGB_8888);
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		
		canvas.drawBitmap(mask, 0, 0,paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(source, 0, 0,paint);
		paint.setXfermode(null);
		
		iv.setImageBitmap(result);
		//----
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
			//提高视图以建立可见阴影
			iv.setElevation(32f);
			//绘制匹配遮罩的轮廓，从面提供适当的阴影
			iv.setOutlineProvider(new ViewOutlineProvider() {
				
				@Override
				public void getOutline(View view, Outline outline) {
					// TODO Auto-generated method stub
					int x = (view.getWidth() - result.getWidth())/2;
					int y = (view.getHeight() - result.getHeight())/2;
					
					Path path = new Path();
					path.moveTo(x, y);
					path.lineTo(x+result.getWidth(), y);
					path.lineTo(x+result.getWidth()/2, y+result.getHeight());
					path.lineTo(x, y);
					path.close();
					
					outline.setConvexPath(path);
				}
			});
			
		}
		setContentView(iv);
		
	}

}
