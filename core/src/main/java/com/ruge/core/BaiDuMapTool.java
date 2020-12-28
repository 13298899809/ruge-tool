package com.ruge.core;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: mike.ma
 * @Date: 2014/6/12
 */
@Slf4j
public class BaiDuMapTool {
    public static final String BAIDU_MAP = "http://api.map.baidu.com/place/v2/search";
    public static final Long DEFAULT_RADIUS = 2000L;
    public static final Long RADIUS = 20000L;
    public static final String AK = "tY5G3L9XWbdxRqg6QmyPxNTFaiw4R47Q";

    /**
     * 调用百度地图查询省市区
     *
     * @param lat 纬度
     * @param lon 经度
     * @return 省市区
     */
    public static String addressByLocation(Double lat, Double lon, Long radius) {
        String address = "";
        if (radius < RADIUS) {
            String location = (lat + "," + lon).trim();
            String query = "银行";
            String output = "json";
            String uri = BAIDU_MAP + "?query=" + query + "&location="
                    + location + "&radius=" + radius + "&output=" + output + "&ak=" + AK;
            log.info("当前url是 {}", uri);
            String result = HttpUtil.createGet(uri).execute().body();
            JSONObject jsonObject = JSON.parseObject(result);
            List results = (List) jsonObject.get("results");
            if (!jsonObject.get("status").equals(0)) {
                log.error("调用百度地图返回错误，地址获取失败！！！" + uri);
            } else if (jsonObject.get("status").equals(0) && results.isEmpty()) {
                radius = radius + 2000;
                log.info("无返回值 增加2000m 现在是 {}" + radius);
                return addressByLocation(lat, lon, radius);
            } else {
                //字符串解析
                String province = String.valueOf(((JSONObject) ((JSONArray) jsonObject.get("results")).get(0)).get("province"));
                String city = String.valueOf(((JSONObject) ((JSONArray) jsonObject.get("results")).get(0)).get("city"));
                String area = String.valueOf(((JSONObject) ((JSONArray) jsonObject.get("results")).get(0)).get("area"));
                address = province + "," + city + "," + area;
                log.info("返回值是 {}", address);
            }
        }
        log.info("最终返回值是 {}", address);
        return address;
    }

    public static void main(String[] args) {
        String s = BaiDuMapTool.addressByLocation(40.532224, 115.732921, 2000L);
        log.info("接口返回值是 {}", s);
    }
}