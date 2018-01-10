package com.fnd.blogger.manager.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ClassUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JacksonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtil() {
        throw new IllegalAccessError("工具类不能通过构造器初始化！");
    }

    public static ObjectMapper getDefaultMapperCopy() {
        return objectMapper.copy();
    }

    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception var2) {
            throw new RuntimeException("对象转JSON时发生异常:" + var2.getMessage(), var2);
        }
    }

    public static String obj2json(Object obj, DateFormat df) {
        try {
            ObjectMapper newObjectMapper = objectMapper.copy();
            return newObjectMapper.writer(df).writeValueAsString(obj);
        } catch (Exception var3) {
            throw new RuntimeException("对象转JSON时发生异常:" + var3.getMessage(), var3);
        }
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转对象时发生异常:" + var3.getMessage() + ",json报文：" + jsonStr, var3);
        }
    }

    public static <T> T json2pojo(String jsonStr, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转对象时发生异常:" + var3.getMessage() + ",json报文：" + jsonStr, var3);
        }
    }

    public static Map<String, Object> json2map(String jsonStr) {
        try {
            return (Map)objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception var2) {
            throw new RuntimeException("JSON 转Map<String,Object>时发生异常:" + var2.getMessage() + ",json报文：" + jsonStr, var2);
        }
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        try {
            Map<String, Map<String, Object>> map = (Map)objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
            Map<String, T> result = new HashMap();
            Iterator var4 = map.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, Map<String, Object>> entry = (Map.Entry)var4.next();
                result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
            }

            return result;
        } catch (Exception var6) {
            throw new RuntimeException("JSON 转Map时发生异常:" + var6.getMessage() + ",json报文：" + jsonStr, var6);
        }
    }

    public static List<Map<String, Object>> json2listWithMap(String jsonArrayStr) {
        try {
            List<Map<String, Object>> list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<Map<String, Object>>>() {
            });
            return list;
        } catch (Exception var2) {
            throw new RuntimeException("JSON 转List时发生异常:" + var2.getMessage() + ",json报文：" + jsonArrayStr, var2);
        }
    }

    public static <T> List<T> json2listWithBean(String jsonArrayStr, Class<T> clazz) {
        try {
            List list;
            if (!ClassUtils.isPrimitiveOrWrapper(clazz) && !ClassUtils.isAssignable(String.class, clazz) && !ClassUtils.isAssignable(List.class, clazz) && !ClassUtils.isAssignable(Set.class, clazz) && !ClassUtils.isAssignable(Map.class, clazz)) {
                list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
                List<T> result = new ArrayList();
                Iterator var4 = list.iterator();

                while(var4.hasNext()) {
                    Map<String, Object> map = (Map)var4.next();
                    result.add(map2pojo(map, clazz));
                }

                return result;
            } else {
                list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
                return list;
            }
        } catch (Exception var6) {
            throw new RuntimeException("JSON 转List时发生异常:" + var6.getMessage() + ",json报文：" + jsonArrayStr, var6);
        }
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.enable(new MapperFeature[]{MapperFeature.SORT_PROPERTIES_ALPHABETICALLY});
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
