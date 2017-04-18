package better517Tools.handleCheckStyle_Comment;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * CabinInfo complex type�� Java �ࡣ.
 * 
 * <p>
 * ����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CabinInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cabin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="baseCabin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cabinGrade" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="seating" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="changePolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="backPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="signPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="airRemark" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="patFlag" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="standardPrice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="initTicketPrice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="protocolTicketPrice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="finallyPrice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="childChangePolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="childBackPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="childSignPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="babyChangePolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="babyBackPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="babySignPolicy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="extInfo" type="{http://op.bee2c.com/DapFlightCabinSearchSrv/}MapString" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CabinInfo", propOrder = {"cabin", "baseCabin", "cabinGrade", "seating", "discount", "changePolicy", "backPolicy", "signPolicy", "airRemark", "patFlag", "standardPrice",
        "initTicketPrice", "protocolTicketPrice", "finallyPrice", "childChangePolicy", "childBackPolicy", "childSignPolicy", "babyChangePolicy", "babyBackPolicy", "babySignPolicy", "extInfo"})
public class CabinInfo {

    @XmlElement(required = true)
    protected String cabin;

    @XmlElement(required = true)
    protected String baseCabin;

    @XmlElement(required = true, nillable = true)
    protected String cabinGrade;

    @XmlElement(required = true)
    protected String seating;

    @XmlElement(required = true)
    protected String discount;

    @XmlElement(required = true)
    protected String changePolicy;

    @XmlElement(required = true)
    protected String backPolicy;

    @XmlElement(required = true)
    protected String signPolicy;

    @XmlElement(required = true, nillable = true)
    protected String airRemark;

    @XmlElement(required = true)
    protected String patFlag;

    @XmlElement(required = true)
    protected String standardPrice;

    @XmlElement(required = true)
    protected String initTicketPrice;

    @XmlElement(required = true)
    protected String protocolTicketPrice;

    @XmlElement(required = true)
    protected String finallyPrice;

    @XmlElement(required = true, nillable = true)
    protected String childChangePolicy;

    @XmlElement(required = true, nillable = true)
    protected String childBackPolicy;

    @XmlElement(required = true, nillable = true)
    protected String childSignPolicy;

    @XmlElement(required = true, nillable = true)
    protected String babyChangePolicy;

    @XmlElement(required = true, nillable = true)
    protected String babyBackPolicy;

    @XmlElement(required = true, nillable = true)
    protected String babySignPolicy;

    protected Map<String, String> extInfo;

    /**
     * ��ȡcabin���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCabin() {
        return cabin;
    }

    /**
     * ����cabin���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCabin(String value) {
        this.cabin = value;
    }

    /**
     * ��ȡbaseCabin���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBaseCabin() {
        return baseCabin;
    }

    /**
     * ����baseCabin���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBaseCabin(String value) {
        this.baseCabin = value;
    }

    /**
     * ��ȡcabinGrade���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCabinGrade() {
        return cabinGrade;
    }

    /**
     * ����cabinGrade���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCabinGrade(String value) {
        this.cabinGrade = value;
    }

    /**
     * ��ȡseating���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSeating() {
        return seating;
    }

    /**
     * ����seating���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSeating(String value) {
        this.seating = value;
    }

    /**
     * ��ȡdiscount���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * ����discount���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDiscount(String value) {
        this.discount = value;
    }

    /**
     * ��ȡchangePolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getChangePolicy() {
        return changePolicy;
    }

    /**
     * ����changePolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setChangePolicy(String value) {
        this.changePolicy = value;
    }

    /**
     * ��ȡbackPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBackPolicy() {
        return backPolicy;
    }

    /**
     * ����backPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBackPolicy(String value) {
        this.backPolicy = value;
    }

    /**
     * ��ȡsignPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSignPolicy() {
        return signPolicy;
    }

    /**
     * ����signPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSignPolicy(String value) {
        this.signPolicy = value;
    }

    /**
     * ��ȡairRemark���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAirRemark() {
        return airRemark;
    }

    /**
     * ����airRemark���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAirRemark(String value) {
        this.airRemark = value;
    }

    /**
     * ��ȡpatFlag���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPatFlag() {
        return patFlag;
    }

    /**
     * ����patFlag���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPatFlag(String value) {
        this.patFlag = value;
    }

    /**
     * ��ȡstandardPrice���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getStandardPrice() {
        return standardPrice;
    }

    /**
     * ����standardPrice���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setStandardPrice(String value) {
        this.standardPrice = value;
    }

    /**
     * ��ȡinitTicketPrice���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getInitTicketPrice() {
        return initTicketPrice;
    }

    /**
     * ����initTicketPrice���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setInitTicketPrice(String value) {
        this.initTicketPrice = value;
    }

    /**
     * ��ȡprotocolTicketPrice���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getProtocolTicketPrice() {
        return protocolTicketPrice;
    }

    /**
     * ����protocolTicketPrice���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setProtocolTicketPrice(String value) {
        this.protocolTicketPrice = value;
    }

    /**
     * ��ȡfinallyPrice���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFinallyPrice() {
        return finallyPrice;
    }

    /**
     * ����finallyPrice���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFinallyPrice(String value) {
        this.finallyPrice = value;
    }

    /**
     * ��ȡchildChangePolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getChildChangePolicy() {
        return childChangePolicy;
    }

    /**
     * ����childChangePolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setChildChangePolicy(String value) {
        this.childChangePolicy = value;
    }

    /**
     * ��ȡchildBackPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getChildBackPolicy() {
        return childBackPolicy;
    }

    /**
     * ����childBackPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setChildBackPolicy(String value) {
        this.childBackPolicy = value;
    }

    /**
     * ��ȡchildSignPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getChildSignPolicy() {
        return childSignPolicy;
    }

    /**
     * ����childSignPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setChildSignPolicy(String value) {
        this.childSignPolicy = value;
    }

    /**
     * ��ȡbabyChangePolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBabyChangePolicy() {
        return babyChangePolicy;
    }

    /**
     * ����babyChangePolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBabyChangePolicy(String value) {
        this.babyChangePolicy = value;
    }

    /**
     * ��ȡbabyBackPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBabyBackPolicy() {
        return babyBackPolicy;
    }

    /**
     * ����babyBackPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBabyBackPolicy(String value) {
        this.babyBackPolicy = value;
    }

    /**
     * ��ȡbabySignPolicy���Ե�ֵ��
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBabySignPolicy() {
        return babySignPolicy;
    }

    /**
     * ����babySignPolicy���Ե�ֵ��
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setBabySignPolicy(String value) {
        this.babySignPolicy = value;
    }

    /**
     * Gets the value of the extInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
     * not a <CODE>set</CODE> method for the extInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getExtInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link MapString }
     * 
     * 
     */
    public Map<String, String> getExtInfo() {
        if (extInfo == null) {
            extInfo = new HashMap<String, String>();
        }

        return this.extInfo;
    }

    /**
     * 获取extInfo.
     * 
     * @param extInfo
     *            要设置的extInfo.
     */
    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

}
