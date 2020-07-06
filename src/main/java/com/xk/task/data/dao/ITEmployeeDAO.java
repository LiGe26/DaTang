package com.xk.task.data.dao;

import com.xk.task.data.pojo.TEmployee;

import java.util.List;


public interface ITEmployeeDAO {
    /**
     * 员工登录的方法
     * 验证用户名和密码
     * @return
     */
    public TEmployee login(TEmployee employee);

    public List<TEmployee> queryAllEmployees(String sql,Object [] params);

    public int insertEmployee(TEmployee employee);

    public int deleteEmployee(int id);

    /**
     * 修改员工上级
     * @param empId 员工编号
     * @param parent_id 修改的上级编号
     * @return 受影响行数
     */
    public int updateEmployeeParent(int empId, Integer parent_id);

    public int queryForInt(String sql,Object [] params);

}
