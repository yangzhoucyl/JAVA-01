package com.netty.gateway.route;

/**
 * @author YangZhou
 */

public class RouteServerInfo {

    private String applicationName;
    private int port;
    private String address;
    private String status;
    private int load;
    private long heartTime;

    public RouteServerInfo(String applicationName, int port, String address, long heartTime,String status, int load) {
        this.applicationName = applicationName;
        this.port = port;
        this.address = address;
        this.heartTime = heartTime;
        this.status = status;
        this.load = load;
    }

    public RouteServerInfo() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public long getHeartTime() {
        return heartTime;
    }

    public void setHeartTime(long heartTime) {
        this.heartTime = heartTime;
    }
}
