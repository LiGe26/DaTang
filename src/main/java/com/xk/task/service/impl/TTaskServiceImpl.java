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
        //任务--》查詢对应分配人
        String sql="select * from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=?";
        return dao.queryTaskListAndAssigner(sql,new Object[]{empId});
    }
    @Override
    public List<TTask> queryTaskListAndAssignerByPaging(int empId,int start,int end) {
        //任务--=》+查詢对应分配人
        String sql="select * from(select row_number()  over ( order by t.TASK_ID desc) rm,t.*,TE.* from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=?)" +
                "where rm between ? and ?";
        return dao.queryTaskListAndAssigner(sql,new Object[]{empId,start,end});
    }

    @Override
    public int queryTaskListAndAssignerTotalCount(int empId) {
        //任务--=》+查詢对应分配人
        String sql="select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=?";
        return dao.getTotalTaskRecordCount(sql,new Object[]{empId});
    }

    @Override
    public int updateTaskStatusBegin(int taskId) {
        String sql="update T_TASK set STATUS='实施中' where TASK_ID=?" ;
        return dao.updateTask(sql,new Object[]{taskId});
    }

    @Override
    public int addTask(TTask task) {
        String sql="insert into T_TASK values(0,?,?,?,null,null,'待实施',?,?,?)";
        return dao.updateTask(sql,new Object[]{task.getTask_Name(),task.getBegin_Date(),task.getEnd_Date(),
        task.getImplementor().getEmployee_Id(),task.getAssigner().getEmployee_Id()
        ,task.getTask_Desc()});
    }

    /**
     * 主管 -》 根据任务编号
     * @param empId
     * @return
     */
    @Override
    public List<TTask> queryTaskListAndImplementor(int empId) {
        //任务--》主管通过员工表连表查询
        String sql="select * from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where t.ASSIGNER_ID=?";
        return dao.queryTaskListAndImplementor(sql,new Object[]{empId});
    }

    @Override
    public List<TTask> queryTaskListAndImplementorByPaging(int empId, int start, int end) {
        String sql="select * from(select row_number()  over ( order by t.TASK_ID desc) rm,t.*,TE.* from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where t.ASSIGNER_ID=? ) temp where temp.rm between ? and ?";
        return dao.queryTaskListAndImplementor(sql,new Object[]{empId,start,end});
    }

    @Override
    public List<TTask> queryTaskUnDoneByManagerIdByPaging(int ManagerId,int start,int end) {
        String sql="select * from (select t.*,row_number()  over ( order by t.TASK_ID desc) rm,te.* from T_TASK t inner join T_EMPLOYEE te on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where t.ASSIGNER_ID=? and STATUS='待实施' )temp where temp.rm between ? and ?";
        return dao.queryTaskListAndImplementor(sql,new Object[]{ManagerId,start,end});
    }

    @Override
    public List<TTask> queryTaskDoneByManagerIdByPaging(int ManagerId,int start,int end) {
        String sql="select * from (select row_number()  over ( order by t.TASK_ID desc) rm,t.*,te.* from T_TASK t inner join T_EMPLOYEE te on t.IMPLEMENTOR_ID = te.EMPLOYEE_ID  where t.ASSIGNER_ID=? and STATUS='实施中' )temp where temp.rm between ? and ?";
        return dao.queryTaskListAndImplementor(sql,new Object[]{ManagerId,start,end});
    }

    @Override
    public int queryTaskUnDoneByManagerTotalRecords(int managerId) {
        String sql="select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where ASSIGNER_ID=? and STATUS='待实施'";
        return dao.getTotalTaskRecordCount(sql,new Object[]{managerId});
    }

    @Override
    public int queryTaskDoneByManagerTotalRecords(int managerId) {
        String sql="select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where ASSIGNER_ID=? and STATUS='实施中'";
        return dao.getTotalTaskRecordCount(sql,new Object[]{managerId});
    }

    @Override
    public TTask queryTaskAndImplementorByTaskId(int taskId) {
        String sql="select * from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where TASK_ID=?";
        return dao.queryTaskListAndImplementor(sql,new Object[]{taskId}).get(0);
    }

    @Override
    public int queryTaskListAndImplementorTotalCount(int managerId) {
        String sql="select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where ASSIGNER_ID=?" ;
        return dao.getTotalTaskRecordCount(sql,new Object[]{managerId});
    }

    @Override
    public int updateTaskBytaskId(TTask task) {
        String sql="update T_TASK set TASK_NAME=?,BEGIN_DATE=?,END_DATE=?,TASK_DESC=?,IMPLEMENTOR_ID=? where TASK_ID=?";
        return dao.updateTask(sql,new Object[]{task.getTask_Name(),task.getBegin_Date(),task.getEnd_Date(),
                task.getTask_Desc(),task.getImplementor().getEmployee_Id(),task.getTask_Id()});
    }

    @Override
    public int deleteTaskByIds(Integer[] deleteIds) {
        StringBuffer sbf=new StringBuffer();
        sbf.append("delete T_TASK where TASK_ID=0 ");
        if(deleteIds!=null){
            //不为空 拼接sql
            for (Integer id:deleteIds){
                sbf.append(" or TASK_ID=? ");
            }
            System.out.println(sbf.toString());
            dao.updateTask(sbf.toString(),deleteIds);//删除多条数据成功会返回0
            return 1;//代表删除成功
        }else {
            return 0;
        }

    }

    @Override
    public int finishTask(int taskId) {
        String sql="update T_TASK set STATUS='已完成' where TASK_ID=?";
        return dao.updateTask(sql,new Object[]{taskId});
    }

}
