package com.ghlee.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 基于JSON 的反序列化实现
 */
public class JSONDecoder implements Decoder {
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
