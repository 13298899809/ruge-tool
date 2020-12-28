package com.ruge.core;

import cn.hutool.http.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName HttpTool
 * @date 2020.08.03 16:12
 */
public class HttpTool {
    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
