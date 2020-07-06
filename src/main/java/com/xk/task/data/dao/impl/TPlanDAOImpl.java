package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.util.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component("planDao")
public class TPlanDAOImpl implements ITPlanDAO {

    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());

    RowMapper rowMapper=new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return new TPlan(rs.getInt("PLAN_ID"),rs.getString("plan_Name"),
                        rs.getString("status"),rs.getString("IS_FEEDBACK"),
                        rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("end_DATE"),
                        rs.getInt("TASK_ID"),
                        rs.getString("FEEDBACK_INFO"),rs.getString("PLAN_DESC"));
            }catch (Exception e){
                e.printStackTrace();
            }
            return  null;
        }
    };
    @Override
    public List<TPlan> queryPlanList(String sql,Object [] params) {
        return template.query(sql,params,rowMapper);
    }

    @Override
    public int updatePlan(String sql, Object[] params) {
        return template.update(sql,params);
    }

    @Override
    public int deletePlansById(String sql, Object[] params) {
        return template.update(sql,params);
    }

    @Override
    public int queryPlanForInt(String sql, Object[] params) {
        return template.queryForInt(sql,params);
    }


}
