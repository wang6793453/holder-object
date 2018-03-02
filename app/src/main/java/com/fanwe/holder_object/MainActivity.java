package com.fanwe.holder_object;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fanwe.lib.holder.object.FObjectHolder;
import com.fanwe.lib.holder.object.FStrongObjectHolder;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 强引用对象持有者
     */
    private FObjectHolder<View> mObjectHolder = new FStrongObjectHolder<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 设置对象变化回调
         */
        mObjectHolder.setCallback(new FObjectHolder.Callback<View>()
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

        View view = mObjectHolder.get(); //获得Holder保存的对象

        findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mObjectHolder.set(new TextView(MainActivity.this)); //设置新对象给Holder
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
