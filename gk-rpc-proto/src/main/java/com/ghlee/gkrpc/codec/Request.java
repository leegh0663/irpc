package com.ghlee.gkrpc.codec;

import lombok.Data;
//表示rpc的一个请求
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;

}
