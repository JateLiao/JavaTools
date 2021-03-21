package com.liaoshijie.tools.tinman.wechat_tool.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liaoshijie.tools.common.utils.HttpUtils;
import com.liaoshijie.tools.common.utils.JsonUtil;

import java.util.Collections;
import java.util.Map;

import static com.liaoshijie.tools.common.utils.HttpUtils.HEADER_DEFAULT;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/11 下午2:50
 */
public class GZHUtils {

    public static String getToken(String appId) {
        String url = "https://api.jojoread.com/pc/pc-wechat-tool/wechat/getAccessToken?appId=%s";
        String body = HttpUtils.get(String.format(url, appId), HEADER_DEFAULT);
        Map<String, Object> map = JsonUtil.json2Obj(body, new TypeReference<Map<String, Object>>() { });
        return (String) map.get("data");
    }
}
