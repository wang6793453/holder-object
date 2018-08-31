package com.sd.holder_object;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sd.lib.holder.object.FStrongObjectHolder;
import com.sd.lib.holder.object.ObjectHolder;

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
