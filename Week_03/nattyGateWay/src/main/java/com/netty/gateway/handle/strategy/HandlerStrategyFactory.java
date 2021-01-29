package com.netty.gateway.handle.strategy;

import com.alibaba.fastjson.JSON;
import com.netty.gateway.handle.constant.HandlerTypeEnum;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YangZhou
 */
@Service
public class HandlerStrategyFactory {

    @Autowired
    public Map<String, BaseHandler> handlers = new ConcurrentHashMap<>();

    public BaseHandler getHandler(@NotNull FullHttpRequest request){
        if (!StringUtils.isEmpty(request.content().toString(StandardCharsets.UTF_8))) {
            String json = request.content().toString(CharsetUtil.UTF_8);
            Map<String, Object> params = (Map<String, Object>) JSON.parseObject(json, Map.class);
            return handlers.get(HandlerTypeEnum.valueOf(params.getOrDefault("handlerType", "ROUTE_HANDLER").toString()).getCode());
        }
        return handlers.get(HandlerTypeEnum.ROUTE_HANDLER.getCode());
    }
}
