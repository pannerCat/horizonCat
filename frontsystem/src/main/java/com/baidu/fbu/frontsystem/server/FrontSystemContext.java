package com.baidu.fbu.frontsystem.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 前置信息上下文类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
public class FrontSystemContext {
    private static final String CONTEXT_FILE_PATH = "classpath:FrontSystemContext.xml";

    public static ApplicationContext applicationContext;
    
    public static void initialize() {
        // 加载Spring上下文
        applicationContext = new ClassPathXmlApplicationContext(CONTEXT_FILE_PATH);
    }
}
