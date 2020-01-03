####  一、前言
SpannableStringBuilder三方库，github怎么看都不满意，用法不够简洁。
我这边用建造者方式重新写一个，来让写法简答起来。

#### 二、SpanLite让你so easy编写spanBuilder

##### 1、引用依赖
```
allprojects {
    repositories {
        maven { url 'https://www.jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.yinlingchaoliu:spanLite:0.0.1'
}
```

##### 2、构造SpannableStringBuilder

SpanBuilder提供支持方法
```
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
```

示例demo
```
SpanBuilder.Builder( "你好小妹" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "张三丰也会下山", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTypeFaceBold().build()
```

##### 3、链式拼装SpanBuilder
```
SpanLite.with( spanTextView )
.append(spanBuilder1)
.append(spanBuilder2)
.append(spanBuilder3)
.active()
```

##### 4、实战演示

```
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

```


#### 三、编写框架方法

##### 1、编写思考路径
单一职责：不要将两步封装融为一步，这样耦合在一起的编写不简洁

##### 2、收集所有相关主要的方法
* SpannableStringBuilder和SpannableString主要通过使用
* setSpan(Object what, int start, int end, int flags)
对应的参数：
* start： 指定Span的开始位置
* end： 指定Span的结束位置，并不包括这个位置。
* flags：取值有如下四个

* Spannable. SPAN_INCLUSIVE_EXCLUSIVE：前面包括，后面不包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本不会应用该样式
* Spannable. SPAN_INCLUSIVE_INCLUSIVE：前面包括，后面包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本也会应用该样式
* Spannable. SPAN_EXCLUSIVE_EXCLUSIVE：前面不包括，后面不包括
* Spannable. SPAN_EXCLUSIVE_INCLUSIVE：前面不包括，后面包括

* what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：
* BackgroundColorSpan : 文本背景色
* ForegroundColorSpan : 文本颜色
* MaskFilterSpan : 修饰效果，如模糊(BlurMaskFilter)浮雕
* RasterizerSpan : 光栅效果
* StrikethroughSpan : 删除线
* SuggestionSpan : 相当于占位符
* UnderlineSpan : 下划线
* AbsoluteSizeSpan : 文本字体（绝对大小）
* DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
* ImageSpan : 图片
* RelativeSizeSpan : 相对大小（文本字体）
* ScaleXSpan : 基于x轴缩放
* StyleSpan : 字体样式：粗体、斜体等
* SubscriptSpan : 下标（数学公式会用到）
* SuperscriptSpan : 上标（数学公式会用到）
* TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）
* TypefaceSpan : 文本字体
* URLSpan : 文本超链接
* ClickableSpan : 点击事件

##### 3、抽象出span简单 工具类
目的：封装就是为了简写，减少不必要重复代码
```
/**
 * span支持工具类
 * @author chentong
 * @date:2020-1-2
 */
final class Util {
    public static void setSpan(){
    }
}
```

##### 4、用建造者模式对单一spanBuilder进行模型简化

目的：在工具类基础上简单组合方式
```
/**
 * span建造者模式，职责建造一个子build,便于用户清晰
 * @author chentong
 * @date:2020-1-2
 *
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
}
```

##### 5、spanLite链式调用拼装

目的：在build基础上，整体拼装方式，为了开发者简单链式调用
```
/**
 * 建造者模式,用于组装完整spanBuild 为textview使用
 *  @author chentong
 *  @date:2020-1-2
 */
public class SpanLite {

    private TextView spanTv;
    private SpannableStringBuilder spanBuilder;

    private SpanLite() {
    }

    private SpanLite(TextView textView) {
        this.spanTv = textView;
        spanBuilder = new SpannableStringBuilder();
    }

    //加入环境
    public static SpanLite with(TextView spanTv) {
        return new SpanLite( spanTv );
    }

    //拼接
    public SpanLite append(SpannableStringBuilder spanBuilder) {
        this.spanBuilder.append( spanBuilder );
        return this;
    }

    //设置超级span，用来特殊处理
    public SpanLite setSpan(Object what, int start, int end, int flags) {
        Util.setSpan( spanBuilder, what, start, end, flags );
        return this;
    }

    //生效
    public void active() {
        Util.setMovementMethod( spanTv );
        spanTv.setText( spanBuilder );
        Util.setHighlightColor( spanTv );
    }

}
```

##### 6、github开源代码
https://github.com/yinlingchaoliu/spanLite
