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
}
