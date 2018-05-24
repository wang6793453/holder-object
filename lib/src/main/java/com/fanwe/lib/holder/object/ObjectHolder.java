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
