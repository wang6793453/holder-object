/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanwe.lib.holder.object;

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
    public synchronized void addCallback(ObjectHolder.Callback<T> callback)
    {
        if (callback == null || mCallbacks.contains(callback))
            return;
        mCallbacks.add(callback);
    }

    @Override
    public synchronized void removeCallback(Callback<T> callback)
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
                item.onObjectChanged(object, old);
            }
        }
    }

    /**
     * 保存对象
     *
     * @param object
     */
    protected abstract void saveObject(T object);

    /**
     * 返回设置的对象
     *
     * @return
     */
    public abstract T get();
}
