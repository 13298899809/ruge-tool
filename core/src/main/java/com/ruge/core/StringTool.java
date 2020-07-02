package com.ruge.core;

import com.sun.istack.internal.Nullable;

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
        return (str == null || "".equals(str));
    }

    /**
     * @param str 目标对象
     * @return 目标对象不为空 true 否则 false
     */
    public static boolean isNotBlank(Object str) {
        return !isBlank(str);
    }
}
