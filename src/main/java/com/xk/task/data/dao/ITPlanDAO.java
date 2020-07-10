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
    @Select("select * from T_PLAN where TASK_ID=#{taskId}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @Results(id = "planMapper",value = {
            @Result(id = true, property="plan_Id", column="PLAN_ID",javaType = int.class),
            @Result(property="plan_Name",column="PLAN_NAME",javaType = String.class),
            @Result(property="status", column="STATUS",javaType = String.class),
            @Result(property="is_Feedback", column="is_Feedback1",javaType = String.class),
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
    @Delete(  "<script>"
            + "<foreach item='id' index='index' collection='array' >"
            + "or plan_Id=#{id}"
            + "</foreach>"
            + "</script>")
    public int deletePlan(Integer [] objs);

    @Update("update T_PLAN set status=#{status},IS_FEEDBACK=#{is_Feedback},FEEDBACK_INFO=#{feedback_Info} where PLAN_ID=#{plan_Id} ")
    public int updatePlanById(TPlan plan);

    @Select("<script>"

            + "<if test='dto.search_loginId !=0'>"
            + "select * from T_PLAN inner join T_TASK TT on T_PLAN.TASK_ID = TT.TASK_ID where IMPLEMENTOR_ID=#{dto.search_loginId} "
            + "</if>"

            +"<if test='dto.search_loginId ==0'>"
            + "select * from T_PLAN where 1=1 "
            + "</if>"

            + "<if test='dto.search_Plan_Name!=null and dto.search_Plan_Name!=\"\"'>"
            + " and PLAN_NAME like '%'||#{dto.search_Plan_Name}||'%'"
            + "</if>"

            + "<if test='dto.search_Task_Id!=0'>"
            + " and TASK_ID=#{dto.search_Task_Id} "
            + "</if>"

            + "<if test='dto.search_Begin_Date1 !=null and dto.search_Begin_Date2!=null'>"
            + "<if test='dto.search_Begin_Date1.before(dto.search_Begin_Date2)'>"
            + " and tp.BEGIN_DATE between #{dto.search_Begin_Date1} and #{dto.search_Begin_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_End_Date1 !=null and dto.search_End_Date2!=null'>"
            + "<if test='dto.search_End_Date1.before(dto.search_End_Date2)'>"
            + " and END_DATE between #{dto.search_End_Date1} and #{dto.search_End_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_Is_Feedback!=null and dto.search_Is_Feedback!=\"\"'>"
            + " and IS_FEEDBACK=#{dto.search_Is_Feedback}"
            + "</if>"

            + "</script>")
    public List<TPlan> advanceQueryPlan(@Param("dto") PlanDTO dto);

    @Select("<script>"

            + "<if test='dto.search_loginId !=0'>"
            + "select * from (select ROWNUM rm,tp.* from T_PLAN tp inner join T_TASK tt on tp.TASK_ID = tt.TASK_ID where tt.IMPLEMENTOR_ID=#{dto.search_loginId} "
            + "</if>"

            +"<if test='dto.search_loginId ==0'>"
            + "select * from (select ROWNUM rm,tp.* from T_PLAN tp where 1=1 "
            + "</if>"

            + "<if test='dto.search_Plan_Name!=null and dto.search_Plan_Name!=\"\"'>"
            + " and PLAN_NAME like '%'||#{dto.search_Plan_Name}||'%'"
            + "</if>"

            + "<if test='dto.search_Task_Id!=0'>"
            + " and TASK_ID=#{dto.search_Task_Id} "
            + "</if>"

            + "<if test='dto.search_Begin_Date1 !=null and dto.search_Begin_Date2!=null'>"
            + "<if test='dto.search_Begin_Date1.before(dto.search_Begin_Date2)'>"
            + " and tp.BEGIN_DATE between #{dto.search_Begin_Date1} and #{dto.search_Begin_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_End_Date1 !=null and dto.search_End_Date2!=null'>"
            + "<if test='dto.search_End_Date1.before(dto.search_End_Date2)'>"
            + " and END_DATE between #{dto.search_End_Date1} and #{dto.search_End_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_Is_Feedback!=null and dto.search_Is_Feedback!=\"\"'>"
            + " and IS_FEEDBACK=#{dto.search_Is_Feedback}"
            + "</if>"


            +") temp where temp.rm between #{start} and #{end}"
            + "</script>")
    public List<TPlan> advanceQueryPlanByPaging(@Param("dto") PlanDTO dto,@Param("start") int start,@Param("end") int end);

    @Select("<script>"
            + "<if test='dto.search_loginId !=0'>"
            + "select * from (select count(*) from T_PLAN tp inner join T_TASK tt on tp.TASK_ID = tt.TASK_ID where tt.IMPLEMENTOR_ID=#{dto.search_loginId} "
            + "</if>"

            +"<if test='dto.search_loginId ==0'>"
            + "select count(*)  from T_PLAN tp where 1=1 "
            + "</if>"

            + "<if test='dto.search_Plan_Name!=null and dto.search_Plan_Name!=\"\"'>"
            + " and PLAN_NAME like '%'||#{dto.search_Plan_Name}||'%'"
            + "</if>"

            + "<if test='dto.search_Task_Id!=0'>"
            + " and tp.TASK_ID=#{search_Task_Id} "
            + "</if>"

            + "<if test='dto.search_Begin_Date1 !=null and dto.search_Begin_Date2!=null'>"
            + "<if test='dto.search_Begin_Date1.before(dto.search_Begin_Date2)'>"
            + " and tp.BEGIN_DATE between #{dto.search_End_Date1} and #{dto.search_End_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_End_Date1 !=null and dto.search_End_Date2!=null'>"
            + "<if test='dto.search_End_Date1.before(dto.search_End_Date2)'>"
            + " and tp.END_DATE #{dto.search_End_Date1} and #{dto.search_End_Date2} "
            + "</if>"
            + "</if>"

            + "<if test='dto.search_Is_Feedback!=null and dto.search_Is_Feedback!=\"\"'>"
            + " and tp.IS_FEEDBACK=#{dto.search_Is_Feedback}"
            + "</if>"

            + "</script>")
    public int advanceQueryPlanTotalCount(@Param("dto") PlanDTO dto);




}
