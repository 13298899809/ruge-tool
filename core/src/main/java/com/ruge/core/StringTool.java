package com.ruge.core;


/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName StringTool
 * @date 2020.07.02 14:33
 */
public class StringTool {
    /**
     * @param str 目标对象
     * @return 目标对象为空 true 否则 false
     */
    public static boolean isBlank(Object str) {
        return (str == null || "".equals(str) || "".equals(str.toString().trim()));
    }

    /**
     * @param str 目标对象
     * @return 目标对象不为空 true 否则 false
     */
    public static boolean isNotBlank(Object str) {
        return !isBlank(str);
    }

    /**
     * @param str 目标字符串
     * @return 全部都是小写 是true 否false
     */
    public static boolean isAllLowerCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param str 目标字符串
     * @return 全部都是大写 是true 否false
     */
    public static boolean isAllUpperCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param str 字符串
     * @return 字符串为null默认返回""空串
     */
    public static String defaultString(Object str) {
        return str == null ? "" : String.valueOf(str);
    }


    /**
     * @param str        字符串
     * @param defaultStr 默认值
     * @return 字符串为null默认返回""空串
     */
    public static String defaultString(Object str, String defaultStr) {
        return str == null ? defaultStr : String.valueOf(str);
    }

    /**
     * @param str 字符串
     * @param key 关键字妇产
     * @return 关键字符串往后的字符
     */
    public static String substrIndexToLast(String str, String key) {
        if (str.contains(key)) {
            str = str.substring(str.indexOf(key) + 1);
        }
        return str;
    }
}
