package com.ghlee.gkrpc.server;

import com.ghlee.gkrpc.codec.Request;
import com.ghlee.gkrpc.codec.ServiceDescriptor;
import com.ghlee.gkrpc.codec.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager sm;
    @Before
    public void init(){
        sm = new ServiceManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method methods = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, methods);

        Request request = new Request();
        request.setService(sdp);

        ServiceInstance sis =  sm.lookup(request);
        assertNotNull(sis);

    }
}