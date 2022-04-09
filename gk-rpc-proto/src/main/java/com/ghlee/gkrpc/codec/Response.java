package com.ghlee.gkrpc.codec;
//表示rpc的返回

import lombok.Data;

@Data
public class Response {
    /**
     * 服务返回编码 0-成功，非0失败
     */
    private int code = 0;
    /**
     * 具体错误信息
     */
    private String msg = "ok";
    /**
     * 返回的数据
     */
    private Object data;

}
