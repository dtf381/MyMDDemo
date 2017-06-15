package com.sunjiajia.alldemo.whew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.sunjiajia.androidnewwidgetsdemo.R;


/**
 * Created by mk on 2016/11/25.
 */

public class WhewLauncherPager extends Activity{

    private WhewView wv;
    private RoundImageView my_photo;
    private ImageView imageView ,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_whew);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        wv = (WhewView) findViewById(R.id.wv);
        my_photo = (RoundImageView) findViewById(R.id.my_photo);
        my_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.isStarting()){
                    //如果动画正在运行就停止，否则就继续执行
                    wv.stop();
                }else {
                    //执行动画
                    wv.start();
                }
            }
        });
        showAnim(imageView,-7,-7,700);
        showAnim(imageView2,-4,-4,900);
        showAnim(imageView3,7,-7,1000);
        showAnim(imageView4,16,-7,1000);
    }

    private void showAnim(View view,int x,int y,int duration){
        TranslateAnimation animation = new TranslateAnimation(0, x, 0, y);
//        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(duration);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
