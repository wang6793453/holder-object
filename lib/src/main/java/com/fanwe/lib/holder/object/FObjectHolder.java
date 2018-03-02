package com.fanwe.lib.holder.object;

/**
 * 对象改变可以被监听的holder
 *
 * @param <T>
 */
public abstract class FObjectHolder<T>
{
    private Callback<T> mCallback;

    /**
     * 设置回调对象
     *
     * @param callback
     */
    public final void setCallback(Callback<T> callback)
    {
        mCallback = callback;
    }

    /**
     * 设置对象
     *
     * @param object
     */
    public final void set(T object)
    {
        T old = get();
        if (old != object)
        {
            if (old != null)
            {
                if (mCallback != null)
                {
                    mCallback.onObjectRelease(old);
                }
            }

            saveObject(object);

            if (mCallback != null)
            {
                mCallback.onObjectSave(object);
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

    /**
     * holder是否为空
     *
     * @return
     */
    public boolean isEmpty()
    {
        return get() == null;
    }

    public interface Callback<T>
    {
        /**
         * 新对象被holder保存，object可能为null
         *
         * @param object
         */
        void onObjectSave(T object);

        /**
         * 旧对象被holder释放，object不为null
         *
         * @param object
         */
        void onObjectRelease(T object);
    }
}
