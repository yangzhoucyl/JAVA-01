package com.netty.gateway.handle;

import com.netty.gateway.handle.constant.HandlerTypeEnum;

/**
 * 心跳连接数据
 * @author YangZhou
 */
public class HeartBeatConnect {

    private String applicationName;
    private String address;
    private int load;
    private int port;
    private String netStatus;
    private long sendTime;
    private HandlerTypeEnum handlerType;

    public HeartBeatConnect() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setNetStatus(String netStatus) {
        this.netStatus = netStatus;
    }

    public String getNetStatus() {
        return netStatus;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public HandlerTypeEnum getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(HandlerTypeEnum handlerType) {
        this.handlerType = handlerType;
    }
}
