package com.android.hookdemo.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by mr.kong on 2017/11/16.
 * 动态代理的Demo
 */

public class DynamicProxyDemo {
    public static void main(String[] args) {
        //代理目标对象
        ProxyInterface targetObject = new TargetObject();
        //代理器
        ProxyObject proxyObject = new ProxyObject();
        proxyObject.setTargetObject(targetObject);
        Object newProxyInstance = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), proxyObject);
        ((ProxyInterface) newProxyInstance).proxyMethod();
    }
}

interface ProxyInterface {
    void proxyMethod();
}

class TargetObject implements ProxyInterface {

    @Override
    public void proxyMethod() {
        System.out.println("将要被代理的对象");
    }
}

class ProxyObject implements InvocationHandler {
    //代码对象
    public Object targetObject;

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用 传入了一个目标对象 ，和对应的对象的参数
        System.out.println("代理之前 你可以做的事情");
        Object invoke = method.invoke(targetObject, args);
        System.out.println("代理之后 你可以做的事情");
        return invoke;
    }
}
