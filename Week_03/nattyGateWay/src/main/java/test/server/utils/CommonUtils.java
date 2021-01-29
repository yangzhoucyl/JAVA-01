package test.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.netty.gateway.handle.okhttp.Okhttp;
import test.server.dto.HeartBeatConnect;

import java.util.Random;

import static com.netty.gateway.handle.constant.HandlerTypeEnum.REGISTER_HANDLER;

/**
 * 公共方法
 * @author YangZhou
 */
public class CommonUtils {

    public static void registerServer(HeartBeatConnect heartBeatConnect){
        heartBeatConnect.setHandlerType(REGISTER_HANDLER);
        heartBeatConnect.setLoad(new Random(100).nextInt());
        String json = JSONObject.toJSONString(heartBeatConnect);
        Okhttp.httpRequest("http://127.0.0.1:8082", json, "POST");
    }
}
