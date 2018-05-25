////AutoModify -By zxiaofan////
/*
 * 文件名：GsonUtil.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： GsonUtil.java
 * 修改人：mengjiao
 * 修改时间：2016年3月16日
 * 修改内容：新增
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author guokan.
 */
@SuppressWarnings("rawtypes")
public final class GsonUtil {
    /**
     * GSON.
     */
    private static final Gson GSON;

    /**
     * CSHARPGSON.
     */
    private static final Gson CSHARPGSON;

    /**
     * 构造函数.
     * 
     */
    private GsonUtil() {
        throw new RuntimeException("工具类不允许实例化!");
    }

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON = builder.create();

        GsonBuilder builder2 = new GsonBuilder();
        builder2.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        CSHARPGSON = builder2.create();
    }

    /**
     * 获取json.
     * 
     * @return GSON
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * 设置csharpgson.
     * 
     * @return 返回csharpgson
     */
    public static Gson getCsharpGson() {
        return CSHARPGSON;
    }

    /**
     * toJson.
     * 
     * @param obj
     *            obj
     * @return String
     */
    public static String toJson(Object obj) {
        if (obj instanceof String) {
            return obj.toString();
        } else {
            return getGson().toJson(obj);
        }
    }
}
