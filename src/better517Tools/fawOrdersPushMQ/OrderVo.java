package better517Tools.fawOrdersPushMQ;

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
    private String orderID;
    
    /**
     * OrderStatus.
     */
    private Integer orderStatus;
    /**
     * orderType.
     */
    private Integer orderType;
    
    public String getOrderID() {
        return orderID;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    
    public Integer getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public Integer getOrderType() {
        return orderType;
    }
    
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
