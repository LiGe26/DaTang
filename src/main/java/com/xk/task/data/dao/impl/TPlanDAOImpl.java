package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.util.DBUtil;
import com.xk.task.web.util.PlanDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("planDao")
public class TPlanDAOImpl extends SqlSessionDaoSupport implements ITPlanDAO {

    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init(){
        setSqlSessionFactory(sqlSessionFactory);
    }

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
    public List<TPlan> queryPlanByTaskId(int taskId) {
        List<TPlan> allPlan=null;
        try {
            SqlSession session=sqlSessionFactory.openSession();
            ITPlanDAO dao=session.getMapper(ITPlanDAO.class);
            allPlan=dao.queryPlanByTaskId(taskId);
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allPlan;
    }

    @Override
    public int addPlan(TPlan plan) {
        int count=0;
        try {
            SqlSession session=sqlSessionFactory.openSession();
            ITPlanDAO dao=session.getMapper(ITPlanDAO.class);
            count=dao.addPlan(plan);
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public TPlan queryPlanById(int id) {
        TPlan plan=null;
        try {
            SqlSession session=sqlSessionFactory.openSession();
            ITPlanDAO dao=session.getMapper(ITPlanDAO.class);
            plan=dao.queryPlanById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return plan;
    }

    @Override
    public int deletePlan(Integer[] objs) {
        return 0;
    }

    @Override
    public int updatePlanById(TPlan plan) {
        int count=0;
        try {
            SqlSession session=sqlSessionFactory.openSession();
            ITPlanDAO dao=session.getMapper(ITPlanDAO.class);
            count=dao.updatePlanById(plan);
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<TPlan> queryPlanList(String sql, Object[] params) {
        return template.query(sql,params,rowMapper);
    }

    @Override
    public int queryPlanForInt(String sql, Object[] params) {
        return template.queryForInt(sql,params);
    }

}
