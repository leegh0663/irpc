package com.ghlee.gkrpc.server;


import com.ghlee.gkrpc.codec.Decoder;
import com.ghlee.gkrpc.codec.Encoder;
import com.ghlee.gkrpc.codec.JSONDecoder;
import com.ghlee.gkrpc.codec.JSONEncoder;
import com.ghlee.gkrpc.transport.HttpTransportServer;
import com.ghlee.gkrpc.transport.TransportServer;
import lombok.Data;

/**
 * server 配置
 */
@Data
public class RpcServerConfig {
     //监听什么端口
    //使用什么模块的
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
