package com.example.nio01;

import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;

public class okhttp {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void main(String[] args) {
        String url = "http://localhost:8081";
        httpRequest(url, "{}");
    }

    public static String httpRequest(String url, String json){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("请求失败");
            }
            String responseBody = response.body().string();
            System.out.println(responseBody);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("请求失败,服务未响应");
        }
        return null;
    }
}
