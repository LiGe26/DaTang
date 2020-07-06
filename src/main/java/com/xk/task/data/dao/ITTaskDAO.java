package com.xk.task.data.dao;

import com.xk.task.data.pojo.TTask;

import java.util.List;

public interface ITTaskDAO {

    public List<TTask> queryTaskListAndAssigner(String sql,Object [] params);

    public List<TTask> queryTaskListAndImplementor(String sql, Object[] params);

    public int updateTask(String sql,Object [] params);

    public int getTotalTaskRecordCount(String sql,Object [] params);
}
