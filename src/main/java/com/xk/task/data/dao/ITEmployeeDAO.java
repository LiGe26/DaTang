package com.xk.task.data.dao;

import com.xk.task.data.pojo.TEmployee;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


public interface ITEmployeeDAO {
    /**
     * 员工登录的方法
     * 验证用户名和密码
     * @return
     */
    @Select("select * from T_EMPLOYEE where EMPLOYEE_NAME=#{employee_Name} and PASSWORD=#{password} and workState=1")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @Results(id = "empMapper",value = {
            @Result(id = true, property="employee_Id", column="EMPLOYEE_ID" ,javaType = int.class),
            @Result(property="employee_Name",column="EMPLOYEE_NAME",javaType = String.class),
            @Result(property="password", column="PASSWORD" ,javaType = String.class),
            @Result(property="reaL_Name", column="REAL_NAME" ,javaType = String.class),
            @Result(property="sex", column="SEX",javaType = String.class),
            @Result(property="birthday", column="BIRTHDAY" ,javaType = Date.class),
            @Result(property="duty", column="DUTY" ,javaType = String.class),
            @Result(property="enrolldate",column="ENROLLDATE",javaType = Date.class),
            @Result(property="education", column="EDUCATION" ,javaType = String.class),
            @Result(property="major", column="MAJOR" ,javaType = String.class),
            @Result(property="experience", column="EXPERIENCE",javaType = String.class),
            @Result(property="role.role_Id", column="ROLE_ID" ,javaType = int.class),
            @Result(property="parent.employee_Id", column="PARENT_ID" ,javaType = int.class)
    })
    public TEmployee login(TEmployee employee);

    //查询所有
    @Select("select * from T_EMPLOYEE")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> getAllEmployess();



    @Select("select * from T_EMPLOYEE where role_id=#{id} and WORKSTATE=1")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> getDents(int id);

    @Select("select * from T_EMPLOYEE where employee_Id=#{id}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> queryEmployeeById(int id);

    @Select("select * from T_EMPLOYEE where PARENT_ID=#{mangerId}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> queryPersonsByManagerId(int mangerId);

    @Select("select count(*) from T_EMPLOYEE where PARENT_ID=#{managerId} and WORKSTATE=1")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultType(int.class)
    public int queryPersonsByManagerIdTotalRecords(int managerId);

    @Select("select * from (select ROWNUM rm,t.* from T_EMPLOYEE t where PARENT_ID=#{mangerId} and WORKSTATE=1 ) temp where temp.rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> queryPersonsByManagerIdByPaging(@Param("mangerId")int mangerId, @Param("start")int start, @Param("end")int end);

    @Select("select * from (select rownum rm,te.* from T_EMPLOYEE te where ROLE_ID!=1 and WORKSTATE=1 order by te.EMPLOYEE_ID desc)temp where temp.rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> getAllEmployessByPaging(@Param("start") int start, @Param("end") int end);

    @Select("select count(*) from T_EMPLOYEE where ROLE_ID!=1 and WORKSTATE=1")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    public int queryTotalEmployeeRecords();

    @Select("select * from (select rownum rm,te.* from T_EMPLOYEE te where ROLE_ID=3 and WORKSTATE=1 order by te.EMPLOYEE_ID desc)temp where temp.rm between #{start} and #{end}")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    @ResultMap(value = "empMapper")
    public List<TEmployee> getAllPersonByPaging(@Param("start")int start,@Param("end")int end);

    @Select("select count(*) from T_EMPLOYEE  where ROLE_ID=3 and WORKSTATE=1")
    @Options(flushCache = Options.FlushCachePolicy.FALSE, timeout = 10000,useCache=true)
    public int queryTotalPersonRecords();

    @Insert("insert into T_EMPLOYEE values(0,#{employee_Name},#{password},#{reaL_Name},#{sex},#{birthday},#{duty},#{enrolldate},#{education},#{major},#{experience},#{role.role_Id},null,1)")
    @ResultType(int.class)
    public int insertEmployee(TEmployee employee);

    @Update("update T_EMPLOYEE set WORKSTATE=0 where EMPLOYEE_ID=#{employee_Id}")
    public int deleteEmployee(@Param("employee_Id") int id);


    /**
     * 修改员工上级
     * @param empId 员工编号
     * @param parent_id 修改的上级编号
     * @return 受影响行数
     */
    @Update("update T_EMPLOYEE set parent_id=#{empId} where employee_Id=#{parent_id}")
    public int updateEmployeeParent(@Param("empId") int empId,@Param("parent_id") Integer parent_id);


}
