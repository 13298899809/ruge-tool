package com.ruge.core;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Vector;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName SFtpClient
 * @date 2020.09.11 17:26
 */
public class SFtpClient {

    private static final Logger loggerMonitor = LoggerFactory.getLogger("monitor");
    /**
     * FTPClient对象
     **/
    private static ChannelSftp ftpClient = null;
    /**
     *
     */
    private static Session sshSession = null;

    /**
     * 连接服务器
     *
     * @param host     host
     * @param port     port
     * @param userName 用户名
     * @param password 密码
     * @return {@link ChannelSftp}
     * @throws Exception 异常
     */
    public static ChannelSftp getConnect(String host, String port, String userName, String password)
            throws Exception {
        try {
            JSch jsch = new JSch();
            // 获取sshSession
            sshSession = jsch.getSession(userName, host, Integer.parseInt(port));
            // 添加s密码
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            // 开启sshSession链接
            sshSession.connect();
            // 获取sftp通道
            ftpClient = (ChannelSftp) sshSession.openChannel("sftp");
            // 开启
            ftpClient.connect();
            loggerMonitor.debug("success ..........");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("连接sftp服务器异常。。。。。。。。");
        }
        return ftpClient;
    }

    /**
     * 下载文件
     *
     * @param ftpPath     服务器文件路径
     * @param savePath    下载保存路径
     * @param oldFileName 服务器上文件名
     * @param newFileName 保存后新文件名
     * @throws Exception 异常
     */
    public static void download(String ftpPath, String savePath, String oldFileName, String newFileName)
            throws Exception {
        FileOutputStream fos = null;
        try {
            ftpClient.cd(ftpPath);
            File file = new File(savePath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (!mkdirs) {
                    throw new Exception("新建文件失败............");
                }
            }
            String saveFile = savePath + newFileName;
            File file1 = new File(saveFile);
            fos = new FileOutputStream(file1);
            ftpClient.get(oldFileName, fos);

        } catch (Exception e) {
            loggerMonitor.error("下载文件异常............{}", e.getMessage());
            throw new Exception("download file error............");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("close stream error..........");
                }
            }
        }
    }

    /**
     * 上传
     *
     * @param uploadPath  上传文件路径
     * @param ftpPath     服务器保存路径
     * @param newFileName 新文件名
     * @throws Exception 异常
     */
    public static void uploadFile(String uploadPath, String ftpPath, String newFileName) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(uploadPath));
            ftpClient.cd(ftpPath);
            ftpClient.put(fis, newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Upload file error.");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("close stream error.");
                }
            }
        }
    }

    /**
     * 关闭
     *
     * @throws Exception 异常
     */
    public static void close() throws Exception {
        loggerMonitor.debug("close............");
        try {
            ftpClient.disconnect();
            sshSession.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("close stream error.");
        }
    }

    public static void main(String[] args) {
        try {
            ChannelSftp sftp = getConnect("sftp.pro.tsp.bjev.com.cn", "22220", "bxbigdata", "bx_bigdata#pro#bjev2020");


            Class cl = ChannelSftp.class;

            Field f1 = cl.getDeclaredField("server_version");
            f1.setAccessible(true);
            f1.set(sftp, 2);
            sftp.setFilenameEncoding("gbk");

            System.out.println(sftp.getServerVersion());

            Vector ls = sftp.ls("/upload");
            for (Object item : ls) {
                System.out.println(item);
            }
            download("/upload", "./", "Violation_Data_Report_20200101.csv", "Violation_Data_Report_20200101.csv");
            download("/upload", "./", "Music_Data_Report_20200101.csv", "Music_Data_Report_20200101.csv");
            download("/upload", "./", "ETCP_Data_Report_20200101.csv", "ETCP_Data_Report_20200101.csv");
            close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
