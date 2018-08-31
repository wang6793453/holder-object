package com.sd.lib.holder.object;

/**
 * 强引用对象holder
 *
 * @param <T>
 */
public class FStrongObjectHolder<T> extends BaseObjectHolder<T>
{
    private T mObject;

    @Override
    protected final void saveObject(T object)
    {
        mObject = object;
    }

    @Override
    public T get()
    {
        return mObject;
    }
}
