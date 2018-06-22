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

import java.lang.ref.WeakReference;

/**
 * 弱引用对象holder
 *
 * @param <T>
 */
public class FWeakObjectHolder<T> extends BaseObjectHolder<T>
{
    private WeakReference<T> mWeakReference;

    @Override
    protected final void saveObject(T object)
    {
        mWeakReference = object == null ? null : new WeakReference<>(object);
    }

    @Override
    public final T get()
    {
        return mWeakReference == null ? null : mWeakReference.get();
    }
}
