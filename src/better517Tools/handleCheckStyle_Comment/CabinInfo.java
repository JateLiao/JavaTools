/*
 * 鏂囦欢鍚嶏細CabinInfo.java
 * 鐗堟潈锛欳opyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 鎻忚堪锛?CabinInfo.java
 * 淇敼浜猴細tianzhong
 * 淇敼鏃堕棿锛?017骞?4鏈?6鏃?
 * 淇敼鍐呭锛氭柊澧?
 */
package better517Tools.handleCheckStyle_Comment;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author tianzhong
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CabinInfo", propOrder = {"cabin", "baseCabin", "cabinGrade", "seating", "discount", "changePolicy", "backPolicy", "signPolicy", "airRemark", "patFlag", "standardPrice",
        "initTicketPrice", "protocolTicketPrice", "finallyPrice", "childChangePolicy", "childBackPolicy", "childSignPolicy", "babyChangePolicy", "babyBackPolicy", "babySignPolicy", "extInfo"})
public class CabinInfo {

    /**
     * cabin.
     */
    @XmlElement(required = true)
    protected String cabin;

    /**
     * baseCabin.
     */
    @XmlElement(required = true)
    protected String baseCabin;

    /**
     * cabinGrade.
     */
    @XmlElement(required = true, nillable = true)
    protected String cabinGrade;

    /**
     * seating.
     */
    @XmlElement(required = true)
    protected String seating;

    /**
     * discount.
     */
    @XmlElement(required = true)
    protected String discount;

    /**
     * changePolicy.
     */
    @XmlElement(required = true)
    protected String changePolicy;

    /**
     * backPolicy.
     */
    @XmlElement(required = true)
    protected String backPolicy;

    /**
     * signPolicy.
     */
    @XmlElement(required = true)
    protected String signPolicy;

    /**
     * airRemark.
     */
    @XmlElement(required = true, nillable = true)
    protected String airRemark;

    /**
     * patFlag.
     */
    @XmlElement(required = true)
    protected String patFlag;

    /**
     * standardPrice.
     */
    @XmlElement(required = true)
    protected String standardPrice;

    /**
     * initTicketPrice.
     */
    @XmlElement(required = true)
    protected String initTicketPrice;

    /**
     * protocolTicketPrice.
     */
    @XmlElement(required = true)
    protected String protocolTicketPrice;

    /**
     * finallyPrice.
     */
    @XmlElement(required = true)
    protected String finallyPrice;

    /**
     * childChangePolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String childChangePolicy;

    /**
     * childBackPolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String childBackPolicy;

    /**
     * childSignPolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String childSignPolicy;

    /**
     * babyChangePolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String babyChangePolicy;

    /**
     * babyBackPolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String babyBackPolicy;

    /**
     * babySignPolicy.
     */
    @XmlElement(required = true, nillable = true)
    protected String babySignPolicy;

    /**
     * extInfo.
     */
    protected Map<String, String> extInfo;

    /**
     * cabin.
     *
     * @return 杩斿洖cabin.
     */    public String getcabin () {
        return cabin;
    }

    /**
     * 璁剧疆cabin.
     *
     * @param cabin 瑕佽缃殑cabin.
     */
    public void setcabin(String cabin) {
        this.cabin = cabin;
    }
    /**
     * baseCabin.
     *
     * @return 杩斿洖baseCabin.
     */    public String getbaseCabin () {
        return baseCabin;
    }

    /**
     * 璁剧疆baseCabin.
     *
     * @param baseCabin 瑕佽缃殑baseCabin.
     */
    public void setbaseCabin(String baseCabin) {
        this.baseCabin = baseCabin;
    }
    /**
     * cabinGrade.
     *
     * @return 杩斿洖cabinGrade.
     */    public String getcabinGrade () {
        return cabinGrade;
    }

    /**
     * 璁剧疆cabinGrade.
     *
     * @param cabinGrade 瑕佽缃殑cabinGrade.
     */
    public void setcabinGrade(String cabinGrade) {
        this.cabinGrade = cabinGrade;
    }
    /**
     * seating.
     *
     * @return 杩斿洖seating.
     */    public String getseating () {
        return seating;
    }

    /**
     * 璁剧疆seating.
     *
     * @param seating 瑕佽缃殑seating.
     */
    public void setseating(String seating) {
        this.seating = seating;
    }
    /**
     * discount.
     *
     * @return 杩斿洖discount.
     */    public String getdiscount () {
        return discount;
    }

    /**
     * 璁剧疆discount.
     *
     * @param discount 瑕佽缃殑discount.
     */
    public void setdiscount(String discount) {
        this.discount = discount;
    }
    /**
     * changePolicy.
     *
     * @return 杩斿洖changePolicy.
     */    public String getchangePolicy () {
        return changePolicy;
    }

    /**
     * 璁剧疆changePolicy.
     *
     * @param changePolicy 瑕佽缃殑changePolicy.
     */
    public void setchangePolicy(String changePolicy) {
        this.changePolicy = changePolicy;
    }
    /**
     * backPolicy.
     *
     * @return 杩斿洖backPolicy.
     */    public String getbackPolicy () {
        return backPolicy;
    }

    /**
     * 璁剧疆backPolicy.
     *
     * @param backPolicy 瑕佽缃殑backPolicy.
     */
    public void setbackPolicy(String backPolicy) {
        this.backPolicy = backPolicy;
    }
    /**
     * signPolicy.
     *
     * @return 杩斿洖signPolicy.
     */    public String getsignPolicy () {
        return signPolicy;
    }

