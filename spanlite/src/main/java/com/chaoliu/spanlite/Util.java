package com.chaoliu.spanlite;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * span支持工具类
 *
 * @author chentong
 * @date:2020-1-2
 */
final class Util {

    //图片置换
    public static ImageSpan drawImageSpan(Context context, @DrawableRes  int drawableRes){
        Drawable d = context.getResources().getDrawable(drawableRes);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        return new ImageSpan(d,ImageSpan.ALIGN_BASELINE);
    }

    public static ImageSpan drawImageSpanWidthHeight(Context context, @DrawableRes  int drawableRes,int width,int height){
        Drawable d = context.getResources().getDrawable(drawableRes);
        d.setBounds(0, 0, width, height);
        return new ImageSpan(d,ImageSpan.ALIGN_BASELINE);
    }

    //下划线
    public static UnderlineSpan drawUnderlineSpan(){
        return new UnderlineSpan();
    }

    //删除线
    public static StrikethroughSpan drawStrikethroughSpan(){
        return new StrikethroughSpan();
    }

    //粗体斜体
    public static StyleSpan drawTypeFaceBoldItalic(){
        return drawStyleSpan(Typeface.BOLD_ITALIC);
    }

    //粗体
    public static StyleSpan drawTypeFaceBold(){
        return drawStyleSpan(Typeface.BOLD);
    }

    //斜体
    public static StyleSpan drawTypeFaceItalic(){
        return drawStyleSpan(Typeface.ITALIC);
    }

    //正常
    public static StyleSpan drawTypeFaceNormal(){
        return drawStyleSpan(Typeface.NORMAL);
    }

    private static StyleSpan drawStyleSpan(int style){
        return new StyleSpan(style);
    }

    //字体大小
    public static AbsoluteSizeSpan drawTextSize(int size){
        return new AbsoluteSizeSpan(size);
    }

    //color字体颜色转换
    public static int getColor(String colorString) {
        return Color.parseColor( colorString );
    }

    //字体颜色
    public static ForegroundColorSpan drawTextColorSpan(int color) {
        return new ForegroundColorSpan( color );
    }

    //字体背景颜色
    public static BackgroundColorSpan drawTextBackgroundColorSpan(int color) {
        return new BackgroundColorSpan( color );
    }

    /**************************/

    //前后都不包括
    public static void setSpanExEx(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanExEx(spanBuilder, what, 0, length);
    }

    //前面不包括，后面包括
    public static void setSpanExIn(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanExIn(spanBuilder, what, 0, length);
    }

    //前面包括，后面不包括
    public static void setSpanInEx(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanInEx(spanBuilder, what, 0, length);
    }

    //前后都包括
    public static void setSpanInIn(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanInIn(spanBuilder, what, 0, length);
    }

    /**************************/

    //前后都不包括
    public static void setSpanExEx(SpannableStringBuilder spanBuilder, Object what, int start, int end) {
        spanBuilder.setSpan( what, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
    }

    //前面不包括，后面包括
    public static void setSpanExIn(SpannableStringBuilder spanBuilder, Object what, int start, int end) {
        spanBuilder.setSpan( what, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE );
    }

    //前面包括，后面不包括
    public static void setSpanInEx(SpannableStringBuilder spanBuilder, Object what, int start, int end) {
        spanBuilder.setSpan( what, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE );
    }

    //前后都包括
    public static void setSpanInIn(SpannableStringBuilder spanBuilder, Object what, int start, int end) {
        spanBuilder.setSpan( what, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
    }

    //设置span
    public static void setSpan(SpannableStringBuilder spanBuilder, Object what, int start, int end, int flags) {
        spanBuilder.setSpan( what, start, end, flags );
    }

    //span 点击事件不生效问题
    public static void setMovementMethod(TextView spanTv) {
        spanTv.setMovementMethod( LinkMovementMethod.getInstance() );
    }

    public static void setHighlightColor(TextView spanTv) {
        spanTv.setHighlightColor( Color.TRANSPARENT );
    }

}
