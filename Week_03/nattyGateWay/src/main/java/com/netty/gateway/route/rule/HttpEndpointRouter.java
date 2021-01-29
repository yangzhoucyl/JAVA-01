package com.netty.gateway.route.rule;

import com.netty.gateway.route.RouteServerInfo;

import java.util.List;

/**
 * @author YangZhou
 */
public interface HttpEndpointRouter {

    /**
     * 网关路由算法
     * @param routes 路由服务端地址
     * @return 请求地址
     */
    RouteServerInfo route(List<RouteServerInfo> routes);
}
