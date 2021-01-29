package com.netty.gateway.handle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HttpHandler httpHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline line = socketChannel.pipeline();
        line.addLast(new HttpServerCodec());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(httpHandler);
    }
}
