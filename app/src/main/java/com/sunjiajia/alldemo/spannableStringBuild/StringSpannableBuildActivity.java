package com.sunjiajia.alldemo.spannableStringBuild;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2017/2/3.
 */
public class StringSpannableBuildActivity extends Activity  {

    private String desc = "在2015年，它还是beta版本，但是就 Android Studio 2 的 Preview 版本发展来看，Google 在这个库上还是很花心思的，我们有理由相信，在2016年 DataBinding 将会迎来第一个正式版";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(this);
        TextView textView1 = new TextView(this);
        textView1.setText(desc);
        TextView textView2 = new TextView(this);
        textView2.setText(desc);
        TextView textView3 = new TextView(this);
        textView3.setText(desc);
        TextView textView4 = new TextView(this);
        textView4.setText(desc);
        TextView textView5 = new TextView(this);
        textView5.setText(desc);
        ll.addView(textView);
        ll.addView(textView1);
        ll.addView(textView2);
        ll.addView(textView3);
        setContentView(ll);
        toggelEllipsize(textView, desc);

//        spannableStringBuilderMethod(textView1);
//        highlight(textView2, 1, 10);
//        underline(textView3, 2, 12);
//        character(textView4, 3, 13);
//        highlight(textView5, 4, 14);
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void toggelEllipsize(final TextView tv, final String desc) {
        if (desc == null) {
            return;
        }
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean isEllipsized = (tv.getTag() == null || tv.getTag().equals(false)) ? false : (Boolean) tv.getTag();
                if (isEllipsized) {
                    tv.setTag(false);
                    tv.setText(desc);
                } else {
                    tv.setTag(true);
                    int paddingleft = tv.getPaddingLeft();
                    int paddingRight = tv.getPaddingRight();
                    TextPaint paint = tv.getPaint();
                    float moreText = tv.getTextSize() * 3;
                    float availableTExtWidth = (tv.getWidth() - paddingleft - paddingRight) * 2 - moreText;

                    CharSequence ellipsizeStr = TextUtils.ellipsize(desc, paint, availableTExtWidth, TextUtils.TruncateAt.END);
                    if (ellipsizeStr.length() < desc.length()) {
                          /*String html = "<img src='game_info_lookmore'/>";
                    CharSequence charSequence = Html.fromHtml(html, new ImageGetter() {

                        @Override
                        public Drawable getDrawable(String source) {
                            Drawable drawable = getResources().getDrawable(
                                    getResourceId(source));
                            drawable.setBounds(
                                    0,
                                    0,
                                    drawable.getIntrinsicWidth()
                                            - DensityUtil.dip2px(GridGameInfoActivity.this, 3),
                                    drawable.getIntrinsicHeight()
                                            - DensityUtil.dip2px(GridGameInfoActivity.this, 1));
                            return drawable;
                        }
                    }, null);
                    ellipsizeStr = ellipsizeStr.toString() + charSequence.toString();*/

                        CharSequence temp = ellipsizeStr + ".";
                        SpannableStringBuilder ssb = new SpannableStringBuilder(temp);
                        Drawable dd = getResources().getDrawable(R.drawable.img_icon);
                        dd.setBounds(0, 0, dd.getIntrinsicWidth(), dd.getIntrinsicHeight());
                        ImageSpan is = new ImageSpan(dd, ImageSpan.ALIGN_BASELINE);
                        ssb.setSpan(is, temp.length() - 1, temp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//                        int yellow = getResources().getColor(R.color.fab_red);
//                        ssb.setSpan(new ForegroundColorSpan(yellow) - 2, ssb.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        tv.setText(ssb);
                        tv.setMovementMethod(LinkMovementMethod.getInstance());
                    } else {
                        tv.setText(desc);
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
//                    tv.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
                    tv.getViewTreeObserver().removeOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                        @Override
                        public void onGlobalFocusChanged(View oldFocus, View newFocus) {

                        }
                    });
                } else {
                    tv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    //    Android spannableStringBuilder用法整理
    private void spannableStringBuilderMethod(TextView tv) {
        SpannableString ss = new SpannableString("红色打电话斜体删除线绿色下划线图片");
        //用颜色标记文本
        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,
                //setSpan时需要指定的 flag.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用超链接标记文本
        ss.setSpan(new URLSpan("tel:454444555"), 2, 5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用样式标记文本（斜体）
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用删除线标记文本
        ss.setSpan(new StrikethroughSpan(), 7, 10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用下划线标记文本
        ss.setSpan(new UnderlineSpan(), 10, 16,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用颜色标记
        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 13,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //获取Drawable资源
        Drawable d = getResources().getDrawable(R.drawable.img_icon);
        //创建ImageSpan
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        //用ImageSpan替换文本
        ss.setSpan(span, 8, 14, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(ss);
        tv.setMovementMethod(LinkMovementMethod.getInstance());//实现文本的滚动
        //通常用于显示文字，但有时候也需要再文字中夹杂一些图片，比如qq中就可以使用表情图片，
        //又比如需要的文字高亮显示等等，如何在android中也做到这样呢？记得android中有个android.text包
        //这里提供了对文本的强大的处理功能。添加图片主要用SpannableString和ImageSpan类：
        Drawable drawable = getResources().getDrawable(R.drawable.theme_mask_icon);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //需要处理的文本，【smile】是需要被代替的文本
        SpannableString spannable = new SpannableString(tv.getText().toString() + "[smile]");
        //要让图片替代指定的文字就要用ImageSpan
        ImageSpan span1 = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        //开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
        //最后一个参数类似数学中的集合，【5,12】表示从5到12，包括5但不包括12

        spannable.setSpan(span1, tv.getText().length(), tv.getText().length() + "[smile]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);

    }

    //将需要的文字高亮显示
    public void highlight(TextView tv, int start, int end) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(tv.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //加下划线
    public void underline(TextView tv, int start, int end) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(tv.getText().toString());
        CharacterStyle span = new UnderlineSpan();
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //组合运用
    public void character(TextView tv, int start, int end) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(tv.getText().toString());
        CharacterStyle span_1 = new StyleSpan(Typeface.ITALIC);
        CharacterStyle span_2 = new ForegroundColorSpan(Color.RED);
        spannable.setSpan(span_1, start, end, spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(span_2, start, end, spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }
//    案例：带有\n换行符的字符串都可以用此方法显示两种颜色

    public SpannableStringBuilder highlight(String text, int color1, int color2, int fontSize) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span_0 = null, span_1 = null, span_2;
        int end = text.indexOf("\n");
        if (end == -1) {//如果没有换行符就使用第一种颜色显示
            span_0 = new ForegroundColorSpan(color1);
            spannable.setSpan(span_0, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            span_0 = new ForegroundColorSpan(color1);
            span_1 = new ForegroundColorSpan(color2);
            spannable.setSpan(span_0, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(span_1, end + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            span_2 = new AbsoluteSizeSpan(fontSize);//字体大小
            spannable.setSpan(span_2, end + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

}
