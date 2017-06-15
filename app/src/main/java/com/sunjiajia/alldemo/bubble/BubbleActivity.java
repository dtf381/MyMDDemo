package com.sunjiajia.alldemo.bubble;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunjiajia.androidnewwidgetsdemo.R;


/**
 * Created by mk on 2016/11/30.
 */

public class BubbleActivity extends Activity {
    PeriscopeLayout periscopeLayout;
    private Button btn_start;
    //心形气泡

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubble);
        periscopeLayout = (PeriscopeLayout) findViewById(R.id.periscope);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用加泡泡的方法
                periscopeLayout.addHeart();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
