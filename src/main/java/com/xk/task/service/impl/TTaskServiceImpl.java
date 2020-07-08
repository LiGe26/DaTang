package com.xk.task.service.impl;

import com.xk.task.data.dao.ITTaskDAO;
import com.xk.task.data.pojo.TTask;
import com.xk.task.service.ITTaskService;
import com.xk.task.web.util.TaskInfo;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Templates;
import java.util.List;

@Service("taskService")
public class TTaskServiceImpl implements ITTaskService {
    @Resource(name = "taskDao")
    private ITTaskDAO dao;

    /**
     * 查询员工的主管及任务
     * @param empId 实施人
     * @return
     */
    @Override
    public List<TTask> queryTaskListAndAssigner(int empId) {
        return dao.queryTaskListAndAssigner(empId);
    }
    @Override
    public List<TTask> queryTaskListAndAssignerByPaging(int empId,int start,int end) {
        return dao.queryTaskListAndAssignerByPaging(empId,start,end);
    }

    @Override
    public int queryTaskListAndAssignerTotalCount(int empId) {
        //任务--=》+查詢对应分配人
        return dao.queryTaskListAndAssignerTotalCount(empId);
    }

    @Override
    public int updateTaskStatusBegin(int taskId) {
        return dao.updateTaskStatusBegin(taskId);
    }

    @Override
    public int addTask(TTask task) {
        return dao.addTask(task);
    }

    /**
     * 主管 -》 根据任务编号
     * @param empId
     * @return
     */
    @Override
    public List<TTask> queryTaskListAndImplementor(int empId) {
        return dao.queryTaskListAndImplementor(empId);
    }

    @Override
    public List<TTask> queryTaskListAndImplementorByPaging(int empId, int start, int end) {
        return dao.queryTaskListAndImplementorByPaging(empId,start,end);
    }

    @Override
    public List<TTask> queryTaskUnDoneByManagerIdByPaging(int managerId,int start,int end) {
        return dao.queryTaskUnDoneByManagerIdByPaging(managerId,start,end);
    }

    @Override
    public List<TTask> queryTaskDoneByManagerIdByPaging(int managerId,int start,int end) {
        return dao.queryTaskDoneByManagerIdByPaging(managerId,start,end);
    }

    @Override
    public int queryTaskUnDoneByManagerTotalRecords(int managerId) {
        return dao.queryTaskUnDoneByManagerTotalRecords(managerId);
    }

    @Override
    public int queryTaskDoneByManagerTotalRecords(int managerId) {
        return dao.queryTaskDoneByManagerTotalRecords(managerId);
    }

    @Override
    public TTask queryTaskAndImplementorByTaskId(int taskId) {
        return dao.queryTaskAndImplementorByTaskId(taskId);
    }

    @Override
    public int queryTaskListAndImplementorTotalCount(int managerId) {
        return dao.queryTaskListAndImplementorTotalCount(managerId);
    }

    @Override
    public int updateTaskBytaskId(TTask task) {
        return dao.updateTaskBytaskId(task);
    }

    @Override
    public int deleteTaskByIds(Integer[] deleteIds) {

        return  dao.deleteTaskByIds(deleteIds);

    }

    @Override
    public int finishTask(int taskId) {
        return dao.finishTask(taskId);
    }

}
