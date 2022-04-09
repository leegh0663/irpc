package com.ghlee.gkrpc.server;

import com.ghlee.gkrpc.codec.Request;
import com.ghlee.gkrpc.codec.common.utils.ReflectionUtils;

/**
 * 调用service的反射
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
