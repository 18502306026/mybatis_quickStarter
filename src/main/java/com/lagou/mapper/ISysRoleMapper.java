package com.lagou.mapper;

import com.lagou.pojo.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lijian
 * @create 2021-03-12 23:51
 */
public interface ISysRoleMapper {
    @Select("select * from sys_role a ,sys_user_role b where a.id=b.roleid and b.userid=#{userId}")
    public List<SysRole> findRoleByUserId(Integer userId);
}
