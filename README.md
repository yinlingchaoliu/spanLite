# spanLite
SpannableString轻松创建，链式调用so easy

#### 前言
SpannableStringBuilder三方库，github怎么看都不满意，用法不够简洁。
我这边用建造者方式重新写一个，来让写法简答起来。

#### 编写思考路径
单一职责：不要将两步封装融为一步，这样融在一起的编写不简洁

##### 1、编写span 工具类
目的：封装就是为了简写，减少不必要重复代码

```
/**
 * span支持工具类
 * @author chentong
 * @date:2020-1-2
 */
final class Util {

    public static void fun(){

    }

}
```

2、编写spanBuild建造者
目的：在工具类基础上简单组合方式

```
/**
 * span建造者模式，职责建造一个子build,便于用户清晰
 * @author chentong
 * @date:2020-1-2
 *
 */
public class SpanBuilder {

}
```

3、编写spanLite拼装者
目的：在build基础上，整体拼装方式，为了开发者简单链式调用
```
/**
 * 建造者模式,用于组装完整spanBuild 为textview使用
 *  @author chentong
 *  @date:2020-1-2
 */
public class SpanLite {

    public SpanLite with(Context context){
        return this;
    }

    public SpanLite add(){
        return this;
    }

    public void build(){

    }

}
```
#### SpanLite让你so easy编写spanBuilder

##### 1、主体调用
```
SpanLite.with( spanTv )
.append(spanBuilder)
.append(spanBuilder)
...
.active()
```
##### 2、spanBuilder链式调用封装
```
SpanBuilder.Builder( "你好小妹" ).setOnClick( new SpanBuilder.Listerner() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText( MainActivity.this, "张三丰也会下山", Toast.LENGTH_SHORT ).show();
                    }
                } ).drawTypeFaceBold().build()
```
##### 3、二合一调用，写法更简单，不需要计算start end
spanLite框架自动计算

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

开源代码示例
https://github.com/yinlingchaoliu/spanLite
