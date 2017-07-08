package com.baidu.fbu.frontsystem.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 版权信息
 * 文件名
 * 描述 Ftp工具类
 * 创建人 panhongshuang
 * 创建时间 2017-07-07
 * 修改日志
 */
public class FtpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpUtil.class);

    // 字符编码
    private static final String ENCODING_CODE = "UTF-8";

    // 缓冲区大小
    private static final int BUFFER_SIZE = 1024 * 2;

    // 读取数据时间和链接超时时间都设置为30s
    private static final int TIME_OUT = 30 * 1000;

    private FTPClient ftpClient;

    /**
     * ftp服务的ip
     */
    private String strIp;

    /**
     * ftp服务的端口
     */
    private int intPort;

    /**
     * 登录ftp服务的用户名
     */
    private String userName;

    /**
     * 登录ftp服务的密码
     */
    private String password;

    public FtpUtil(String strIp, int intPort, String userName, String password) {
        this.strIp = strIp;
        this.intPort = intPort;
        this.userName = userName;
        this.password = password;
        this.ftpClient = new FTPClient();
    }

    /**
     * 判断是否登入成功
     */
    public boolean ftpLogin() {
        boolean isLogin = false;
        this.ftpClient.setControlEncoding(ENCODING_CODE);
        this.ftpClient.setConnectTimeout(TIME_OUT);
        this.ftpClient.setBufferSize(BUFFER_SIZE);
        this.ftpClient.setDataTimeout(TIME_OUT);
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        this.ftpClient.configure(ftpClientConfig);
        try {
            if (this.intPort > 0) {
                this.ftpClient.connect(this.strIp, this.intPort);
            } else {
                this.ftpClient.connect(this.strIp);
            }
            // FTP服务器连接回答
            int reply = this.ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                LOGGER.error("connect to FTP server fail, IP={}, PORT={} ", strIp, intPort);
                return isLogin;
            }

            this.ftpClient.login(this.userName, this.password);
            // 设置传输协议
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            LOGGER.info(" login FTP server success ");

            isLogin = true;
        } catch (Exception e) {
            LOGGER.error("login FTP server fail ! username={}, password={}, ip={}, port={} ", this.userName, this.password, this.strIp, this.intPort, e);
        }

        return isLogin;
    }

    /**
     * 退出关闭服务器链接
     */
    public void ftpLogOut() {
        if (null != this.ftpClient && this.ftpClient.isConnected()) {
            try {
                boolean reuslt = this.ftpClient.logout(); // 退出FTP服务器
                if (reuslt) {
                    LOGGER.info("login out FTP server success");
                }
            } catch (IOException e) {
                LOGGER.warn("login out FTP server error ", e);
            } finally {
                try {
                    this.ftpClient.disconnect(); // 关闭FTP服务器的连接
                } catch (IOException e) {
                    LOGGER.warn("close FTP server connect error", e);
                }
            }
        }
    }

    /***
     * 下载文件
     *
     * @param remoteFilePath
     *            待下载文件所在的路径
     * @param remoteFileName
     *            待下载文件名称
     * @param filepath
     *            下载到本地的路径
     */

    public boolean downloadFile(String remoteFilePath, String remoteFileName, String filepath) {
        boolean success = false;
        try {
            this.ftpClient.changeWorkingDirectory(remoteFilePath);
        } catch (Exception e) {
            LOGGER.error("changeWorkingDirectory fail ", e);
        }
        String strFilePath = filepath + remoteFileName;
        BufferedOutputStream outStream = null;
        try {
            outStream = new BufferedOutputStream(new FileOutputStream(strFilePath));
            LOGGER.info(remoteFileName + " begin download .... ");

            success = this.ftpClient.retrieveFile(remoteFileName, outStream);

            if (success) {
                LOGGER.info(remoteFileName + " download success " + strFilePath);
                return success;
            }
        } catch (Exception e) {
            LOGGER.error(remoteFileName + " download fail ", e);
        } finally {
            if (null != outStream) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    LOGGER.error("downloadFile occur IOException when close outStream ! ", e);
                }
            }
        }

        if (success) {
            LOGGER.error(remoteFileName + " download fail");
        }
        return success;
    }
    /**
     * 上传文件
     * @param localFilePath 本地文件路径
     * @param localFileName 本地文件名
     * @return
     */
    public boolean uploadFile (String localFilePath, String localFileName) {
        boolean uploadFlag = false;
        File localFile = null;
        FileInputStream inputStream = null;
        try {
            localFile = new File(localFilePath + File.separator + localFileName);
            inputStream = new FileInputStream(localFile);
            uploadFlag = this.ftpClient.storeFile(localFileName, inputStream);
        } catch (Exception e) {
            LOGGER.error("uploadFile occur Exception ! ", e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("uploadFile occur IOException when close inputStream ! ", e);
                }
            }
        }
        return uploadFlag;
    }
    
    public void delete(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileUtils.forceDelete(file);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
