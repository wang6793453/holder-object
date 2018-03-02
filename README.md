# About
这个库的功能主要是用来做对象持有者，当对象持有者里面的对象发生变更的时候会通知回调对象，便于做一些初始化和释放的操作<br>

## 简介
FObjectHolder<T>有以下两个实现类:
<br>
* FStrongObjectHolder<T> 强引用Holder
* FWeakObjectHolder<T>   弱引用Holder

FObjectHolder<T>的方法介绍:
<br>
```java
FObjectHolder<View> holder = new FStrongObjectHolder<>(); //创建强引用Holder
//FObjectHolder<View> holder = new FWeakObjectHolder<>();   //创建弱引用Holder

holder.set(view);    //给Holder设置一个对象
View view = holder.get(); //获得Holder里面保存的对象

holder.setCallback(new FObjectHolder.Callback<View>() //设置对象变化回调
{
    @Override
    public void onObjectSave(View object)
    {
        /**
         * 给Holder设置新对象的时候会回调此方法，object有可能为null
         * 常用来做一些初始化操作
         */
    }

    @Override
    public void onObjectRelease(View object)
    {
        /**
         * 给Holder设置新对象的时候，如果Holder保存的旧对象不为null，会回调此方法
         * 常用来做一些释放操作
         */
    }
});
```

## 简单DEMO
```java
public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 强引用对象持有者
     */
    private FObjectHolder<View> mStrongHolder = new FStrongObjectHolder<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 设置对象变化回调
         */
        mStrongHolder.setCallback(new FObjectHolder.Callback<View>()
        {
            @Override
            public void onObjectSave(View object)
            {
                /**
                 * 给Holder设置新对象的时候会回调此方法，object有可能为null
                 * 常用来做一些初始化操作，如下例子
                 */
                if (object != null)
                {
                    object.addOnAttachStateChangeListener(mOnAttachStateChangeListener);
                }
                Log.i(TAG, "onObjectSave:" + object);
            }

            @Override
            public void onObjectRelease(View object)
            {
                /**
                 * 给Holder设置新对象的时候，如果Holder保存的旧对象不为null，会回调此方法
                 * 常用来做一些释放操作，如下例子
                 */
                object.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
                Log.e(TAG, "onObjectRelease:" + object);
            }
        });

        View view = mStrongHolder.get(); //获得Holder保存的对象

        findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mStrongHolder.set(new TextView(MainActivity.this)); //设置新对象给Holder
            }
        });
    }

    private View.OnAttachStateChangeListener mOnAttachStateChangeListener = new View.OnAttachStateChangeListener()
    {
        @Override
        public void onViewAttachedToWindow(View v)
        {
        }

        @Override
        public void onViewDetachedFromWindow(View v)
        {
        }
    };
}
```