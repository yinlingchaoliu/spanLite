package com.chaoliu.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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

        SpanLite.with( spanTv )
                .append( SpanBuilder.Builder( "你好小妹" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "张三丰也会下山", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTypeFaceBold().build() )
                .append( SpanBuilder.Builder( "kakakakak" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "kakak", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTypeFaceItalic().build() )
                .append(SpanBuilder.Builder( "《开户协议》" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "协议就要花钱", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTextColor( Color.BLUE ).drawTextBackgroundColor( Color.YELLOW ).drawTypeFaceBold().drawTextSize( 60 )
                        .build()  )
                .active();


//        SpannableStringBuilder spannableString = new SpannableStringBuilder();
//        spannableString.append("小明回复小红：你在干嘛呀。");
//        //设置字体颜色
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF0090FF"));
//        spannableString.setSpan(colorSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        spanTv.setText(spannableString);


    }
}
