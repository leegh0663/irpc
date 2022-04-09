package com.ghlee.gkrpc.codec;

/**
 * 序列化
 */
public interface Encoder {
    byte[] encoder(Object obj);
}
