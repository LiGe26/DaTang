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

    /**
     * 员工执行映射--》分配人主管
     */
//    RowMapper rowMapper=new RowMapper() {
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new TTask(rs.getInt("TASK_ID"),rs.getString("TASK_NAME"),
//                    rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("END_DATE"),
//                    rs.getTimestamp("REAL_BEGIN_DATE"),rs.getTimestamp("REAL_END_DATE"),
//                    rs.getString("STATUS"),new TEmployee(rs.getInt("IMPLEMENTOR_ID")),
//                    new TEmployee(rs.getInt("ASSIGNER_ID"),rs.getString("reaL_Name")),rs.getString("TASK_DESC"));
//        }
//    };
//    /**
//     * 主管执行是映射--》实施人（接受任务的）员工
//     */
//    RowMapper rowMapperImplementor=new RowMapper() {
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new TTask(rs.getInt("TASK_ID"),rs.getString("TASK_NAME"),
//                    rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("END_DATE"),
//                    rs.getTimestamp("REAL_BEGIN_DATE"),rs.getTimestamp("REAL_END_DATE"),
//                    rs.getString("STATUS"),new TEmployee(rs.getInt("IMPLEMENTOR_ID"),rs.getString("reaL_Name")),
//                    new TEmployee(rs.getInt("ASSIGNER_ID")),rs.getString("TASK_DESC"));
//        }
//    };


//    RowMapper rowMapper=new RowMapper() {
        //        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new TTask(rs.getInt("TASK_ID"),rs.getString("TASK_NAME"),
//                    rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("END_DATE"),
//                    rs.getTimestamp("REAL_BEGIN_DATE"),rs.getTimestamp("REAL_END_DATE"),
//                    rs.getString("STATUS"),new TEmployee(rs.getInt("IMPLEMENTOR_ID")),
//                    new TEmployee(rs.getInt("ASSIGNER_ID"),rs.getString("reaL_Name")),rs.getString("TASK_DESC"));
//        }
    @Select("select * from T_TASK t inner join T_EMPLOYEE TE on t.ASSIGNER_ID = TE.EMPLOYEE_ID  where IMPLEMENTOR_ID=#{id}")
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









    public List<TTask> queryTaskListAndAssigner(String sql,Object [] params);

    public List<TTask> queryTaskListAndImplementor(String sql, Object[] params);

    public int updateTask(String sql,Object [] params);

    public int getTotalTaskRecordCount(String sql,Object [] params);
}
