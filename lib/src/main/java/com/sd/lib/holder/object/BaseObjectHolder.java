package com.sd.lib.holder.object;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 对象改变可以被监听的holder
 *
 * @param <T>
 */
public abstract class BaseObjectHolder<T> implements ObjectHolder<T>
{
    private final List<Callback<T>> mCallbacks = new CopyOnWriteArrayList<>();

    @Override
    public synchronized final void addCallback(ObjectHolder.Callback<T> callback)
    {
        if (callback == null || mCallbacks.contains(callback))
            return;

        mCallbacks.add(callback);
    }

    @Override
    public synchronized final void removeCallback(Callback<T> callback)
    {
        mCallbacks.remove(callback);
    }

    @Override
    public synchronized final void set(T object)
    {
        final T old = get();
        if (old != object)
        {
            saveObject(object);

            for (Callback item : mCallbacks)
            {
                item.onObjectChanged(old, object);
            }
        }
    }

    /**
     * 保存对象
     *
     * @param object
     */
    protected abstract void saveObject(T object);
}
