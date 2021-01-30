package com.netty.gateway.handle.strategy;

import com.alibaba.fastjson.JSONObject;
import com.netty.gateway.handle.HeartBeatConnect;
import com.netty.gateway.route.RouteMappingSingleton;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * 心跳连接处理器
 * @author YangZhou
 */
@Service(value = "heartBeat")
public class HeartbeatHandler implements BaseHandler{

    @Override
    public Object handler(FullHttpRequest request) {
        String json = request.content().toString(StandardCharsets.UTF_8);
        HeartBeatConnect registerServer = JSONObject.parseObject(json, HeartBeatConnect.class);
        System.out.println("服务心跳连接检测: [服务名: " + registerServer.getApplicationName() + "端口号: "
                + registerServer.getPort() + ", ip地址: " + registerServer.getAddress() + "]");
        if (0 != registerServer.getSendTime()){
            RouteMappingSingleton.getInstance().getRoutes().get(registerServer.getApplicationName()).forEach(server ->{
                String address = server.getAddress() + server.getPort();
                if (address.equals(registerServer.getAddress()+registerServer.getPort())){
                    server.setHeartTime(registerServer.getSendTime());
                    server.setStatus("ON");
                }
            });
        }
        return true;
    }
}
