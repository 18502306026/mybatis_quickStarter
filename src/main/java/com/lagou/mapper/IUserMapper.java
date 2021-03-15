package com.lagou.mapper;

import com.lagou.pojo.Orders;
import com.lagou.pojo.SysRole;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lijian
 * @create 2021-03-11 12:37
 */
@CacheNamespace //开启二级缓存
public interface IUserMapper {
    public List<User> findAll();
    public List<User> findByIds(int[] arry);

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "orders",column = "id",javaType = List.class,
                    many = @Many(select = "com.lagou.mapper.IOrderMapper.findOrderByUserId"))
    })
    public List<User> findAllUserAndOrder();

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sysRoles",column = "id",javaType = List.class,
                    many = @Many(select = "com.lagou.mapper.ISysRoleMapper.findRoleByUserId"))
    })
    public List<User> findAllUserAndRole();

    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);
    @Update("update user set username = #{username} where id = #{id}" )
    public void updateUserById(User user);

}
