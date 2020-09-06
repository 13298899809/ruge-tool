package com.ruge.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonToolTest {

    @Test
    public void getBeanToJson() {

        Map<String,Object> cbtMap = new HashMap<>();
        cbtMap.put("key", "image");
        cbtMap.put("name", "回复图片");
        cbtMap.put("type","click");


        Map<String,Object> vbtMap = new HashMap<>();
        vbtMap.put("url", "http://www.cuiyongzhi.com");
        vbtMap.put("name", "博客");
        vbtMap.put("type","view");
        vbtMap.put("key",cbtMap);

        List list = new ArrayList<>();
        list.add(cbtMap);
        list.add(vbtMap);
        System.out.println(JsonTool.getBeanToJson(list));
    }
}