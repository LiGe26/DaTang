package com.xk.task.web.util;

import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TRole;

import java.util.List;

//表单工具类
public class LoginFormLoad {
    //员工
    private TEmployee employee;
    private TRole roles; //角色

    public TEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(TEmployee employee) {
        this.employee = employee;
    }

    public TRole getRoles() {
        return roles;
    }

    public void setRoles(TRole roles) {
        this.roles = roles;
    }

    public LoginFormLoad() {
    }

    public LoginFormLoad(TEmployee employee, TRole roles) {
        this.employee = employee;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LoginFormLoad{" +
                "employee=" + employee +
                ", roles=" + roles +
                '}';
    }
}
