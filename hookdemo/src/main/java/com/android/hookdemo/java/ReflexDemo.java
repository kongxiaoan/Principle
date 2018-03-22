package com.android.hookdemo.java;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by mr.kong on 2017/11/16.
 * Java反射
 */

public class ReflexDemo {
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //获得Class对象的方法之一
        Class<?> c = Class.forName("className");
        //获得实现接口
        Class<?>[] interfaces = c.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.print(interfaces[i].getName() + ""); //getName获取到的是接口的名称
        }
        //获得父类
        Class<?> superclass = c.getSuperclass();
        String name = superclass.getName();//获得父类的名称

        //获得构造方法
        Constructor<?>[] constructors = c.getConstructors();//获得公开的构造方法
        Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();//获取全部的构造方法
        for (int i = 0; i < constructors.length; i++) {
            String modifier = Modifier.toString(constructors[i].getModifiers());// 获得访问权限
            String name1 = constructors[i].getName();//构造方法的名称
            Class<?>[] parameterTypes = constructors[i].getParameterTypes();//获得参数类型对象
        }

        //获得Method
        Method[] methods = c.getMethods();//返回公共的全部的方法 包括继承的方法
        Method[] declaredMethods = c.getDeclaredMethods(); //返回本类的全部的方法
        for (int i = 0; i < methods.length; i++) {
            Class<?> returnType = methods[i].getReturnType(); //获取返回值类型
            String name1 = methods[i].getName(); //方法的名称
            String s = Modifier.toString(methods[i].getModifiers());//访问权限
            Class<?>[] exceptionTypes = methods[i].getExceptionTypes();//获得异常
            String name2 = exceptionTypes[i].getName(); //获的异常的名称
        }

        //获得Field
        Field[] fields = c.getFields(); //获得公共属性 包括继承的属性
        Field[] declaredFields = c.getDeclaredFields(); //获得本类的全部属性
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType(); //获取属性的类型对象
            String name1 = fields[i].getName();
            String s = Modifier.toString(fields[i].getModifiers());
        }


        //-----------------------------------例子 通过反射创建一个对象 -----------
        //1
        Class<?> cl = Class.forName("Person");
        Person person = (Person) cl.newInstance();
        //2
        Constructor<?> constructor = cl.getConstructor();
        Object o = constructor.newInstance(""); //根据构造函数创建对象

        //3
        Class<?> aClass = Class.forName("Person");
        Constructor<?>[] constructor1 = aClass.getConstructors();
        Person person1 = (Person) constructor1[0].newInstance("kpa", 15);

        Person newInstance = (Person) aClass.newInstance();

        //如果是有私有的构造函数就先设置aClass.setAccessible(true)

        //调用特定的方法

        //Method m = c1.getMethod("funcname",Class<?>...c);   //funcname表示调用方法的名称，c表示参数的Class对象
        Method show = aClass.getMethod("show", String.class, int.class);//表示函数 show(String,int)
        //Object obj = m.invoke(c1.newInstance(),"xiazdong",20);   //如果有返回值，则invoke函数返回；
        //注：如果是调用静态的方法，则不需要设置对象；
        //Object obj = m.invoke(null,"xiazdong");

        //注：如果参数中有数组，比如 public static void main(String[]args);则
        //Method m = c1.getMethod("main",String[].class);
        //m.invoke(null,(Object)new String[]{"aa","bb"});是对的；
        //m.invoke(null,new String[]{"aa","bb"}); 会调用 main(String,String);函数；

        //调用特定的属性
//        Field f = c1.getDeclaredField("name");    //返回name属性
//        f.setAccessible(true);    //私有属性可见
//        String name = (String)f.get(Object obj);   //返回obj对象的name属性的值
//        f.set(Object obj,String n);      //设置obj对象的name属性为n值；
        //操作数组

//        int tmp[] = {1,2,3};
//        Class<?> c  = tmp.getClass().getComponentType();
//        Array.getLength(tmp);        //tmp数组的长度
//        c.getName();           //获得数组类型名称
//        Array.get(Object obj,int index);      //获得obj数组的index索引的数值
//        Array.set(Object obj,int index,VALUE);    //设置obj数组的index索引的数值为value；
//        Object obj  = Array.newInstance(c,length);          //c为数组的类型，length为数组的长度；obj为返回的数组对象；

    }
}
