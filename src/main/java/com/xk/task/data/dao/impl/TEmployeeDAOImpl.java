package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITEmployeeDAO;
import com.xk.task.data.dao.ITTaskDAO;
import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TRole;
import com.xk.task.data.util.DBUtil;
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
import java.util.List;

@Repository("empDao")
public class TEmployeeDAOImpl extends SqlSessionDaoSupport implements ITEmployeeDAO {


    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        setSqlSessionFactory(sqlSessionFactory);
    }

    //映射实体类
    RowMapper rowMapper = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TEmployee(rs.getInt("employee_Id"), rs.getString("employee_Name"),
                    rs.getString("password"), rs.getString("reaL_Name"), rs.getString("sex"),
                    rs.getTimestamp("birthday"), rs.getString("duty"), rs.getTimestamp("enrolldate"),
                    rs.getString("education"), rs.getString("major"), rs.getString("experience"),
                    new TRole(rs.getInt("ROLE_ID")), new TEmployee(rs.getInt("PARENT_ID")));
        }
    };


    @Override
    public TEmployee login(TEmployee employee) {
        //and status=1  //用户在职状态
        TEmployee tEmployee = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            tEmployee = dao.login(employee);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("未查询到员工");
            return null;
        }
        return tEmployee;
    }

    @Override
    public List<TEmployee> getAllEmployess() {
        List allEmployee = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allEmployee = dao.getAllEmployess();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmployee;
    }

    @Override
    public List<TEmployee> getDents(int id) {
        List allEmployee = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allEmployee = dao.getDents(id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmployee;
    }

    @Override
    public List<TEmployee> queryEmployeeById(int id) {
        List<TEmployee> employee = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            employee = dao.queryEmployeeById(id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<TEmployee> queryPersonsByManagerId(int mangerId) {
        List allEmployee = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allEmployee = dao.queryPersonsByManagerId(mangerId);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmployee;
    }

    @Override
    public int queryPersonsByManagerIdTotalRecords(int managerId) {
        System.out.println("执行添加的DAO");
        SqlSession session = super.getSqlSession();
        ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
        int num = dao.queryPersonsByManagerIdTotalRecords(managerId);
        return num;
    }

    @Override
    public List<TEmployee> queryPersonsByManagerIdByPaging(int mangerId, int start, int end) {
        List<TEmployee> allEmp = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allEmp = dao.queryPersonsByManagerIdByPaging(mangerId, start, end);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmp;
    }

    @Override
    public List<TEmployee> getAllEmployessByPaging(int start, int end) {
        List<TEmployee> allEmp = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allEmp = dao.getAllEmployessByPaging(start, end);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmp;
    }

    @Override
    public int queryTotalEmployeeRecords() {
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            count = dao.queryTotalEmployeeRecords();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<TEmployee> getAllPersonByPaging(int start, int end) {
        List<TEmployee> allPerson = null;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            allPerson = dao.getAllPersonByPaging(start, end);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPerson;
    }

    @Override
    public int queryTotalPersonRecords() {
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            count = dao.queryTotalPersonRecords();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public int insertEmployee(TEmployee emp) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITEmployeeDAO dao= sqlSession.getMapper(ITEmployeeDAO.class);
        int  count=dao.insertEmployee(emp);
        sqlSession.close();
        return count;
    }

    @Override
    public int deleteEmployee(int id) {

//        String sql3="delete T_EMPLOYEE where employee_Id =?";
        //修改员工的状态为离职状态
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            count = dao.deleteEmployee(id);
            System.out.println("返回删除的条数" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public int updateEmployeeParent(int empId, Integer parent_id) {
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            ITEmployeeDAO dao = session.getMapper(ITEmployeeDAO.class);
            count = dao.updateEmployeeParent(empId, parent_id);
            System.out.println("返回删除的条数" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
