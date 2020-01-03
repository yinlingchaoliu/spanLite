package com.chaoliu.demo;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaoliu.spanlite.SpanBuilder;
import com.chaoliu.spanlite.SpanLite;

public class MainActivity extends AppCompatActivity {

    private TextView spanTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        spanTv = findViewById( R.id.spanTv );

        SpannableStringBuilder spanBuilder = SpanBuilder.Builder( "spanBuilder方法" ) //传入文字
                .drawTextBackgroundColor( Color.YELLOW )//设置文字背景色
                .drawTextBackgroundColor( "#000000" )
                .drawTextColor( Color.RED )//设置文字颜色
                .drawTextColor( "#000000" )
                .drawTypeFaceBold()//设置粗体
                .drawTypeFaceItalic()//设置斜体
                .drawTypeFaceNormal()//设置正常
                .drawTypeFaceBoldItalic()//设置粗体斜体
                .drawStrikethrough()//设置删除线
                .drawUnderline()//设置下划线
                .drawScaleX( 2 )//设置x倍数
                .drawBlurMaskFilter( 3, BlurMaskFilter.Blur.INNER )//设置模糊
                .drawDynamicDrawable( new DynamicDrawableSpan() {//设置图片
                    @Override
                    public Drawable getDrawable() {
                        Drawable drawable = getResources().getDrawable( R.mipmap.ic_launcher );
                        drawable.setBounds( 0, 0, 50, 50 );
                        return drawable;
                    }
                } )
                .drawSubscript()//设置下标
                .drawSuperscript()//设置上标
                .drawTextSizeAbsolute( 30 )//设置绝对字体大小
                .drawTextSizeRelative( 40 )//设置相对字体大小
                .drawImage( this, R.mipmap.ic_launcher )//设置图片
                .drawTextAppearance( this, R.style.AppTheme )//设置文字style
                .drawTypeface( "serif" )//设置字体
                .drawURL( "tel:18601986749" )//设置url
                .setOnClick( new SpanBuilder.Listerner() {//设置监听点击事件
                                 @Override //点击事件
                                 public void onClick(View widget) {
                                     Toast.makeText( MainActivity.this, "kakak", Toast.LENGTH_SHORT ).show();
                                 }

                                 @Override //设置更新属性
                                 public void updateDrawState(TextPaint ds) {
                                     super.updateDrawState( ds );
                                 }
                             }
                ).build();//创建


        SpanLite.with( spanTv )
                .append( SpanBuilder.Builder( "你好小妹" ).build() )
                .append( SpanBuilder.Builder( "kakakakak" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "kakak", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTypeFaceItalic().build() )
                .active();
    }
}
