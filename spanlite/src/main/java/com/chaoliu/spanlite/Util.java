package com.chaoliu.spanlite;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * span支持工具类
 *
 * @author chentong
 * @date:2020-1-2
 */
final class Util {

    //模糊效果
    public static MaskFilterSpan drawBlurMaskFilter(float radius, BlurMaskFilter.Blur style) {
        MaskFilter filter = new BlurMaskFilter( radius, style );
        return new MaskFilterSpan( filter );
    }

    //图片置换
    public static ImageSpan drawImageSpan(Context context, @DrawableRes int drawableRes) {
        return new ImageSpan( context, drawableRes );
    }

    //图片置换
    public static ImageSpan drawImageSpan(Drawable drawable ,int verticalAlignment) {
        return new ImageSpan( drawable ,verticalAlignment);
    }

    //图片置换
    public static ImageSpan drawImageDrawableSpan(Context context, @DrawableRes int drawableRes) {
        Drawable d = context.getResources().getDrawable( drawableRes );
        d.setBounds( 0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight() );
        return new ImageSpan( d );
    }

    public static ImageSpan drawImageSpanWidthHeight(Context context, @DrawableRes int drawableRes, int width, int height) {
        Drawable d = context.getResources().getDrawable( drawableRes );
        d.setBounds( 0, 0, width, height );
        return new ImageSpan( d );
    }

    //下划线
    public static UnderlineSpan drawUnderlineSpan() {
        return new UnderlineSpan();
    }

    //删除线
    public static StrikethroughSpan drawStrikethroughSpan() {
        return new StrikethroughSpan();
    }

    //粗体斜体
    public static StyleSpan drawTypeFaceBoldItalic() {
        return drawStyleSpan( Typeface.BOLD_ITALIC );
    }

    //粗体
    public static StyleSpan drawTypeFaceBold() {
        return drawStyleSpan( Typeface.BOLD );
    }

    //斜体
    public static StyleSpan drawTypeFaceItalic() {
        return drawStyleSpan( Typeface.ITALIC );
    }

    //正常
    public static StyleSpan drawTypeFaceNormal() {
        return drawStyleSpan( Typeface.NORMAL );
    }

    private static StyleSpan drawStyleSpan(int style) {
        return new StyleSpan( style );
    }

    //设置的是绝对大小
    public static AbsoluteSizeSpan drawTextSizeAbsolute(int size) {
        return new AbsoluteSizeSpan( size );
    }

    //设置的是相对大小
    public static RelativeSizeSpan drawTextSizeRelative(float size) {
        return new RelativeSizeSpan( size );
    }

    //基于X的缩放
    public static ScaleXSpan drawScaleXSpan(float proportion) {
        return new ScaleXSpan(proportion);
    }

    //上标
    public static SuperscriptSpan drawSuperscriptSpan() {
        return new SuperscriptSpan();
    }

    //下标
    public static SubscriptSpan drawSubscriptSpan() {
        return new SubscriptSpan();
    }

    //设置字体
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static TypefaceSpan drawTypefaceSpan(Typeface typeface) {
        return new TypefaceSpan(typeface);
    }

    //设置字体
    public static TypefaceSpan drawTypefaceSpan(String typeface) {
        return new TypefaceSpan(typeface);
    }

    //文本超链接
    public static URLSpan drawURLSpan(String url) {
        return new URLSpan(url);
    }

    //设置文字style
    public static TextAppearanceSpan drawTextAppearanceSpan(Context context, @StyleRes int style) {
        return new TextAppearanceSpan(context,style);
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

    //字体颜色
    public static ForegroundColorSpan drawTextColorSpan(String colorString) {
        return new ForegroundColorSpan( getColor( colorString ) );
    }

    //字体背景颜色
    public static BackgroundColorSpan drawTextBackgroundColorSpan(String colorString) {
        return new BackgroundColorSpan( getColor( colorString ) );
    }

    /**************************/

    //前后都不包括
    public static void setSpanExEx(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanExEx( spanBuilder, what, 0, length );
    }

    //前面不包括，后面包括
    public static void setSpanExIn(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanExIn( spanBuilder, what, 0, length );
    }

    //前面包括，后面不包括
    public static void setSpanInEx(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanInEx( spanBuilder, what, 0, length );
    }

    //前后都包括
    public static void setSpanInIn(SpannableStringBuilder spanBuilder, Object what) {
        int length = spanBuilder.length();
        setSpanInIn( spanBuilder, what, 0, length );
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

    //设置点击背景透明
    public static void setHighlightColor(TextView spanTv) {
        spanTv.setHighlightColor( Color.TRANSPARENT );
    }

}
