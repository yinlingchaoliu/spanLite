package com.chaoliu.spanlite;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * span建造者模式，职责建造一个子build,便于用户清晰
 *
 * @author chentong
 * @date:2020-1-2
 */
public class SpanBuilder {

    private SpannableStringBuilder spanBuilder;
    private String content;

    private SpanBuilder() {
    }

    private SpanBuilder(String content) {
        this.content = content;
        spanBuilder = new SpannableStringBuilder( content );
    }

    public static SpanBuilder Builder(String content) {
        return new SpanBuilder( content );
    }

    public SpanBuilder drawImage(Context context, @DrawableRes int drawableRes) {
        Util.setSpanExEx( spanBuilder, Util.drawImageSpan( context, drawableRes ) );
        return this;
    }

    public SpanBuilder drawImageWidthHeight(Context context, @DrawableRes int drawableRes, int width, int height) {
        Util.setSpanExEx( spanBuilder, Util.drawImageSpanWidthHeight( context, drawableRes, width, height ) );
        return this;
    }

    //下划线
    public SpanBuilder drawUnderline() {
        Util.setSpanExEx( spanBuilder, Util.drawUnderlineSpan() );
        return this;
    }

    //删除线
    public SpanBuilder drawStrikethrough() {
        Util.setSpanExEx( spanBuilder, Util.drawStrikethroughSpan() );
        return this;
    }

    //粗体斜体
    public SpanBuilder drawTypeFaceBoldItalic() {
        Util.setSpanExEx( spanBuilder, Util.drawTypeFaceBoldItalic() );
        return this;
    }

    //粗体
    public SpanBuilder drawTypeFaceBold() {
        Util.setSpanExEx( spanBuilder, Util.drawTypeFaceBold() );
        return this;
    }

    //斜体
    public SpanBuilder drawTypeFaceItalic() {
        Util.setSpanExEx( spanBuilder, Util.drawTypeFaceItalic() );
        return this;
    }

    //正常
    public SpanBuilder drawTypeFaceNormal() {
        Util.setSpanExEx( spanBuilder, Util.drawTypeFaceNormal() );
        return this;
    }

    //字体大小
    public SpanBuilder drawTextSize(int size) {
        Util.setSpanExEx( spanBuilder, Util.drawTextSize( size ) );
        return this;
    }

    //字体颜色
    public SpanBuilder drawTextColor(int color) {
        Util.setSpanExEx( spanBuilder, Util.drawTextColorSpan( color ) );
        return this;
    }

    //字体背景颜色
    public SpanBuilder drawTextBackgroundColor(int color) {
        Util.setSpanExEx( spanBuilder, Util.drawTextBackgroundColorSpan( color ) );
        return this;
    }

    //字体背景颜色
    public SpanBuilder setOnClickAndUpdate(final UpdateListerner listerner) {

        Util.setSpanExEx( spanBuilder, new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (listerner != null) {
                    listerner.onClick( widget );
                }
            }

            public void updateDrawState(TextPaint ds) {
                /**set textColor**/
                ds.setColor( ds.linkColor );
                /**Remove the underline**/
                ds.setUnderlineText( false );
                if (listerner != null) {
                    listerner.updateDrawState( ds );
                }
            }
        } );

        return this;
    }

    //字体背景颜色
    public SpanBuilder setOnClick(final Listerner listerner) {

        Util.setSpanExEx( spanBuilder, new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (listerner != null) {
                    listerner.onClick( widget );
                }
            }

            public void updateDrawState(TextPaint ds) {
                /**set textColor**/
                ds.setColor( ds.linkColor );
                /**Remove the underline**/
                ds.setUnderlineText( false );
            }
        } );

        return this;
    }

    public SpannableStringBuilder build() {
        return spanBuilder;
    }

    public interface UpdateListerner {
        void onClick(View widget);

        void updateDrawState(TextPaint ds);
    }

    public interface Listerner {
        void onClick(View widget);
    }

}