    /**
     * 璁剧疆signPolicy.
     *
     * @param signPolicy 瑕佽缃殑signPolicy.
     */
    public void setsignPolicy(String signPolicy) {
        this.signPolicy = signPolicy;
    }
    /**
     * airRemark.
     *
     * @return 杩斿洖airRemark.
     */    public String getairRemark () {
        return airRemark;
    }

    /**
     * 璁剧疆airRemark.
     *
     * @param airRemark 瑕佽缃殑airRemark.
     */
    public void setairRemark(String airRemark) {
        this.airRemark = airRemark;
    }
    /**
     * patFlag.
     *
     * @return 杩斿洖patFlag.
     */    public String getpatFlag () {
        return patFlag;
    }

    /**
     * 璁剧疆patFlag.
     *
     * @param patFlag 瑕佽缃殑patFlag.
     */
    public void setpatFlag(String patFlag) {
        this.patFlag = patFlag;
    }
    /**
     * standardPrice.
     *
     * @return 杩斿洖standardPrice.
     */    public String getstandardPrice () {
        return standardPrice;
    }

    /**
     * 璁剧疆standardPrice.
     *
     * @param standardPrice 瑕佽缃殑standardPrice.
     */
    public void setstandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }
    /**
     * initTicketPrice.
     *
     * @return 杩斿洖initTicketPrice.
     */    public String getinitTicketPrice () {
        return initTicketPrice;
    }

    /**
     * 璁剧疆initTicketPrice.
     *
     * @param initTicketPrice 瑕佽缃殑initTicketPrice.
     */
    public void setinitTicketPrice(String initTicketPrice) {
        this.initTicketPrice = initTicketPrice;
    }
    /**
     * protocolTicketPrice.
     *
     * @return 杩斿洖protocolTicketPrice.
     */    public String getprotocolTicketPrice () {
        return protocolTicketPrice;
    }

    /**
     * 璁剧疆protocolTicketPrice.
     *
     * @param protocolTicketPrice 瑕佽缃殑protocolTicketPrice.
     */
    public void setprotocolTicketPrice(String protocolTicketPrice) {
        this.protocolTicketPrice = protocolTicketPrice;
    }
    /**
     * finallyPrice.
     *
     * @return 杩斿洖finallyPrice.
     */    public String getfinallyPrice () {
        return finallyPrice;
    }

    /**
     * 璁剧疆finallyPrice.
     *
     * @param finallyPrice 瑕佽缃殑finallyPrice.
     */
    public void setfinallyPrice(String finallyPrice) {
        this.finallyPrice = finallyPrice;
    }
    /**
     * childChangePolicy.
     *
     * @return 杩斿洖childChangePolicy.
     */    public String getchildChangePolicy () {
        return childChangePolicy;
    }

    /**
     * 璁剧疆childChangePolicy.
     *
     * @param childChangePolicy 瑕佽缃殑childChangePolicy.
     */
    public void setchildChangePolicy(String childChangePolicy) {
        this.childChangePolicy = childChangePolicy;
    }
    /**
     * childBackPolicy.
     *
     * @return 杩斿洖childBackPolicy.
     */    public String getchildBackPolicy () {
        return childBackPolicy;
    }

    /**
     * 璁剧疆childBackPolicy.
     *
     * @param childBackPolicy 瑕佽缃殑childBackPolicy.
     */
    public void setchildBackPolicy(String childBackPolicy) {
        this.childBackPolicy = childBackPolicy;
    }
    /**
     * childSignPolicy.
     *
     * @return 杩斿洖childSignPolicy.
     */    public String getchildSignPolicy () {
        return childSignPolicy;
    }

    /**
     * 璁剧疆childSignPolicy.
     *
     * @param childSignPolicy 瑕佽缃殑childSignPolicy.
     */
    public void setchildSignPolicy(String childSignPolicy) {
        this.childSignPolicy = childSignPolicy;
    }
    /**
     * babyChangePolicy.
     *
     * @return 杩斿洖babyChangePolicy.
     */    public String getbabyChangePolicy () {
        return babyChangePolicy;
    }

    /**
     * 璁剧疆babyChangePolicy.
     *
     * @param babyChangePolicy 瑕佽缃殑babyChangePolicy.
     */
    public void setbabyChangePolicy(String babyChangePolicy) {
        this.babyChangePolicy = babyChangePolicy;
    }
    /**
     * babyBackPolicy.
     *
     * @return 杩斿洖babyBackPolicy.
     */    public String getbabyBackPolicy () {
        return babyBackPolicy;
    }

    /**
     * 璁剧疆babyBackPolicy.
     *
     * @param babyBackPolicy 瑕佽缃殑babyBackPolicy.
     */
    public void setbabyBackPolicy(String babyBackPolicy) {
        this.babyBackPolicy = babyBackPolicy;
    }
    /**
     * babySignPolicy.
     *
     * @return 杩斿洖babySignPolicy.
     */    public String getbabySignPolicy () {
        return babySignPolicy;
    }

    /**
     * 璁剧疆babySignPolicy.
     *
     * @param babySignPolicy 瑕佽缃殑babySignPolicy.
     */
    public void setbabySignPolicy(String babySignPolicy) {
        this.babySignPolicy = babySignPolicy;
    } 
}

