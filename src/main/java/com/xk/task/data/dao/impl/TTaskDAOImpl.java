package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.dao.ITTaskDAO;
import com.xk.task.data.pojo.TEmployee;

import com.xk.task.data.pojo.TRole;
import com.xk.task.data.pojo.TTask;
import com.xk.task.data.util.DBUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("taskDao")
public class TTaskDAOImpl extends SqlSessionDaoSupport implements ITTaskDAO {

    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init(){
        setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public List<TTask> queryTaskListAndAssigner(int empId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);

        List<TTask>  tasks=dao.queryTaskListAndAssigner(empId);
        sqlSession.close();
        return tasks;
    }

    @Override
    public List<TTask> queryTaskListAndAssignerByPaging(int empId, int start, int end) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        List<TTask>  tasks=dao.queryTaskListAndAssignerByPaging(empId,start,end);
        sqlSession.close();
        return tasks;
    }

    @Override
    public int queryTaskListAndAssignerTotalCount(int empId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int  count=dao.queryTaskListAndAssignerTotalCount(empId);
        sqlSession.close();
        return count;
    }

    @Override
    public int updateTaskStatusBegin(int taskId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int  count=dao.updateTaskStatusBegin(taskId);
        sqlSession.close();
        return count;
    }

    @Override
    public int addTask(TTask task) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int  count=dao.addTask(task);
        sqlSession.close();
        return count;
    }


    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());
    /**
     * 员工执行映射--》分配人主管
     */
    RowMapper rowMapper=new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TTask(rs.getInt("TASK_ID"),rs.getString("TASK_NAME"),
                    rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("END_DATE"),
                    rs.getTimestamp("REAL_BEGIN_DATE"),rs.getTimestamp("REAL_END_DATE"),
                    rs.getString("STATUS"),new TEmployee(rs.getInt("IMPLEMENTOR_ID")),
                    new TEmployee(rs.getInt("ASSIGNER_ID"),rs.getString("reaL_Name")),rs.getString("TASK_DESC"));
        }
    };
    /**
     * 主管执行是映射--》实施人（接受任务的）员工
     */
    RowMapper rowMapperImplementor=new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TTask(rs.getInt("TASK_ID"),rs.getString("TASK_NAME"),
                    rs.getTimestamp("BEGIN_DATE"),rs.getTimestamp("END_DATE"),
                    rs.getTimestamp("REAL_BEGIN_DATE"),rs.getTimestamp("REAL_END_DATE"),
                    rs.getString("STATUS"),new TEmployee(rs.getInt("IMPLEMENTOR_ID"),rs.getString("reaL_Name")),
                    new TEmployee(rs.getInt("ASSIGNER_ID")),rs.getString("TASK_DESC"));
        }
    };



    @Override
    public List<TTask> queryTaskListAndAssigner(String sql, Object[] params) {
        try {
            return template.query(sql,params,rowMapper);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("未查询到任务");
            return null;
        }
    }


    @Override
    public List<TTask> queryTaskListAndImplementor(String sql, Object[] params) {
        try {
            return template.query(sql,params,rowMapperImplementor);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("未查询到任务");
            return null;

        }

    }



    @Override
    public int updateTask(String sql, Object[] params) {
        return template.update(sql,params);
    }

    @Override
    public int getTotalTaskRecordCount(String sql, Object[] params) {
        return template.queryForInt(sql,params);
    }
}
