package com.sunjiajia.alldemo.highLight_view.hightsample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.sunjiajia.alldemo.highLight_view.hightlight.HighLight;
import com.sunjiajia.androidnewwidgetsdemo.R;


/**
 *
 */
public class ThreeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlight_threeview);
        addViewToLayout();
    }

    private void addViewToLayout() {
        HighLight highLight = new HighLight(this)
                .setOnClickCallback(new HighLight.OnClickCallback() {
                    @Override
                    public void onClick() {
                        Toast.makeText(ThreeActivity.this,"覆盖层被点击了",Toast.LENGTH_SHORT).show();
                    }
                }).addLayout(R.layout.layout_three);
    }
}
