package com.liaoshijie.tools.tinman.wechat_tool.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/11 下午5:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBaseBo {
    private Integer errcode;
    private String errmsg;
}
