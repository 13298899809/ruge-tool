package com.ruge.core;

import java.util.UUID;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName IdTool
 * @date 2020.08.21 14:48
 */
public class IdTool {
    /**
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 订单号
     * 生成规则：来源-年月日时分秒-四位随机数
     * 示例如下：AFAPP202009021520339549
     */
    public static String orderId(String perfix) {
        return perfix+DateTool.getDateInstance("yyyyMMddHHmmss")+uuid().substring(0,4);
    }

}
