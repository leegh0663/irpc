package com.ghlee.gkrpc.server;

import com.ghlee.gkrpc.codec.Decoder;
import com.ghlee.gkrpc.codec.Encoder;
import com.ghlee.gkrpc.codec.Request;
import com.ghlee.gkrpc.codec.Response;
import com.ghlee.gkrpc.codec.common.utils.ReflectionUtils;
import com.ghlee.gkrpc.transport.RequestHandler;
import com.ghlee.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;


    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceInvoker = new ServiceInvoker();
        this.serviceManager = new ServiceManager();
    }


    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }
    public void stop(){
        this.net.stop();
    }
    public void start(){
        this.net.start();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            //第一步读到recive中读取数据， 第二部 用invoker调用数据 第三步 将处理的数据放到toresp
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get Request:{}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);

            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMsg("RpcServer got error:" + e.getClass().getName()  + ":" + e.getMessage());
            } finally{
                byte[] outBytes = encoder.encoder(resp);
                try {
                    toResp.write(outBytes);
                    log.info("responsed client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };


}
