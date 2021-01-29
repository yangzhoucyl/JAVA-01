package com.netty.gateway.handle.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author YangZhou
 */
public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

    private String url;

    public NettyHttpClientOutboundHandler() {
    }

    public NettyHttpClientOutboundHandler(String url) {
        this.url = url;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI(url);
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(CONNECTION, KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("msg -> " + msg);
        FullHttpResponse response = null;
        try {
            if (msg instanceof FullHttpResponse) {
                 response = (FullHttpResponse) msg;

                    ByteBuf buf = response.content();
                    String result = buf.toString(CharsetUtil.UTF_8);
                    System.out.println("response -> " + result);
                    response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK,
                            Unpooled.wrappedBuffer(result.getBytes(StandardCharsets.UTF_8)));
                    response.headers().set("Content-Type", "application/json");
                    response.headers().setInt("Content-Length", response.content().readableBytes());

            }
        } finally {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.write(response);
        }
    }


}

