package com.xk.task.data.dao;

import com.xk.task.data.pojo.TRole;

import java.util.List;

public interface ITRoleDAO {
    /**
     * 根据角色编号查询角色
     * @return
     */
    public TRole queryRoleById(int id);

    /**
     * 查询所有员工的方法
     * @return 员工集合
     */
    public List<TRole> queryAllRoles();
}
