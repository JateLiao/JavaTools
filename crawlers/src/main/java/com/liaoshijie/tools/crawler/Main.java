/*
 * 文件名：Main.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Main.java
 * 修改人：tianzhong
 * 修改时间：2017年11月13日
 * 修改内容：新增
 */
package com.liaoshijie.tools.crawler;

import com.liaoshijie.tools.common.utils.HttpUtils;

import static com.liaoshijie.tools.common.utils.HttpUtils.HEADER_DEFAULT;

/**
 * TODO 添加类的一句话简单描述.
 * @author     tianzhong
 */
public class Main {

    /**
     * TODO 添加方法注释.
     * @param args
     */
    public static void main(String[] args) {
        //ComicCrawler.doMain();
        String resource = HttpUtils.get("https://manhua.fzdm.com/2/1010/index_1.html", HEADER_DEFAULT);
        System.out.println(resource);
    }


}
