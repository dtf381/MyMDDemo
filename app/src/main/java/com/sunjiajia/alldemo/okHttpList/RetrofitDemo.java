package com.sunjiajia.alldemo.okHttpList;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import okhttp3.Call;

/**
 * Created by mk on 2017/6/7.
 */

public class RetrofitDemo extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public interface MovieService {
//        @GET("top250")
//        Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
//    }
}
