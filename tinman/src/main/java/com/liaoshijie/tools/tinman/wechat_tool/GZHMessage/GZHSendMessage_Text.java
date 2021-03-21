package com.liaoshijie.tools.tinman.wechat_tool.GZHMessage;

import com.google.common.collect.Lists;
import com.liaoshijie.tools.common.utils.HttpUtils;
import com.liaoshijie.tools.common.utils.JsonUtil;
import com.liaoshijie.tools.tinman.wechat_tool.common.ResponseBaseBo;
import com.liaoshijie.tools.tinman.wechat_tool.utils.GZHUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liaoshijie.tools.common.utils.HttpUtils.HEADER_DEFAULT;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/11 下午2:19
 */
@Slf4j
public class GZHSendMessage_Text {

    /**
     * main method.
     **/
    public static void main(String[] args) {
        String appId = "wx2c13dbfbd2921e1d";
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";
        String path = "/Users/liaoshijie/Workspace/java/JavaTools/tinman/src/main/java/com/liaoshijie/tools/tinman/wechat_tool/GZHMessage/OpenIds";

        try {
            // 获取待发送openId
            File file = new File(path);
            List<String> openIds = FileUtils.readLines(file, "UTF-8");
            List<List<String>> openList = Lists.partition(openIds, 10000);
            // 参数构造
            Map<String, Object> param = new HashMap<>(4);
            Map<String, String> mediaMap = new HashMap<>(1);
            mediaMap.put("content", "<img src=\"http://mmbiz.qpic.cn/mmbiz_png/px94AQ7RQPkOuz2v8sXgEiaWnibEBn8jwGiahIOTr0q5ehkPPl0miatyhxMhCP6ZYdr4PFYEUH3n71ia9U6IY3tdmpg/0\"  />");
            param.put("text", mediaMap);
            param.put("msgtype", "text");
            for (int i = 0; i < openList.size(); i++) {
                List<String> paramOpenIds = openList.get(i);
                param.put("touser", paramOpenIds);
                try {
                    // 请求微信
                    String body = HttpUtils.post(String.format(url, GZHUtils.getToken(appId)), JsonUtil.toJson(param), HEADER_DEFAULT);
                    ResponseBaseBo responseBaseBo = JsonUtil.json2Obj(body, ResponseBaseBo.class);
                    if (responseBaseBo == null || responseBaseBo.getErrcode() != 0) {
                        log.warn("请求失败，first={},last={}", paramOpenIds.get(0), paramOpenIds.get(paramOpenIds.size() - 1));
                    }
                } catch (Exception e) {
                    log.error("请求异常,first={},last={}", paramOpenIds.get(0), paramOpenIds.get(paramOpenIds.size() - 1), e);
                }
            }
        } catch (Exception e) {
            log.error("处理异常", e);
        }

    }
}
