package com.ghlee.gkrpc.example;

import com.ghlee.gkrpc.server.RpcServer;
import com.ghlee.gkrpc.server.RpcServerConfig;

public class Server {
    public static void main(String[] args) {

        RpcServerConfig config = new RpcServerConfig();
        RpcServer server = new RpcServer(config);
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
