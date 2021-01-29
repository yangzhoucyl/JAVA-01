package com.netty.gateway.route;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存路由表
 * @author YangZhou
 */
public class RouteMappingSingleton {

    private static volatile RouteMappingSingleton routeMapping;

    private ConcurrentHashMap<String, List<RouteServerInfo>> routes = new ConcurrentHashMap<>();

    private RouteMappingSingleton(){
    }

    public static RouteMappingSingleton getInstance(){
        if (null == routeMapping){
            synchronized (RouteMappingSingleton.class){
                if (null == routeMapping){
                    routeMapping = new RouteMappingSingleton();
                }
            }
        }
        return routeMapping;
    }

    public ConcurrentHashMap<String, List<RouteServerInfo>> getRoutes() {
        return routes;
    }

    public void setRoutes(ConcurrentHashMap<String, List<RouteServerInfo>> routes) {
        this.routes = routes;
    }
}
