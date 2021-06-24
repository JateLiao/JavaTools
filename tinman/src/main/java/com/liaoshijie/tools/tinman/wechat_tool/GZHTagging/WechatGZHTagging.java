package com.liaoshijie.tools.tinman.wechat_tool.GZHTagging;

import com.google.common.collect.Lists;
import com.liaoshijie.tools.common.utils.HttpUtils;
import com.liaoshijie.tools.common.utils.JsonUtil;
import com.liaoshijie.tools.tinman.wechat_tool.utils.GZHUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/1/14 8:42 下午
 */
@Slf4j
public class WechatGZHTagging {

    private static final Map<String, String> headerDefault = Collections.emptyMap();
    private static final String SUCCESS_BODY = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(30);

    /**
     * main method.
     **/
    public static void main(String[] args) throws IOException {
        String appId = "wx666aebb8536ccb68";
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s";
        String path = "/Users/liaoshijie/Workspace/java/JavaTools/tinman/src/main/java/com/liaoshijie/tools/tinman/wechat_tool/GZHTagging/未购年课.txt";
        Integer tagId = 182;

        File file = new File(path);
        List<String> openIds = FileUtils.readLines(file, "UTF-8");
        List<List<String>> openList = Lists.partition(openIds, 10);
        int index = 0;
        List<Future> futures = new ArrayList<>(openList.size());
        for (List<String> list : openList) {
            //if (index++ <= 1500) {
            //    continue;
            //}
            try {
                Future future = EXECUTOR_SERVICE.submit(() -> {
                    try {
                        String token = GZHUtils.getToken(appId);
                        Map<String, Object> param = new HashMap<>(2);
                        param.put("openid_list", list);
                        param.put("tagid", tagId);
                        String body = HttpUtils.post(String.format(url, token), JsonUtil.toJson(param), headerDefault);
                        if (!Objects.equals(SUCCESS_BODY, body)) {
                            log.error("请求失败,id={},dody={}", list.get(0), body);
                        }
                    } catch (Exception e) {
                        log.error("异常位置,id={}", list.get(0), e);
                    }
                });
                futures.add(future);
            } catch (Exception e) {
                log.error("异常位置,id={}", list.get(0), e);
            }
            log.info("处理批次：{}", index++);
        }
        for (Future future : futures) {
            if (!future.isDone()) {
                try {
                    future.get();
                } catch (Exception e) {
                    log.error("future获取异常", e);
                }
            }
        }

        log.info("打标完成");
    }
}
