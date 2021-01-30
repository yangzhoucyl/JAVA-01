package com.netty.gateway.handle.okhttp;

import okhttp3.*;

import java.io.IOException;

/**
 * @author YangZhou
 */
public class Okhttp {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static final String HTTP_METHOD_POST = "POST";

    public static final String HTTP_METHOD_GET = "GET";

    public static String httpRequest(String url, String json, String method){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = null;
        if (HTTP_METHOD_POST.equalsIgnoreCase(method)){
            request = new Request.Builder().url(url).post(body).build();
        }else if (HTTP_METHOD_GET.equalsIgnoreCase(method)){
            request = new Request.Builder().url(url).get().build();
        }
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("请求失败");
            }
//            Response.Builder builder = response.newBuilder();
//            Response clone = builder.build();
//            response.newBuilder().body(clone.body()).build();
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("请求失败,服务未响应");
        }
        return null;
    }
}
