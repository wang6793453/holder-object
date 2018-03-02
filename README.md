# About
这个库的功能主要是用来做对象持有者，当对象持有者里面的对象发生变更的时候会通知回调对象，便于做一些初始化和释放的操作<br>

## 简介
FObjectHolder<T>支持的方法:
```java
FObjectHolder<T> holder = new FStrongObjectHolder<>();

holder.set(T object); //给Holder设置一个对象
T Object = holder.get(); //获得Holder里面保存的对象
```