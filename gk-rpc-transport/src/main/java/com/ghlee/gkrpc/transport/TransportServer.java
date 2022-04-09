package com.ghlee.gkrpc.transport;

/**
 * 1,启动 监听端口
 * 2 等待网络客户端来连接，收到请求做处理
 * 3 关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();
    void stop();
}
