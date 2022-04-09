package com.ghlee.gkrpc.codec;

import com.alibaba.fastjson.JSON;

public class JSONEncoder implements Encoder {
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
