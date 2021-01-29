package test.server.connect;

import com.alibaba.fastjson.JSONObject;
import com.netty.gateway.handle.constant.HandlerTypeEnum;
import com.netty.gateway.handle.okhttp.Okhttp;
import test.server.dto.HeartBeatConnect;

import java.util.Random;

public class HeartBeatConnectThread implements Runnable{

        private HeartBeatConnect heartBeatConnect;

        public HeartBeatConnectThread(HeartBeatConnect heartBeatConnect) {
            this.heartBeatConnect = heartBeatConnect;
        }

        @Override
        public void run() {
            while (true){
                heartBeatConnect.setLoad(new Random(100).nextInt());
                heartBeatConnect.setHandlerType(HandlerTypeEnum.HEART_BEAT);
                String json = JSONObject.toJSONString(this.heartBeatConnect);
                Okhttp.httpRequest("http://127.0.0.1:8082", json, "POST");
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }