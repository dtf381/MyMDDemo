package com.sunjiajia.alldemo.sumulate_click;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sunjiajia.androidnewwidgetsdemo.R;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * Created by mk on 2017/1/3.
 */

public class Base_SumulateClick extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_sumulateclick);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickeMe(v);
            }
        });
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                simulate(button);
            }
        },5000);

    }

    public void clickeMe(View view) {
        Toast.makeText(this, "模拟点击按钮", Toast.LENGTH_LONG).show();
    }

    public void simulate(View view) {
        setSimulateClick(button, 160, 100);
    }

    //执行模拟点击
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }


    /**
     * 执行shell命令
     *
     * @param cmd
     */
    private void execShellCmd(String cmd) {
        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
//        execShellCmd("getevent -p");
//        execShellCmd("sendevent /dev/input/event0 1 158 1");
//        execShellCmd("sendevent /dev/input/event0 1 158 0");
//        execShellCmd("input keyevent 3");//home
//        execShellCmd("input text  'helloworld!' ");
//        execShellCmd("input tap 168 252");
//        execShellCmd("input swipe 100 250 200 280");
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
