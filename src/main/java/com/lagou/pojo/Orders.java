package com.lagou.pojo;

/**
 * @author lijian
 * @create 2021-03-11 23:13
 */
public class Orders {
    private Integer id;
    private String ordertime;
    private String total;
    private Integer uid;

    //订单所属用户
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime='" + ordertime + '\'' +
                ", total='" + total + '\'' +
                ", uid=" + uid +
                ", user=" + user +
                '}';
    }
}
