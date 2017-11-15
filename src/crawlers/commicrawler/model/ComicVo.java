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
public class ComicVo {

    /**
     * 添加字段注释.
     */
    private String comicNo;

    /**
     * 添加字段注释.
     */
    private String comicName;

    /**
     * 漫画集数.
     */
    private String comicChapterNo;

    /**
     * 漫画集数.
     */
    private int currentChapterNo;

    /**
     * 设置currentChapterNo.
     * 
     * @return 返回currentChapterNo
     */
    public int getCurrentChapterNo() {
        return currentChapterNo;
    }

    /**
     * 获取currentChapterNo.
     * 
     * @param currentChapterNo
     *            要设置的currentChapterNo
     */
    public void setCurrentChapterNo(int currentChapterNo) {
        this.currentChapterNo = currentChapterNo;
    }

    /**
     * 设置commicNo.
     * 
     * @return 返回commicNo
     */
    public String getComicNo() {
        return comicNo;
    }

    /**
     * 设置commicChapterNo.
     * 
     * @return 返回commicChapterNo
     */
    public String getComicChapterNo() {
        return comicChapterNo;
    }

    /**
     * 获取commicChapterNo.
     * 
     * @param commicChapterNo
     *            要设置的commicChapterNo
     */
    public void setComicChapterNo(String commicChapterNo) {
        this.comicChapterNo = commicChapterNo;
    }

    /**
     * 获取commicNo.
     * 
     * @param commicNo
     *            要设置的commicNo
     */
    public void setComicNo(String commicNo) {
        this.comicNo = commicNo;
    }

    /**
     * 设置commicName.
     * 
     * @return 返回commicName
     */
    public String getComicName() {
        return comicName;
    }

    /**
     * 获取commicName.
     * 
     * @param commicName
     *            要设置的commicName
     */
    public void setComicName(String commicName) {
        this.comicName = commicName;
    }

}
