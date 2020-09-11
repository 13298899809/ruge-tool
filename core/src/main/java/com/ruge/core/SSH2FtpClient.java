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
 * @ClassName SSH2FtpClient
 * @date 2020.09.11 17:26
 */
public class SSH2FtpClient {

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
     * @param host
     * @param port
     * @param userName
     * @param password
     * @return
     * @throws Exception
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
     * @param ftp_path	服务器文件路径
     * @param save_path	下载保存路径
     * @param oldFileName	服务器上文件名
     * @param newFileName	保存后新文件名
     * @throws Exception
     */
    public static void download(String ftp_path, String save_path, String oldFileName, String newFileName)
            throws Exception {
        FileOutputStream fos = null;
        try {
            ftpClient.cd(ftp_path);
            File file = new File(save_path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String saveFile = save_path + newFileName;
            File file1 = new File(saveFile);
            fos = new FileOutputStream(file1);
            ftpClient.get(oldFileName, fos);
        } catch (Exception e) {
            loggerMonitor.error("下载文件异常............", e.getMessage());
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
     * @param upload_path 上传文件路径
     * @param ftp_path	服务器保存路径
     * @param newFileName	新文件名
     * @throws Exception
     */
    public static void uploadFile(String upload_path, String ftp_path, String newFileName) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(upload_path));
            ftpClient.cd(ftp_path);
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
     * @throws Exception
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
            ChannelSftp sftp = getConnect("sftp.sit.tsp.bjev.com.cn", "22220", "bxbigdatadev", "bx_bigdata#dev#bjev2020");

            Field f =ChannelSftp.class.getDeclaredField("server_version");
            f.setAccessible(true);
            f.set(sftp, 2);


            Vector ls = sftp.ls("/upload");
            for (Object item : ls) {
                System.out.println(item);
//                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) item;
//                System.out.println(entry.getFilename());
            }
            close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
