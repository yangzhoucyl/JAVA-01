package com.netty.gateway.handle.constant;

/**
 * 处理类型枚举
 * @author YangZhou
 */
public enum HandlerTypeEnum {

    /**
     * 心跳检查连接
     */
    HEART_BEAT("heartBeat"),
    ROUTE_HANDLER("routeHandler"),
    REGISTER_HANDLER("registerHandler");


    private String code;

    HandlerTypeEnum(String heartBeat) {
        this.code = heartBeat;
    }

    public String getCode() {
        return code;
    }
}
