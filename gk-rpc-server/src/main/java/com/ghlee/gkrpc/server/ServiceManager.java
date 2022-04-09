package com.ghlee.gkrpc.server;

import com.ghlee.gkrpc.codec.Request;
import com.ghlee.gkrpc.codec.ServiceDescriptor;
import com.ghlee.gkrpc.codec.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理rpc暴露的服务
 */
@Slf4j
public class ServiceManager {
    //注册服务
    private Map<ServiceDescriptor, ServiceInstance> services;
    //查找服务
    public ServiceManager(){
        this.services = new ConcurrentHashMap<ServiceDescriptor, ServiceInstance>();
    }
    public void register(Class interfaceClass, Object bean){
       Method[] methods =  ReflectionUtils.getPublicMethods(interfaceClass);
       for( Method method : methods){
           ServiceInstance sis = new ServiceInstance(bean, method);
           ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
           services.put(sdp,sis);
           log.info("register service:{} {}",sdp.getClazz(),sdp.getMethod());

       }
    }
    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);

    }
}
