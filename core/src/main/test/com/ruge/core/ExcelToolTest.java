package com.ruge.core;

import com.ruge.core.tmp.User;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExcelToolTest {

    @Test
    public void readExcelToListMap() {
        File file = new File("D:\\google\\海外项目计划节点.xlsx");
        Map<String, String> mapping = new HashMap<>();
        mapping.put("姓名", "name");
        List<Map<String, Object>> maps = ExcelTool.readExcelToListMap(file, mapping);
        maps.forEach(e -> {
            System.out.println(e);
        });
    }

    @Test
    public void mapToBean() {
        Map<String, String> mapping1 = new HashMap<>();
        mapping1.put("主键", "1");
        mapping1.put("姓名", "张1");
        Map<String, String> mapping2 = new HashMap<>();
        mapping2.put("主键", "2");
        mapping2.put("姓名", "张2");
        List list = new ArrayList();
        list.add(mapping1);
        list.add(mapping2);
        File file = new File("D:\\google\\海外项目计划节点.xlsx");
        List<User> users = ExcelTool.readExcelToListBean(file, User.class);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void writeExcelToStream() {
        Map<String, String> mapping1 = new HashMap<>();
        mapping1.put("主键", "1");
        mapping1.put("姓名", "张1");
        Map<String, String> mapping2 = new HashMap<>();
        mapping2.put("主键", "2");
        mapping2.put("姓名", "张2");
        List<User> list = new ArrayList();
        list.add(new User(1,"张1====================================================="));
        list.add(new User(2,"张2"));

        ExcelTool.writeExcelToStream(list, User.class, "测试导出");
    }
}