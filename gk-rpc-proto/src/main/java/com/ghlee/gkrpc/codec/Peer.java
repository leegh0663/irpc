package com.ghlee.gkrpc.codec;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 */
@Data
@AllArgsConstructor //所有字段的构造方法
public class Peer {
    private String host;
    private int port;
}
