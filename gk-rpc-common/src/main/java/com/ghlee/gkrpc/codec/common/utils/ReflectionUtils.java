package com.ghlee.gkrpc.codec.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    //创建出来是一个object 但是希望可以返回想要的类型

    /**
     * 根据class创建对象
     * @param clazz 待创建对象的类
     * @param <T>   对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz){

        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取某个class的共有方法
     * @param clazz
     * @return 当前类型名的共有方法
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList();
        for(Method m : methods){
            if(Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回结果
     */

    public static Object invoke(Object obj,
                                Method method,
                                Object... args){
        try {
            return method.invoke(obj  , args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
