package com.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import org.springframework.stereotype.Service;

/**
 * @author yangzhou
 */
@Service
public class ResponseHeaderFilter implements HttpResponseFilter{

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("say", "ha ha ha");
    }
}
