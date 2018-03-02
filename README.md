# About
这个库的功能主要是用来做对象持有者，当对象持有者里面的对象发生变更的时候会通知回调对象，便于做一些初始化和释放的操作<br>

## 简介
FObjectHolder<T>有以下两个实现类:
<br>
* FStrongObjectHolder<T> 强引用Holder
* FWeakObjectHolder<T>   弱引用Holder

FObjectHolder<T>支持的方法:
<br>
```java
FObjectHolder<View> holder = new FStrongObjectHolder<>(); //创建强引用Holder

holder.set(view);    //给Holder设置一个对象
View Object = holder.get(); //获得Holder里面保存的对象

holder.setCallback(new FObjectHolder.Callback<View>() //设置对象变化回调
{
    @Override
    public void onObjectSave(View object)
    {
        /**
         * 给Holder设置新对象的时候会回调此方法，object有可能为null
         * 常用来做一些初始化操作
         */
    }

    @Override
    public void onObjectRelease(View object)
    {
        /**
         * 给Holder设置新对象的时候，如果Holder保存的旧对象不为null，会回调此方法
         * 常用来做一些释放操作，如下例子
         */
    }
});
```