package com.xk.task.data.dao;

import com.xk.task.data.pojo.TPlan;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface ITPlanDAO {
    /**
     * 根据任务编号查询所有计划的业务
     * @param taskId
     * @return
     */
    @Select("select * from T_PLAN where TASK_ID=#{taskId}")
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

    @Select("select * from T_PLAN where PLAN_ID=#{id}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "planMapper")
    public TPlan queryPlanById(int id);


    /**
     * 删除多个计划的业务
     * @param objs
     * @return
     */
    @Delete(  "<script>delete T_PLAN where plan_Id=0"
            + "<foreach item='id' index='index' collection='array' >"
            + "or plan_Id=#{id}"
            + "</foreach>"
            + "</script>")
    public int deletePlan(Integer [] objs);

    @Update("update T_PLAN set status=#{status},IS_FEEDBACK=#{is_Feedback},FEEDBACK_INFO=#{feedback_Info} where PLAN_ID=#{plan_Id} ")
    public int updatePlanById(TPlan plan);

    public List<TPlan> queryPlanList(String sql,Object [] params);

    public int queryPlanForInt(String sql,Object [] params);


}
