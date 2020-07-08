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

    @Override
    public List<TTask> queryTaskListAndImplementor(int empId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        List<TTask>  tasks=dao.queryTaskListAndImplementor(empId);
        sqlSession.close();
        return tasks;
    }

    @Override
    public List<TTask> queryTaskListAndImplementorByPaging(int empId, int start, int end) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        List<TTask>  tasks=dao.queryTaskListAndImplementorByPaging(empId,start,end);
        sqlSession.close();
        return tasks;
    }

    @Override
    public List<TTask> queryTaskUnDoneByManagerIdByPaging(int managerId, int start, int end) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        List<TTask>  tasks=dao.queryTaskUnDoneByManagerIdByPaging(managerId,start,end);
        sqlSession.close();
        return tasks;
    }

    @Override
    public List<TTask> queryTaskDoneByManagerIdByPaging(int managerId, int start, int end) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        List<TTask>  tasks=dao.queryTaskDoneByManagerIdByPaging(managerId,start,end);
        sqlSession.close();
        return tasks;
    }

    @Override
    public int queryTaskUnDoneByManagerTotalRecords(int managerId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int  count=dao.queryTaskUnDoneByManagerTotalRecords(managerId);
        sqlSession.close();
        return count;
    }

    @Override
    public int queryTaskDoneByManagerTotalRecords(int managerId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int  count=dao.queryTaskDoneByManagerTotalRecords(managerId);
        sqlSession.close();
        return count;
    }

    @Override
    public TTask queryTaskAndImplementorByTaskId(int taskId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        TTask  task=dao.queryTaskAndImplementorByTaskId(taskId);
        sqlSession.close();
        return task;
    }

    @Override
    public int queryTaskListAndImplementorTotalCount(int managerId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int count=dao.queryTaskListAndImplementorTotalCount(managerId);
        sqlSession.close();
        return count;
    }

    @Override
    public int updateTaskBytaskId(TTask task) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int count=dao.updateTaskBytaskId(task);
        sqlSession.close();
        return count;
    }

    @Override
    public int deleteTaskByIds(Integer[] deleteIds) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int count=dao.deleteTaskByIds(deleteIds);
        sqlSession.close();
        return count;
    }

    @Override
    public int finishTask(int taskId) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITTaskDAO dao= sqlSession.getMapper(ITTaskDAO.class);
        int count=dao.finishTask(taskId);
        sqlSession.close();
        return count;
    }



}
