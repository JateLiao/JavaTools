package com.liaoshijie.tools.tinman.wechat_tool.GZHMessage;

import com.liaoshijie.tools.common.utils.HttpUtils;
import com.liaoshijie.tools.common.utils.JsonUtil;
import com.liaoshijie.tools.tinman.wechat_tool.common.ResponseBaseBo;
import com.liaoshijie.tools.tinman.wechat_tool.utils.GZHUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.liaoshijie.tools.common.utils.HttpUtils.HEADER_DEFAULT;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/11 下午2:19
 */
@Slf4j
public class GZHSendMessage_Image_ByTag {

    /**
     * main method.
     **/
    public static void main(String[] args) {
        Integer tagId = 10000000;
        String mediaId = "vIQIwHvOQd0bVsy_ja-aezxgu2JdZSFrIGwqTFeU5Dgs75dNlMwKUvwvym-m_VE2";
        String appId = "wx2c13dbfbd2921e1d";
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";

        try {
            // 参数构造
            Map<String, Object> param = new HashMap<>(4);
            Map<String, Object> mediaMap = new HashMap<>(1);
            mediaMap.put("media_ids", Collections.singletonList(mediaId));
            param.put("images", mediaMap);
            param.put("msgtype", "image");
            param.put("clientmsgid", UUID.randomUUID().toString().substring(0, 8));
            //param.put("recommend", "image"); // 推荐语
            param.put("need_open_comment", 0);
            param.put("only_fans_can_comment", 1);
            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put("is_to_all", false);
            filterMap.put("tag_id", tagId);
            param.put("filter", filterMap);
            // 请求微信
            String body = HttpUtils.post(String.format(url, GZHUtils.getToken(appId)), JsonUtil.toJson(param), HEADER_DEFAULT);
            ResponseBaseBo responseBaseBo = JsonUtil.json2Obj(body, ResponseBaseBo.class);
            if (responseBaseBo == null || responseBaseBo.getErrcode() != 0) {
                log.warn("请求失败，responseBaseBo={}", responseBaseBo);
            }
        } catch (Exception e) {
            log.error("处理异常", e);
        }

    }
}
