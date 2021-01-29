package com.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.stereotype.Service;


/**
 * 请求头过滤器
 * @author yangzhou
 */
@Service
public class RequestHeaderFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest request) {
        request.headers().set("author", "yang");
    }
}
