package com.ruge.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName JsonTool
 * @date 2020.08.07 09:32
 */
public class JsonTool {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect
    };

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * 将map转换成class
     *
     * @param map   map
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getMapToBean(Map map, Class<T> clazz) {
        return JSON.parseObject(JSONObject.toJSONString(map), clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static Map<String, Object> getJsonToMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 功能描述：把Object数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getjsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 功能描述：把Object数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param objData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getObjectToListMap(Object objData) {
        return JSON.parseObject(getBeanToJson(objData), new TypeReference<List<Map<String, Object>>>() {
        });
    }


    /**
     * 功能描述：把Object数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param list List数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getListToListMap(List list) {
        return JSON.parseObject(getBeanToJson(list), new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 功能描述：把Object数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param list List数据
     * @return List<Map < String, Object>>
     */
    public static Map<String, Object> getObjToMap(Object list) {
        return JSON.parseObject(getBeanToJson(list), new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * 两个类之间值的转换
     * 从object》》tClass
     *
     * @param object 有数据的目标类
     * @param tClass 转换成的类
     * @param <T>
     * @return 返回tClass类
     */
    public static <T> T getObjectToClass(Object object, Class<T> tClass) {
        String json = getBeanToJson(object);
        return getJsonToBean(json, tClass);
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> getJsonToListBean(String jsonString, Class<T> clazz) {
        List<T> ts = JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
}
