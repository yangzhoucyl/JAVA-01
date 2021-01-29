package com.netty.gateway.route.rule;

import com.netty.gateway.route.RouteServerInfo;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter{

    @Override
    public RouteServerInfo route(List<RouteServerInfo> routes) {
        if (null != routes && !routes.isEmpty()){
            int size = routes.size();
            Random random = new Random(System.currentTimeMillis());
            RouteServerInfo route = routes.get(random.nextInt(size));
            return route;
        }
        return null;
    }
}
