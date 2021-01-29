package com.netty.gateway.handle.strategy;

import com.netty.gateway.handle.okhttp.Okhttp;
import com.netty.gateway.route.RouteMappingSingleton;
import com.netty.gateway.route.RouteServerInfo;
import com.netty.gateway.route.rule.HttpEndpointRouter;
import com.netty.gateway.route.rule.RandomHttpEndpointRouter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路由处理器
 * @author YangZhou
 */
@Service(value = "routeHandler")
public class RouteHandler implements BaseHandler{

    @Override
    public Object handler(FullHttpRequest request) {
        RouteMappingSingleton routes = RouteMappingSingleton.getInstance();
        String uri = request.uri();
        String applicationName = uri.split("/")[1];
        List<RouteServerInfo> serverInfos = routes.getRoutes().get(applicationName);
        HttpEndpointRouter httpEndpointRouter = new RandomHttpEndpointRouter();
        RouteServerInfo route = httpEndpointRouter.route(serverInfos);
        String routeUri = uri.replace("/" + applicationName, "");
        return Okhttp.httpRequest(route.getAddress() + ":" +route.getPort() + routeUri, request.content().toString(CharsetUtil.UTF_8), request.method().name());
    }
}
