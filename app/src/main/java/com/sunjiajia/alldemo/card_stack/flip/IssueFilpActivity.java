package com.sunjiajia.alldemo.card_stack.flip;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aphidmobile.flip.FlipViewController;
import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2016/12/8.
 */

public class IssueFilpActivity extends Activity {

    private FlipViewController flipView;
    public int[] array = new int[]{R.drawable.img_icon,R.drawable.image,R.drawable.dialog_desktop,R.drawable.img_mask};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);

        flipView = new FlipViewController(this);
        flipView.setAdapter(new MyBaseAdapter(this,array));
        setContentView(flipView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flipView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flipView.onPause();
    }

    private static class MyBaseAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private int[] array;
        private Context context;

        public MyBaseAdapter(Context context,int[] array) {
            this.array = array;
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return array.length;
        }

        @Override
        public Object getItem(int position) {
            return array[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = new ImageView(context);
            }
            convertView.setBackgroundResource(array[position]);
            return convertView;

            /*if (position == 0) {
                return inflater.inflate(R.layout.activity_flip_page1, null);
            } else if (position == 1) {
                return inflater.inflate(R.layout.activity_flip_page2, null);
            } else {
                return inflater.inflate(R.layout.activity_flip_page3, null);
            }*/
        }
    }
}
