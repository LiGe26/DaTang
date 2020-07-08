package com.xk.task.data.dao;

import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TTask;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ITTaskDAO {


    @Select("select * from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=#{id}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @Results(id = "assignerTaskMapper",value = {
            @Result(id = true,column = "task_Id",property = "task_Id",javaType = int.class),
            @Result(column = "task_Name",property = "task_Name",javaType = String.class),
            @Result(column = "begin_Date",property = "begin_Date",javaType = Date.class),
            @Result(column = "end_Date",property = "end_Date",javaType = Date.class),
            @Result(column = "real_Begin_Date",property = "real_Begin_Date",javaType = Date.class),
            @Result(column = "real_End_Date",property = "real_End_Date",javaType = Date.class),
            @Result(column = "status",property = "status",javaType = String.class),
            @Result(column = "IMPLEMENTOR_ID",property ="implementor.employee_Id",javaType = int.class),
            @Result(column = "ASSIGNER_ID",property ="assigner.employee_Id",javaType = int.class),
            @Result(column = "reaL_Name",property ="assigner.reaL_Name" ),
            @Result(column = "TASK_DESC",property ="task_Desc",javaType = String.class)
    })
    public List<TTask> queryTaskListAndAssigner(@Param("id") int empId);

    @Select("select * from(select row_number()  over ( order by t.TASK_ID desc) rm," +
            "t.*,TE.* from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  " +
            "where IMPLEMENTOR_ID=#{empId}) where rm between #{start} and #{end}")
    @ResultMap("assignerTaskMapper")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    public List<TTask> queryTaskListAndAssignerByPaging(@Param("empId") int empId,@Param("start") int start,@Param("end")int end) ;

    @Select("select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=#{empId}")
    @ResultType(int.class)
    public int queryTaskListAndAssignerTotalCount(@Param("empId")int empId);


    @Update("update T_TASK set STATUS='实施中' where TASK_ID=#{taskId}")
    @ResultType(int.class)
    public int updateTaskStatusBegin(int taskId) ;


    @Insert("insert into T_TASK values(0,#{task_Name},#{begin_Date},#{end_Date},null,null,'待实施',#{implementor.employee_Id},#{assigner.employee_Id},#{task_Desc})")
    @ResultType(int.class)
    public int addTask(TTask task);


    @Select("select * from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where t.ASSIGNER_ID=#{empId}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @Results(id = "implementorTaskMapper",value = {
            @Result(id = true,column = "task_Id",property = "task_Id",javaType = int.class),
            @Result(column = "task_Name",property = "task_Name",javaType = String.class),
            @Result(column = "begin_Date",property = "begin_Date",javaType = Date.class),
            @Result(column = "end_Date",property = "end_Date",javaType = Date.class),
            @Result(column = "real_Begin_Date",property = "real_Begin_Date",javaType = Date.class),
            @Result(column = "real_End_Date",property = "real_End_Date",javaType = Date.class),
            @Result(column = "status",property = "status",javaType = String.class),
            @Result(column = "IMPLEMENTOR_ID",property ="implementor.employee_Id",javaType = int.class),
            @Result(column = "reaL_Name",property ="implementor.reaL_Name",javaType = String.class),
            @Result(column = "ASSIGNER_ID",property ="assigner.employee_Id",javaType = int.class),
            @Result(column = "TASK_DESC",property ="task_Desc",javaType = String.class)
    })
    public List<TTask> queryTaskListAndImplementor(int empId);


    @Select("select * from(select row_number()  over ( order by t.TASK_ID desc) rm," +
            "t.*,TE.* from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID" +
            "  where t.ASSIGNER_ID=#{empId}) where rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultMap("implementorTaskMapper")
    public List<TTask> queryTaskListAndImplementorByPaging(@Param("empId") int empId,@Param("start") int start,@Param("end")int end);



    @Select("select * from (select t.*,row_number()  over ( order by t.TASK_ID desc) rm," +
            "te.* from T_TASK t inner join T_EMPLOYEE te on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  " +
            "where t.ASSIGNER_ID=#{managerId} and STATUS='待实施' )temp where temp.rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultMap("implementorTaskMapper")
    public List<TTask> queryTaskUnDoneByManagerIdByPaging(@Param("managerId") int managerId,@Param("start")int start,@Param("end")int end);


    @Select("select * from (select row_number()  over ( order by t.TASK_ID desc) rm," +
            "t.*,te.* from T_TASK t inner join T_EMPLOYEE te on t.IMPLEMENTOR_ID = te.EMPLOYEE_ID  " +
            "where t.ASSIGNER_ID=#{managerId} and STATUS='实施中' )temp where temp.rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultMap("implementorTaskMapper")
    public List<TTask> queryTaskDoneByManagerIdByPaging(@Param("managerId") int managerId,@Param("start")int start,@Param("end")int end);


    @Select("select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  " +
            "where ASSIGNER_ID=#{managerId} and STATUS='待实施'")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultType(int.class)
    public int queryTaskUnDoneByManagerTotalRecords(int managerId);

    @Select("select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where ASSIGNER_ID=#{managerId} and STATUS='实施中'")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultType(int.class)
    public int queryTaskDoneByManagerTotalRecords(int managerId);


    @Select("select * from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where TASK_ID=#{taskId}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultMap("implementorTaskMapper")
    public TTask queryTaskAndImplementorByTaskId(int taskId);


    @Select("select count(*) from T_TASK t inner join T_EMPLOYEE TE on t.IMPLEMENTOR_ID = TE.EMPLOYEE_ID  where ASSIGNER_ID=#{managerId}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE,useCache = true,timeout = 10000)
    @ResultType(int.class)
    public int queryTaskListAndImplementorTotalCount(int managerId);


    @Update("update T_TASK set TASK_NAME=#{task_Name},BEGIN_DATE=#{begin_Date},END_DATE=#{end_Date}," +
            "TASK_DESC=#{task_Desc},IMPLEMENTOR_ID=#{implementor.employee_Id} where TASK_ID=#{task_Id}")
    public int updateTaskBytaskId(TTask task);


    @Delete(  "<script>delete T_TASK where TASK_ID=0"
            + "<foreach item='id' index='index' collection='array' >"
            + "or TASK_ID=#{id}"
            + "</foreach>"
            + "</script>")
    public int deleteTaskByIds(Integer[] deleteIds);


    @Update("update T_TASK set STATUS='已完成' where TASK_ID=#{taskId}")
    public int finishTask(int taskId);


}
