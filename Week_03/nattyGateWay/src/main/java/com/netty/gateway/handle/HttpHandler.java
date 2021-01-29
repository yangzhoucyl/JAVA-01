package com.netty.gateway.handle;

import com.netty.gateway.filter.HttpRequestFilter;
import com.netty.gateway.filter.HttpResponseFilter;
import com.netty.gateway.filter.RequestHeaderFilter;
import com.netty.gateway.handle.strategy.HandlerStrategyFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author YangZhou
 */
@Service
@ChannelHandler.Sharable
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private HandlerStrategyFactory handlerStrategyFactory;

    @Autowired
    private HttpRequestFilter requestFilter;

    @Autowired
    private HttpResponseFilter responseFilter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        handleResponse(httpRequest, ctx);
        System.out.println("xxx");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void handleResponse(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        requestFilter.filter(fullRequest);
        FullHttpResponse fullHttpResponse = null;
        // okhttp
        Object response = handlerStrategyFactory.getHandler(fullRequest).handler(fullRequest);
        try{
            if (response != null){
                    fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK,
                            Unpooled.wrappedBuffer(response.toString().getBytes(StandardCharsets.UTF_8)));
                    fullHttpResponse.headers().set("Content-Type", "application/json");
                    fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
                } else {
                    fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND);
                    fullHttpResponse.headers().set("Content-Type", "application/json");
                    fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
            }
            responseFilter.filter(fullHttpResponse);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
        }
    }


    // netty
//    NettyHttpClient httpClient = new NettyHttpClient();
//        try {
//        httpClient.connect(route.getAddress(), route.getPort(), routeUri);
//        System.out.println("xxxx");
//        fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK,
//                Unpooled.wrappedBuffer("xxxx".getBytes(StandardCharsets.UTF_8)));
//        fullHttpResponse.headers().set("Content-Type", "application/json");
//        fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
//    } catch (Exception e) {
//        e.printStackTrace();
//    }finally {
//        if (fullRequest != null) {
//            if (!HttpUtil.isKeepAlive(fullRequest)) {
//                ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
//            } else {
//                fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
//                ctx.write(fullHttpResponse);
//            }
//        }
//    }
}
