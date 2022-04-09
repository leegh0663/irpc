package com.ghlee.gkrpc.client;

import com.ghlee.gkrpc.codec.Peer;
import com.ghlee.gkrpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪一个server去连接
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);
    //选择一个transport与sever做交互

    TransportClient select();

    void release(TransportClient client);
    void close();
}
