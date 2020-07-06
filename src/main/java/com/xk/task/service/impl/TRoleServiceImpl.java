package com.xk.task.service.impl;

import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.pojo.TRole;
import com.xk.task.service.ITRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class TRoleServiceImpl implements ITRoleService {
    @Resource(name = "roleDao")
    private ITRoleDAO dao;
    @Override
    public TRole queryRoleById(int id) {
        return dao.queryRoleById(id);
    }

    @Override
    public List<TRole> queryAllRoles() {
        return dao.queryAllRoles();
    }
}
