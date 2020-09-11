package com.ruge.core;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FtpToolTest {

//    public static final String HOST = "47.93.18.115";
//    public static final Integer PORT = 21;
//    public static final String USER_NAME = "vsftpd";
//    public static final String PASS_WORD = "qwertyui";

    public static final String HOST = "sftp.sit.tsp.bjev.com.cn";
    public static final Integer PORT = 22220;
    public static final String USER_NAME = "bxbigdatadev";
    public static final String PASS_WORD = "bx_bigdata#dev#bjev2020";
    public static FTPClient ftpClient;


    public void getConnection() {
        try {
            ftpClient= FtpTool.getConnection(HOST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Before
    public void testGetConnection() {
        try {
            FtpTool.getConnection(HOST,PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Before
    public void testGetConnection1() {
        try {
            FtpTool.getConnection(HOST,USER_NAME,PASS_WORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetConnection2() {
        try {
            FtpTool.getConnection(HOST,PORT,USER_NAME,PASS_WORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void moveFile() {
    }

    @Test
    public void getFileNameList() {
    }

    @Test
    public void readFile() {
    }

    @Test
    public void downFile() {
    }

    @Test
    public void storeAsFile() {
    }

    @Test
    public void close() {
    }

    @Test
    public void isConnected() {
        System.out.println(FtpTool.isConnected());
    }

    @Test
    public void uploadFile() {
        String fileName="name.txt";
        String context = "张三李四王五";
        InputStream inputStream = new ByteArrayInputStream(context.getBytes());
        String uploadDir = "/home/vsftpd/";
        try {
            FtpTool.uploadFile(fileName,inputStream,uploadDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadFileFromDailyDir() {
        try {
            String uploadDir = "/home/vsftpd/";
            String fileName="name.txt";
            String windowsPath = "D:\\google\\"+fileName;
            FtpTool.downloadFile(uploadDir+fileName,windowsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}