package com.netty.gateway.handle.strategy;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author YangZhou
 */
public interface BaseHandler {

    /**
     * 路由处理接口
     * @param request FullHttpRequest
     * @return
     */
    Object handler(FullHttpRequest request);

}
