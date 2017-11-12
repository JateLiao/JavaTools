/*
 * 文件名：CommicVo.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： CommicVo.java
 * 修改人：KOBE
 * 修改时间：2017年11月12日
 * 修改内容：新增
 */
package crawlers.commicrawler.model;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author KOBE
 */
public class CommicVo {

    /**
     * 添加字段注释.
     */
    private String commicNo;

    /**
     * 添加字段注释.
     */
    private String commicName;

    /**
     * 漫画集数.
     */
    private String commicChapterNo;

    /**
     * 设置commicNo.
     * 
     * @return 返回commicNo
     */
    public String getCommicNo() {
        return commicNo;
    }

    /**
     * 设置commicChapterNo.
     * 
     * @return 返回commicChapterNo
     */
    public String getCommicChapterNo() {
        return commicChapterNo;
    }

    /**
     * 获取commicChapterNo.
     * 
     * @param commicChapterNo
     *            要设置的commicChapterNo
     */
    public void setCommicChapterNo(String commicChapterNo) {
        this.commicChapterNo = commicChapterNo;
    }

    /**
     * 获取commicNo.
     * 
     * @param commicNo
     *            要设置的commicNo
     */
    public void setCommicNo(String commicNo) {
        this.commicNo = commicNo;
    }

    /**
     * 设置commicName.
     * 
     * @return 返回commicName
     */
    public String getCommicName() {
        return commicName;
    }

    /**
     * 获取commicName.
     * 
     * @param commicName
     *            要设置的commicName
     */
    public void setCommicName(String commicName) {
        this.commicName = commicName;
    }

}
