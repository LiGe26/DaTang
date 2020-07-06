package com.xk.task.data.dao.impl;

import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.pojo.TRole;
import com.xk.task.data.util.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("roleDao")
public class TRoleDAOImpl implements ITRoleDAO {
    private JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());


    @Override
    public TRole queryRoleById(int id) {
        String sql="select * from T_ROLE where ROLE_ID=?";
        return template.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<TRole>(TRole.class));
    }

    @Override
    public List<TRole> queryAllRoles() {
        String sql="select * from T_ROLE";

        return template.query(sql,new BeanPropertyRowMapper<TRole>(TRole.class));
    }
}
