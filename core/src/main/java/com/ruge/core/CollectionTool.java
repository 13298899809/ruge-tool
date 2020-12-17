package com.ruge.core;


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

    /**
     * 根据map的key排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     * @author zero 2019/04/08
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = new LinkedHashMap<>();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

}
