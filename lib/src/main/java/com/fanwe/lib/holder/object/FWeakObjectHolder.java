package com.fanwe.lib.holder.object;

import java.lang.ref.WeakReference;

/**
 * 弱引用对象holder
 *
 * @param <T>
 */
public class FWeakObjectHolder<T> extends FObjectHolder<T>
{
    private WeakReference<T> mWeakReference;

    @Override
    protected void saveObject(T object)
    {
        if (object != null)
        {
            mWeakReference = new WeakReference<>(object);
        } else
        {
            mWeakReference = null;
        }
    }

    @Override
    public T get()
    {
        return mWeakReference == null ? null : mWeakReference.get();
    }
}
