# About
这个库的功能主要是用来做对象持有者，当对象持有者里面的对象发生变更的时候会通知回调对象，便于做一些初始化和释放的操作<br>

目前的实现类有：
* FStrongObjectHolder<T> 强引用Holder
* FWeakObjectHolder<T>   弱引用Holder

# Gradle
`implementation 'com.fanwe.android:holder-object:1.0.3'`

# 简单demo
```java
public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 强引用对象持有者
     */
    private ObjectHolder<View> mObjectHolder = new FStrongObjectHolder<>();

    /**
     * 弱引用对象持有者
     */
//    private ObjectHolder<View> mObjectHolder = new FWeakObjectHolder<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mObjectHolder.set(new TextView(MainActivity.this)); //设置新对象给Holder
            }
        });

        initObjectHolder();
    }

    private void initObjectHolder()
    {
        /**
         * 添加回调监听
         */
        mObjectHolder.addCallback(new ObjectHolder.Callback<View>()
        {
            @Override
            public void onObjectChanged(View newObject, View oldObject)
            {
                Log.i(TAG, "onObjectChanged:" + "\r\n" + newObject + "\r\n" + oldObject);
                if (oldObject != null)
                {
                    // 移除旧对象的监听
                    oldObject.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
                }

                if (newObject != null)
                {
                    // 添加新对象的监听
                    newObject.addOnAttachStateChangeListener(mOnAttachStateChangeListener);
                }
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

# 接口
```java
public interface ObjectHolder<T>
{
    /**
     * 添加回调
     *
     * @param callback
     */
    void addCallback(Callback<T> callback);

    /**
     * 移除回调
     *
     * @param callback
     */
    void removeCallback(Callback<T> callback);

    /**
     * 设置对象
     *
     * @param object
     */
    void set(T object);

    /**
     * 获得对象
     *
     * @return
     */
    T get();

    interface Callback<T>
    {
        /**
         * 对象变更回调
         *
         * @param newObject 新设置的对象，可能为null
         * @param oldObject 旧的对象，可能为null
         */
        void onObjectChanged(T newObject, T oldObject);
    }
}
```