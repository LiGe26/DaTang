package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITEmployeeDAO;
import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TRole;
import com.xk.task.data.util.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("empDao")
public class TEmployeeDAOImpl implements ITEmployeeDAO {
    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());

    //映射实体类
    RowMapper rowMapper=new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TEmployee(rs.getInt("employee_Id"),rs.getString("employee_Name"),
                    rs.getString("password"),rs.getString("reaL_Name"),rs.getString("sex"),
                    rs.getTimestamp("birthday"),rs.getString("duty"),rs.getTimestamp("enrolldate"),
                    rs.getString("education"),rs.getString("major"),rs.getString("experience"),
                    new TRole(rs.getInt("ROLE_ID")),new TEmployee(rs.getInt("PARENT_ID")));
        }
    };


    @Override
    public TEmployee login(TEmployee employee) {
        //and status=1  //用户在职状态
        String sql="select * from T_EMPLOYEE where EMPLOYEE_NAME=? and PASSWORD=? and workState=1";
        try {
            return (TEmployee) template.queryForObject(sql,new Object[]{employee.getEmployee_Name(),
                    employee.getPassword()},rowMapper);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("未查询到员工");
            return null;
        }
    }

    @Override
    public List<TEmployee> queryAllEmployees(String sql,Object [] params) {
        return template.query(sql,params,rowMapper);
    }

    @Override
    public int insertEmployee(TEmployee emp) {
        String sql="insert into T_EMPLOYEE values(0,?,?,?,?,?,?,?,?,?,?,?,?,1)";

        return template.update(sql,new Object[]{emp.getEmployee_Name(),emp.getPassword(),
        emp.getReaL_Name(),emp.getSex(),emp.getBirthday(),emp.getDuty(),emp.getEnrolldate(),
        emp.getEducation(),emp.getMajor(),emp.getExperience(),emp.getRole().getRole_Id(),null});
    }

    @Override
    public int deleteEmployee(int id) {

//        String sql3="delete T_EMPLOYEE where employee_Id =?";
        //修改员工的状态为离职状态
        String sql="update T_EMPLOYEE set WORKSTATE=0 where EMPLOYEE_ID=?"; //根据编号修改员工离职状态
        return template.update(sql,new Object[]{id});
    }



    @Override
    public int updateEmployeeParent(int empId, Integer parent_id) {
        String sql="update T_EMPLOYEE set parent_id=? where employee_Id=?";
        return template.update(sql,new Object[]{parent_id,empId});
    }

    @Override
    public int queryForInt(String sql, Object[] params) {
        return template.queryForInt(sql,params);
    }

}
