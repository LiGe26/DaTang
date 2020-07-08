package com.xk.task.data.dao;

import com.xk.task.data.pojo.TRole;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ITRoleDAO {
    /**
     * 根据角色编号查询角色
     * @return
     */

    @Select("select * from T_ROLE where ROLE_ID=#{id}")
    @Results(id="roleMapper",value = {
            @Result(id = true,property = "role_Id",column = "role_Id",javaType = int.class),
            @Result(property = "role_Name",column = "role_Name",javaType = String.class),
            @Result(property = "role_Desc",column = "role_Desc",javaType = String.class)
    })
    public TRole queryRoleById(int id);

    /**
     * 查询所有员工的方法
     * @return 员工集合
     */
    @ResultMap("roleMapper")
    @Select("select * from T_ROLE")
    public List<TRole> queryAllRoles();

}
