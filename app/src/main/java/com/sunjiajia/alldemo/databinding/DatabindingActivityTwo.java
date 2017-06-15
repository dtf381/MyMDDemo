package com.sunjiajia.alldemo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunjiajia.androidnewwidgetsdemo.R;
import com.sunjiajia.androidnewwidgetsdemo.databinding.DatabindingTwoBinding;

/**
 * Created by mk on 2017/2/6.
 */

public class DatabindingActivityTwo extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.databinding_two);
         DatabindingTwoBinding binding = DataBindingUtil.setContentView(this, R.layout.databinding_two);
        UserTwo user = new UserTwo("http://tupian.enterdesk.com/2013/xll/011/02/5/10.jpg");
        binding.setUserTwo(user);
    }

}
