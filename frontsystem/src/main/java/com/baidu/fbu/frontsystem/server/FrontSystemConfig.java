package com.baidu.fbu.frontsystem.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 前置服务配置类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
@Component
public class FrontSystemConfig {  
    /**
     * 前置本地端口
     */
    @Value("${frontSystem.port}")
    private int frontSystemPort;
    
    /**
     * 启动监听线程数
     */
    @Value("${listener.poolSize}")
    private int listenerPoolSize;
    
    /**
     * 远程下载IP地址
     */
    @Value("${download.remote.ipAddress}")
    private String downloadRemoteIP;
    
    /**
     * 远程下载端口
     */
    @Value("${download.remote.port}")
    private String downloadRemotePort;
    
    /**
     * 远程上传IP地址
     */
    @Value("${upload.remote.ipAddress}")
    private String uploadRemoteIP;
    
    /**
     * 远程上传端口
     */
    @Value("${upload.remote.port}")
    private String uploadRemotePort;
    
    /**
     * 指令应答报文
     */
    @Value("${command.response.message}")
    private String responseMessage;
    
    /**
     * 健康监测报文
     */
    @Value("${health.detect.message}")
    private String healthDetectMessage;
    
    /**
     * 健康监测应答
     */
    @Value("${health.response.message}")
    private String healthResponseMessage;

    public int getFrontSystemPort() {
        return frontSystemPort;
    }

    public void setFrontSystemPort(int frontSystemPort) {
        this.frontSystemPort = frontSystemPort;
    }  
    
    public int getListenerPoolSize() {
        return listenerPoolSize;
    }

    public void setListenerPoolSize(int listenerPoolSize) {
        this.listenerPoolSize = listenerPoolSize;
    }

    public String getDownloadRemoteIP() {
        return downloadRemoteIP;
    }

    public void setDownloadRemoteIP(String downloadRemoteIP) {
        this.downloadRemoteIP = downloadRemoteIP;
    }

    public String getDownloadRemotePort() {
        return downloadRemotePort;
    }

    public void setDownloadRemotePort(String downloadRemotePort) {
        this.downloadRemotePort = downloadRemotePort;
    }

    public String getUploadRemoteIP() {
        return uploadRemoteIP;
    }

    public void setUploadRemoteIP(String uploadRemoteIP) {
        this.uploadRemoteIP = uploadRemoteIP;
    }

    public String getUploadRemotePort() {
        return uploadRemotePort;
    }

    public void setUploadRemotePort(String uploadRemotePort) {
        this.uploadRemotePort = uploadRemotePort;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getHealthDetectMessage() {
        return healthDetectMessage;
    }

    public void setHealthDetectMessage(String healthDetectMessage) {
        this.healthDetectMessage = healthDetectMessage;
    }

    public String getHealthResponseMessage() {
        return healthResponseMessage;
    }

    public void setHealthResponseMessage(String healthResponseMessage) {
        this.healthResponseMessage = healthResponseMessage;
    }
}
