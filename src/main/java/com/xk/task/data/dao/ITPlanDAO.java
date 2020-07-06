package com.xk.task.data.dao;

import com.xk.task.data.pojo.TPlan;

import java.util.List;

public interface ITPlanDAO {
    /**
     * 计划查询
     * @param sql
     * @param params
     * @return
     */
    public List<TPlan> queryPlanList(String sql,Object [] params);

    public int updatePlan(String sql,Object [] params);

    /**
     * 传入数组删除多个计划
     * @param sql
     * @param params
     * @return
     */
    public int deletePlansById(String sql,Object [] params);


    public int queryPlanForInt(String sql,Object [] params);



}
