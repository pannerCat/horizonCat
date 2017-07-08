package com.baidu.fbu.frontsystem.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 前置服务监听处理类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
public class FrontSystemHandler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrontSystemHandler.class);

    private Socket socket;

    public FrontSystemHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader inReader = null;
        PrintWriter outWriter = null;
        try {
            inReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message = inReader.readLine();
            LOGGER.info("Receive message :" + message);
            outWriter = new PrintWriter(this.socket.getOutputStream(), true);
            outWriter.write("OK");
            outWriter.flush();

        } catch (Exception e) {
            LOGGER.info("FrontSystemHandler occur Exception", e);
        } finally {
            if (null != inReader) {
                try {
                    inReader.close();
                } catch (IOException e) {
                    LOGGER.info("FrontSystemHandler occur IOException", e);
                }
            }

            if (null != outWriter) {
                outWriter.close();
            }
        }
    }
}
