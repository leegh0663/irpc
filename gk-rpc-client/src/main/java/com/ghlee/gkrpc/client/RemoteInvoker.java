package com.ghlee.gkrpc.client;

import com.ghlee.gkrpc.codec.*;
import com.ghlee.gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response resp = invokeRemote(request);
        if( resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote:" );
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response resp = null;
        try{
            client = selector.select();
            byte[] outBytes = encoder.encoder(request);
            InputStream recive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(recive, recive.available());
            resp = decoder.decode(inBytes, Response.class);


        }catch (IOException e){
            e.printStackTrace();
            log.warn(e.getMessage());
            resp = new Response();
            resp.setCode(1);
            resp.setMsg("RPCcLIENT GOT ERROR:" + e.getClass() + ":" + e.getMessage());
        }finally {
            if(client != null){
                selector.release(client);
            }
        }
        return resp;
    }
}
