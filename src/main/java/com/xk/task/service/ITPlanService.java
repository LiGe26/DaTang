package com.xk.task.service;

import com.xk.task.data.pojo.TPlan;
import com.xk.task.web.util.PlanDTO;

import java.util.List;

public interface ITPlanService {
    /**
     * 根据任务编号查询所有计划的业务
     * @param taskId
     * @return
     */
    public List<TPlan> queryPlanByTaskId(int taskId);

    /**
     * 添加计划
     * @param plan
     * @return
     */
    public int addPlan(TPlan plan);

    public TPlan queryPlanById(int id);

    /**
     * 删除多个计划的业务
     * @param objs
     * @return
     */
    public int deletePlan(Integer [] objs);

    public int updatePlanById(TPlan plan);

    /**
     * 根据数据查询对象查询计划
     * @param dto 数据传输对象
     * @return
     */
    public List<TPlan> advanceQueryPlan(PlanDTO dto);

    public List<TPlan> advanceQueryPlanByPaging(PlanDTO dto ,int start,int end);

    /**
     *
     * @param dto 数据传输对象
     * @return 返回计划总数
     */
    public int advanceQueryPlanTotalCount(PlanDTO dto);
}
