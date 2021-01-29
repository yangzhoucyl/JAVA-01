package com.netty.gateway.handle.strategy;

import com.alibaba.fastjson.JSONObject;
import com.netty.gateway.handle.HeartBeatConnect;
import com.netty.gateway.route.RouteMappingSingleton;
import com.netty.gateway.route.RouteServerInfo;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务注册处理器
 * @author YangZhou
 */
@Component(value = "registerHandler")
public class ServerRegisterHandler implements BaseHandler{
    @Override
    public Object handler(FullHttpRequest request) {
        String json = request.content().toString(StandardCharsets.UTF_8);
        HeartBeatConnect registerServer = JSONObject.parseObject(json, HeartBeatConnect.class);
        System.out.println("服务注册连接接: [服务名: " + registerServer.getApplicationName() + "端口号: "
                + registerServer.getPort() + ", ip地址: " + registerServer.getAddress() + "]");
        if (!StringUtils.isEmpty(registerServer.getApplicationName())){
            RouteServerInfo serverInfo = new RouteServerInfo(registerServer.getApplicationName(),
                    registerServer.getPort(), registerServer.getAddress(), registerServer.getSendTime(), "ON", 0);
            RouteMappingSingleton routeMapping = RouteMappingSingleton.getInstance();
            List<RouteServerInfo> servers = routeMapping.getRoutes().get(registerServer.getApplicationName());
            if (servers == null || servers.isEmpty()){
                servers = new ArrayList<>();
                servers.add(serverInfo);
                routeMapping.getRoutes().put(serverInfo.getApplicationName(), servers);
            }else {
                routeMapping.getRoutes().get(serverInfo.getApplicationName()).add(serverInfo);
            }
        }
        return true;
    }
}
