package com.baidu.fbu.frontsystem.server;

import com.baidu.fbu.frontsystem.monitor.FrontSystemListener;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 前置服务启动类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
public class FrontSystemServer {
    public static void main(String[] args) {
        FrontSystemContext.initialize();
        
        FrontSystemConfig frontSystemConfig = FrontSystemContext.applicationContext.getBean(FrontSystemConfig.class);
        
        Thread listenerThread = new Thread(new FrontSystemListener(frontSystemConfig.getFrontSystemPort(), frontSystemConfig.getListenerPoolSize()));
        listenerThread.setName("FrontSystemListener");
        listenerThread.start();
    }
}
