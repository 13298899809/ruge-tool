package com.ruge.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/7/9 21:59
 */
public class FilenameTool {

    /**
     * @param fileName 文件路径
     * @return 获取文件拓展名
     */
    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");

        if (index == -1) {
            return null;
        }
        return fileName.substring(index + 1);
    }

    /**
     * @param fileName  文件名
     * @param extension 拓展名集合
     * @return 判断文件扩展名是否包含在指定集合中
     */
    public static boolean isExtension(String fileName, String extension) {
        return extension.equals(getExtension(fileName));
    }

    /**
     * @param fileName  文件名
     * @param extension 拓展名集合
     * @return 判断文件扩展名是否包含在指定集合中
     */
    public static boolean isExtension(String fileName, List<String> extension) {
        return extension.contains(getExtension(fileName));
    }

}
