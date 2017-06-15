package com.sunjiajia.alldemo.highLight_view.hightsample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.sunjiajia.alldemo.highLight_view.hightlight.HighLight;
import com.sunjiajia.androidnewwidgetsdemo.R;


/**
 */
public class SecondActivity extends Activity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlight_second);
        view = findViewById(R.id.iv_hight);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                addhightView();
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        findViewById(R.id.bt_go_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThreeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addhightView() {
        //使用默认的设置
        HighLight highLight = new HighLight(this)
                .setMyBroderType(HighLight.MyType.DASH_LINE)//使用实线
                .addHighLight(R.id.iv_hight, R.layout.high_layout_hight, new HighLight.OnPosCallback() {
                    @Override
                    public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                        marginInfo.rightMargin = rightMargin;
                        marginInfo.bottomMargin = bottomMargin + view.getHeight();
                    }
                }, HighLight.MyShape.CIRCULAR);//圆形高亮
        highLight.show();
    }
}
