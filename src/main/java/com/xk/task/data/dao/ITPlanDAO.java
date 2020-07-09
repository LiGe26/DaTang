package com.xk.task.data.dao;

import com.xk.task.data.pojo.TPlan;
import com.xk.task.web.util.PlanDTO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface ITPlanDAO {
    /**
     * 根据任务编号查询所有计划的业务
     * @param taskId
     * @return
     */
    @Select("select * from T_PLAN where TASK_ID=#{task_Id}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @Results(id = "planMapper",value = {
            @Result(id = true, property="plan_Id", column="PLAN_ID",javaType = int.class),
            @Result(property="plan_Name",column="PLAN_NAME",javaType = String.class),
            @Result(property="status", column="STATUS",javaType = String.class),
            @Result(property="is_Feedback", column="IS_FEEDBACK",javaType = String.class),
            @Result(property="begin_Date", column="BEGIN_DATE",javaType = Date.class),
            @Result(property="end_Date", column="END_DATE",javaType = Date.class),
            @Result(property="task_Id", column="TASK_ID",javaType = int.class),
            @Result(property="feedback_Info",column="FEEDBACK_INFO",javaType = String.class),
            @Result(property="plan_Desc", column="PLAN_DESC",javaType = String.class)
    })
    public List<TPlan> queryPlanByTaskId(int taskId);

    /**
     * 添加计划
     * @param plan
     * @return
     */
    @Insert("insert into T_PLAN values(0,#{plan_Name},'待实施','否',#{begin_Date},#{end_Date},#{task_Id},null,#{plan_Desc})")
    public int addPlan(TPlan plan);

    @Select("select * from T_PLAN where PLAN_ID=#{plan_Id}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "planMapper")
    public TPlan queryPlanById(int id);

    /**
     * 删除多个计划的业务
     * @param objs
     * @return
     */
    @Delete("delete T_PLAN where plan_Id=0 ")
    public int deletePlan(Integer [] objs);

    @Update("update T_PLAN set status=#{status},IS_FEEDBACK=#{is_Feedback},FEEDBACK_INFO=#{feedback_Info} where PLAN_ID=#{plan_Id} ")
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
