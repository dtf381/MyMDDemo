package com.sunjiajia.alldemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sunjiajia.alldemo.Bluetooth.BluetoothActivity;
import com.sunjiajia.alldemo.SrcollView_Refresh.ScrollVieRefreshList;
import com.sunjiajia.alldemo.blur.BlurMainActivity;
import com.sunjiajia.alldemo.bubble.BubbleActivity;
import com.sunjiajia.alldemo.card_stack.Card_stackList;
import com.sunjiajia.alldemo.databinding.DatabindingList;
import com.sunjiajia.alldemo.dialog_progressbar.Dialog_ProgressBar_List;
import com.sunjiajia.alldemo.email_phone_mail.EmailPhoneMailList;
import com.sunjiajia.alldemo.highLight_view.hightsample.HighMainActivity;
import com.sunjiajia.alldemo.material_design.DesignList;
import com.sunjiajia.alldemo.okHttpList.OkHttpList;
import com.sunjiajia.alldemo.roundimageview.MaskActivity;
import com.sunjiajia.alldemo.roundimageview.MasksActivity;
import com.sunjiajia.alldemo.roundimageview.ShaderActivity;
import com.sunjiajia.alldemo.snowflake.SnowFlackActivity;
import com.sunjiajia.alldemo.spannableStringBuild.StringSpannableBuildActivity;
import com.sunjiajia.alldemo.sumulate_click.SumulateClickActivity;
import com.sunjiajia.alldemo.swipeback.swipebacksample.MainActivity;
import com.sunjiajia.alldemo.whew.WavaActivity;
import com.sunjiajia.alldemo.whew.WhewLauncherPager;
import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2016/12/2.
 */

public class MainListActivity extends ListActivity {

    String[] array;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        array = getResources().getStringArray(R.array.main_list);
        if (savedInstanceState == null) {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0://RecycleaView
                startActivity(MyActivity.class);
                break;
            case 1://snowflake下雪
                startActivity(SnowFlackActivity.class);
                break;
            case 2://气泡上升
                startActivity(BubbleActivity.class);
                break;
            case 3://水波涟漪1
                startActivity(WhewLauncherPager.class);
                break;
            case 4://水波涟漪2
                startActivity(WavaActivity.class);
                break;
            case 5://高斯模糊1
                startActivity(BlurMainActivity.class);
                break;
            case 6://自定义图片遮罩
                startActivity(ShaderActivity.class);
                break;
            case 7://图片遮罩
                startActivity(MaskActivity.class);
                break;
            case 8://图片遮罩带阴影
                startActivity(MasksActivity.class);
                break;
            case 9://自定义dialog和progressBar
                startActivity(Dialog_ProgressBar_List.class);
                break;
            case 10://图片相册滑动TinderCard
                startActivity(Card_stackList.class);
                break;
            case 11://仿微信侧滑退出Activity
                startActivity(MainActivity.class);
                break;
            case 12://ScrollView_ListView等上下拉弹性效果，以及上下拉刷新
                startActivity(ScrollVieRefreshList.class);
                break;
            case 13://蓝牙
                startActivity(BluetoothActivity.class);
                break;
            case 14://模拟点击
                startActivity(SumulateClickActivity.class);
                break;
            case 15://图文混排
                startActivity(StringSpannableBuildActivity.class);
                break;
            case 16://DataBinding 数据绑定
                startActivity(DatabindingList.class);
                break;
            case 17://RxJava_RxAndroid
                break;
            case 18://高亮功能引导页面
                startActivity(HighMainActivity.class);
                break;
            case 19://发送邮件，短信，电话
                startActivity(EmailPhoneMailList.class);
                break;
            case 20://材料设计/material_design
                startActivity(DesignList.class);
                break;
            case 21://okHttp3.6
                startActivity(OkHttpList.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
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
