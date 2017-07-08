package com.baidu.fbu.frontsystem.monitor;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 前置服务监听类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
public class FrontSystemListener implements Runnable{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontSystemListener.class);
    
    /**
     * 监听端口
     */
    private int port;
    /**
     * 监听线程数
     */
    private int poolSize;
    
    private ExecutorService executorService = null;
    
    private ServerSocket serverSocket;
    
    public FrontSystemListener(int port, int poolSize) {
        this.port = port;
        this.poolSize = poolSize;
    }
    
    public void run() {
        try {
            serverSocket = new ServerSocket(this.port);
            executorService = Executors.newFixedThreadPool(this.poolSize);
            while (true) {
                try {
                    executorService.execute(new FrontSystemHandler(serverSocket.accept()));
                } catch (Exception e) {
                    LOGGER.error("FrontSystemListener occur Exception !", e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("FrontSystemListener run occur Exception !", e);
        }
    }
}
