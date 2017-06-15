package com.sunjiajia.alldemo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunjiajia.androidnewwidgetsdemo.BR;
import com.sunjiajia.androidnewwidgetsdemo.R;
import com.sunjiajia.androidnewwidgetsdemo.databinding.DatabingdingListviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 2017/2/7.
 */

public class DatabindingListView extends Activity {

    private List<Beauty> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabingdingListviewBinding binding = DataBindingUtil.setContentView(this, R.layout.databingding_listview);

        list = new ArrayList<>();
        //加载数据
        initData();

        MyBaseAdapter<Beauty> adapter = new MyBaseAdapter<>(list,R.layout.databinding_listview_item, BR.beauty,getLayoutInflater());
                binding.setAdapter(adapter);
    }
    private void initData() {
        Beauty beauty1 = new Beauty("第一个美女", "http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg");
        Beauty beauty2 = new Beauty("第二个美女", "http://difang.kaiwind.com/zhejiang/jctp/201407/18/W020140718488039321020.jpg");
        Beauty beauty3 = new Beauty("第三个美女", "http://imgsrc.baidu.com/forum/pic/item/a8ec8a13632762d006deaa12a0ec08fa503dc6bf.jpg");
        Beauty beauty4 = new Beauty("第四个美女", "http://pic.58pic.com/58pic/15/35/05/95258PICQnd_1024.jpg");
        Beauty beauty5 = new Beauty("第五个美女", "http://tupian.enterdesk.com/2013/xll/011/02/5/10.jpg");
        list.add(beauty1);
        list.add(beauty2);
        list.add(beauty3);
        list.add(beauty4);
        list.add(beauty5);
    }
}
