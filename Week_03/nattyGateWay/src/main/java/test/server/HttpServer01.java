package test.server;

import com.alibaba.fastjson.JSONObject;
import test.server.connect.HeartBeatConnectThread;
import test.server.dto.HeartBeatConnect;
import test.server.utils.CommonUtils;
import test.server.utils.Okhttp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

import static com.netty.gateway.handle.constant.HandlerTypeEnum.HEART_BEAT;
import static com.netty.gateway.handle.constant.HandlerTypeEnum.REGISTER_HANDLER;

public class HttpServer01 {

    private static final int PORT = 8081;

    public static void main(String[] args) {
        HeartBeatConnect heartBeatConnect = new HeartBeatConnect("Test-Server-01", "http://127.0.0.1", 0, PORT, "ON",
                 System.currentTimeMillis(), HEART_BEAT);
        try {
            CommonUtils.registerServer(heartBeatConnect);
            ServerSocket serverSocket = new ServerSocket(PORT);
            new Thread(new HeartBeatConnectThread(heartBeatConnect)).start();
            while (true){
                Socket socket = serverSocket.accept();
                service(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void service(Socket socket){
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:application/json;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,nio");
            printWriter.close();
            socket.close();
            System.out.println(socket.getLocalAddress());
            System.out.println(socket.getInetAddress());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }






}
