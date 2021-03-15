package com.lagou.pojo;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.Serializable;
import java.util.List;

/**
 * @author lijian
 * @create 2021-03-10 22:11
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String birthday;
    private List<Orders> orders;
    private List<SysRole> sysRoles;

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", orders=" + orders +
                ", sysRoles=" + sysRoles +
                '}';
    }
}
