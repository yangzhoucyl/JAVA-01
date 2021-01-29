package com.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 响应头过滤
 * @author yangzhou
 */
public interface HttpResponseFilter {

    /**
     * 过滤器方法
     * @param response
     */
    void filter(FullHttpResponse response);
}
