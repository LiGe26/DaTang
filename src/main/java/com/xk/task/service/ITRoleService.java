package com.xk.task.service;

import com.xk.task.data.pojo.TRole;

import java.util.List;

public interface ITRoleService {

    public TRole queryRoleById(int id);


    public List<TRole> queryAllRoles();
}
