package com.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 过滤器
 * @author yangzhou
 */
public interface HttpRequestFilter {
    /**
     * 过滤器方法
     * @param request
     */
    void filter(FullHttpRequest request);
}
