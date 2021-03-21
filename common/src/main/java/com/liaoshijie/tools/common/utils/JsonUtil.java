package com.liaoshijie.tools.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author default
 */
@Slf4j
public class JsonUtil {

    final static ObjectMapper om;

    static {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getRawObjectMapper() {
        return om;
    }

    public static <T> T json2Obj(String json, Class<T> clazz) {
        if (null == json) {
            return null;
        }
        ObjectMapper objectMapper = getRawObjectMapper();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("json cover object exception;json={},clazz={}", json, clazz.getName(), e);
            //throw new Exception("json反序列化异常");
        }
        return null;
    }

    /**
     * json字符串转成list
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> json2ObjList(String jsonString, Class<T> cls) {
        if (null == jsonString) {
            return null;
        }
        ObjectMapper objectMapper = getRawObjectMapper();
        try {
            return objectMapper.readValue(jsonString, getCollectionType(List.class, cls));
        } catch (JsonProcessingException e) {
            String className = cls.getSimpleName();
            log.error(" parse json [{}] to class [{}] error：{}", jsonString, className, e);
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getRawObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T json2Obj(String json, TypeReference<T> reference) {
        if (null == json) {
            return null;
        }
        ObjectMapper objectMapper = getRawObjectMapper();
        try {
            return objectMapper.readValue(json, reference);
        } catch (IOException e) {
            log.error("json cover object exception;json={},clazz={}", json, reference.getType(), e);
            //throw new BusinessRuntimeException(ResultCode.SYSTEM_ERROR);
        }
        return null;
    }

    public static <T> T json2Obj(InputStream jsonStream, TypeReference<T> reference) {
        if (null == jsonStream) {
            return null;
        }
        ObjectMapper objectMapper = getRawObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(jsonStream, reference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }


    public static String toJson(Object obj) {
        try {
            return getRawObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(" to json cover exception;obj={}", obj, e);
            return null;
        }
    }

    public static boolean validate(String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (Throwable ignore) {
        }

        return valid;
    }

    public static JsonNode toJsonNode(Object obj) {
        return getRawObjectMapper().convertValue(obj, JsonNode.class);
    }

    @SneakyThrows
    public static ObjectNode toJsonObject(String obj) {
        final JsonNode jsonNode = getRawObjectMapper().readTree(obj);
        if (jsonNode.isObject()) {
            return (ObjectNode) (jsonNode);
        }
        return null;
    }
}
