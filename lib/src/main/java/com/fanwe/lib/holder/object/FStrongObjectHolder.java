package com.fanwe.lib.holder.object;

/**
 * 强引用对象holder
 *
 * @param <T>
 */
public class FStrongObjectHolder<T> extends FObjectHolder<T>
{
    private T mObject;

    @Override
    protected void saveObject(T object)
    {
        mObject = object;
    }

    @Override
    public T get()
    {
        return mObject;
    }
}