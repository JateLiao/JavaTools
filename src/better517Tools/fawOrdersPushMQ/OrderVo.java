package better517Tools.fawOrdersPushMQ;

import com.google.gson.annotations.SerializedName;

/**
 * @Desc OrderVo
 * @ProjectName JavaTools
 * @Company com.lsj
 * @CreateTime 2018/5/25 20:23
 * @Author tianzhong
 */
public class OrderVo {
    
    /**
     * OrderID.
     */
    @SerializedName ("OrderID")
    private String orderID;
    
    /**
     * OrderStatus.
     */
    @SerializedName ("OrderStatus")
    private String orderStatus;
    /**
     * orderType.
     */
    @SerializedName ("OrderType")
    private String orderType;
    
    public String getOrderID() {
        return orderID;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
