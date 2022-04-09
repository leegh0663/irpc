package com.ghlee.gkrpc.example;

import com.ghlee.gkrpc.client.RpcClient;
import com.ghlee.gkrpc.client.RpcClientConfig;

public class Client {
    public static void main(String[] args) {
        RpcClientConfig config = new RpcClientConfig();
        RpcClient client = new RpcClient(config);
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(3,1);
        System.out.println(r1);
        System.out.println(r2);
    }
}
