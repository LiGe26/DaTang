package com.xk.task.service;

import com.xk.task.data.pojo.TTask;

import java.util.List;

public interface ITTaskService {
    /**
     * 员工--》根据员工编号查询任务带分配人.
     * @param empId
     * @return
     */
    public List<TTask> queryTaskListAndAssigner(int empId);


    public List<TTask> queryTaskListAndAssignerByPaging(int empId,int start,int end);

    /**
     *
     * @param empId 员工编号
     * @return
     */

    public int queryTaskListAndAssignerTotalCount(int empId);
    /**
     * 修改任务状态
     */
    public int updateTaskStatusBegin(int taskId);

    public int addTask(TTask task);

    public List<TTask> queryTaskListAndImplementor(int empId);

    public List<TTask> queryTaskListAndImplementorByPaging(int empId,int start,int end);

    /**
     * 根据主管编号查询所有未实施的任务业务
     * @param ManagerId 主管编号
     * @return
     */
    public List<TTask> queryTaskUnDoneByManagerIdByPaging(int ManagerId,int start,int emp);
    /**
     * 根据主管编号查询所有实施中的任务业务
     * @param ManagerId 主管编号
     * @return
     */
    public List<TTask> queryTaskDoneByManagerIdByPaging(int ManagerId,int start,int end);

    /**
     * 查询待实施的任务
     * @param managerId 主管编号
     * @return
     */
    public int queryTaskUnDoneByManagerTotalRecords(int managerId);

    /**
     * 查询实施中的任务
     * @param managerId 主管编号
     * @return
     */
    public int queryTaskDoneByManagerTotalRecords(int managerId);
    //根据任务编号查询任务（包括实施人)
    public TTask queryTaskAndImplementorByTaskId(int taskId);

    public int queryTaskListAndImplementorTotalCount(int empId);


    /**
     * 根据任务编号修改任务
     * @return
     */
    public int updateTaskBytaskId(TTask task);

    public int deleteTaskByIds(Integer [] deleteIds);

    public int finishTask(int taskId);


}
