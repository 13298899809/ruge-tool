package com.ruge.core;

import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName CollectionTool
 * @date 2020.07.31 16:16
 */
public class CollectionTool {
    /**
     * 集合倒叙
     *
     * @param list 集合
     */
    public static void reverse(List list) {
        Collections.reverse(list);
    }

    /**
     * @param iter {@link Iterator}
     * @param <T>  {@link List}
     * @return iterator 转 List
     */
    public static <T> List<T> iteratorToList(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }


}
