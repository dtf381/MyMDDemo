package com.sunjiajia.alldemo.okHttpList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sunjiajia.androidnewwidgetsdemo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by mk on 2017/3/15.
 * https://github.com/hongyangAndroid/okhttputils
 * okhttp需要导入okio.jar包，okhttp.jar,okhttputils.jar
 */

public class OkHttpList extends AppCompatActivity {

    private String[] str = new String[]{
            "OkHttpGet", "OkHttpPost", "OkHttpPostJson", "OkHttpPostFile", "OkHttpPost表单上传文件"
            , "下载文件", "显示图片", "上传下载的进度显示", "HEAD,DELETE,PUT,PATCH", "同步请求"
            , "取消单个请求", "根据tag取消请求", "混淆"
    };
    ListView lv;
    LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll = new LinearLayout(this);
        setContentView(ll);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        lv = new ListView(this);
        lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout showLL = new LinearLayout(this);
        showLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        ll.addView(lv);
        ll.addView(view);
        ll.addView(showLL);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_textview, str);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
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

    //初始化OkHttp设置。
    //初始化OkhttpClient时，通过设置拦截器实现，框架中提供了一个LoggerInterceptor，当然你可以自行实现一个Interceptor 。
    private void initOKHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    //初始化cookie . 对于cookie一样，直接通过cookiejar方法配置，参考上面的配置过程。
    private void initOKHttpCookie() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
//        PersistentCookieStore //持久化cookie
//                SerializableHttpCookie //持久化cookie
//        MemoryCookieStore //cookie信息存在内存中
    }

    //对于Https,依然通过配置即可，框架中提供一个类HttpsUtils
    //同样的，框架中只是提供了几个实现类，你可以自行实现SSLSocketFactory，传入sslSocketFactory即可。
    private void initOKHttps() {
//设置可访问所有的https网站
   /*     HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);*/

        /*//设置具体的证书
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory("证书的inputstream",null,null);
        OkHttpClient okHttpClient1 = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory,sslParams1.trustManager)
        .build();
        OkHttpUtils.initClient(okHttpClient1);*/

        //双向认证
//        HttpsUtils.getSslSocketFactory(
//                证书的inputstream,
//                本地证书的inputstream,
//                本地证书的密码)


    }

    //Get请求
    private void get() {
        String url = "http://www.csdn.net/";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {

                    }
                });
    }

    //Post请求
    private void post(String url) {
        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int i) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Object o, int i) {

                    }
                });
    }

    //PostJson请求
    //提交一个Gson字符串到服务器端，注意：传递JSON的时候，不要通过addHeader去设置contentType，
    //而使用.mediaType(MediaType.parse("application/json; charset=utf-8")).。
    private void postJson(String url) {
      /*  OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(new User("zhy", "123")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback());*/
    }

    //将文件作为请求体，发送到服务器。
    private void postFile(String url, File file) {
     /*   OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(new MyStringCallback());*/
    }

    //    Post表单形式上传文件
    //支持单个多个文件，addFile的第一个参数为文件的key，即类别表单中<input type="file" name="mFile"/>的name属性。
    private void postFromFile() {
       /* OkHttpUtils.post()
                .addFile("mFile", "messenger_01.png", file)
                .addFile("mFile", "test1.txt", file2)
                .url(url)
                .params(params)
                .headers(headers)
                .execute(new MyStringCallback());*/
    }

    //下载文件
    //注意下载文件可以使用FileCallback，需要传入文件需要保存的文件夹以及文件名。
    private void downFie(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar") {

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(File file, int i) {

                    }
                });

    }

    //显示图片
    //显示图片，回调传入BitmapCallback即可。
    private void showImage(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
//                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }

    //HEAD,DELETE,PUT,PATCH
    //如果需要requestBody，例如：PUT、PATCH，自行构造进行传入。
    private void headPUT() {
       /* OkHttpUtils
                .put()
                .requestBody(RequestBody.create(null,"may be something"))
                .build()
                .execute(new MyStringCallback());*/
    }

    //同步的请求
    //execute方法不传入callback即为同步的请求，返回Response。
    private void synchronizedOkHttp(String url) {
       /* Response response = OkHttpUtils
                .get()//
                .url(url)//
                .tag(OkHttpList.this)//
                .build()//
                .execute();*/
    }

    //取消单个请求
    private void cancelOneRequest(String url) {
        RequestCall call = OkHttpUtils.get().url(url).build();
        call.cancel();
    }

    //混淆
    private void hunxiaoOkHttp() {
     /*   #okhttputils
                -dontwarn com.zhy.http.**
        -keep class com.zhy.http.**{*;}
        #okhttp
                -dontwarn okhttp3.**
        -keep class okhttp3.**{*;}
        #okio
                -dontwarn okio.**
        -keep class okio.**{*;}*/
    }

    //根据tag取消请求
    //目前对于支持的方法都添加了最后一个参数Object tag，取消则通过OkHttpUtils.cancelTag(tag)执行。
    //例如：在Activity中，当Activity销毁取消请求：
    //比如，当前Activity页面所有的请求以Activity对象作为tag，可以在onDestory里面统一取消。
    private void CancelToTag(String url) {
       /* OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//

        @Override
        protected void onDestroy()
        {
            super.onDestroy();
            //可以取消同一个tag的
            OkHttpUtils.cancelTag(this);//取消以Activity.this作为tag的请求
        }*/
    }


    private void showDialog(String content) {
        AlertDialog dialog = new AlertDialog.Builder(OkHttpList.this)
                .setMessage(content)
                .create();
        dialog.show();
    }

    //自定义CallBack
    //目前内部包含StringCallBack,FileCallBack,BitmapCallback，可以根据自己的需求去自定义Callback，
    // 例如希望回调User对象：
    public abstract class UserCallback extends Callback<com.sunjiajia.alldemo.databinding.User> {
        @Override
        public com.sunjiajia.alldemo.databinding.User parseNetworkResponse(Response response, int i) throws Exception {
            String string = response.body().string();
            com.sunjiajia.alldemo.databinding.User user = new Gson().fromJson(string, com.sunjiajia.alldemo.databinding.User.class);
            return user;
        }
    }


    private class User {
        private String name;
        private String age;

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
