package com.lagou.mapper;

import com.lagou.pojo.Orders;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lijian
 * @create 2021-03-11 12:37
 */
public interface IOrderMapper {

    @Select("select * from orders")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one = @One(select = "com.lagou.mapper.IUserMapper.findUserById"))
    })
    public List<Orders> selectOrderAndUser();

    @Select("select * from orders where uid = #{userId}")
    public List<Orders> findOrderByUserId(Integer userId);
}
