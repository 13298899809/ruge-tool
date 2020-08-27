package com.ruge.core;


/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName MathTool
 * @date 2020.08.18 15:53
 */
public class MathTool {

    /**
     * @param source  源数据
     * @param percent 百分比
     * @return 获取数据百分比
     */
    public static Double getNumberPercent(Long source, Double percent) {
        if (null == source) {
            return null;
        }
        if (null == percent) {
            return null;
        }
        return source * percent;
    }
}
