package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.pojo.TRole;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Repository("roleDao")
public class TRoleDAOImpl extends SqlSessionDaoSupport implements ITRoleDAO {
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init(){
        setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public TRole queryRoleById(int id) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITRoleDAO dao= sqlSession.getMapper(ITRoleDAO.class);

        TRole role=dao.queryRoleById(id);
        sqlSession.close();
        return role;
    }

    @Override
    public List<TRole> queryAllRoles() {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ITRoleDAO dao= sqlSession.getMapper(ITRoleDAO.class);

        List<TRole> roles=dao.queryAllRoles();
        sqlSession.close();
        return roles;
    }
}
