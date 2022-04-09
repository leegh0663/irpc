package com.ghlee.gkrpc.codec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    //一个类名
    private String clazz;

    //返回方法
    private String method;

    private String returnType;

    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setReturnType(method.getReturnType().getName());
        sdp.setMethod(method.getName());
        Class[] parameteClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameteClass.length];
        for (int i = 0; i < parameteClass.length; i++) {
            parameterTypes[i] = parameteClass[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public String toString() {

        return  "clazz='" + clazz +
                ", method='" + method  +
                ", returnType='" + returnType  +
                ", parameterTypes=" + Arrays.toString(parameterTypes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceDescriptor)) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }




    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
