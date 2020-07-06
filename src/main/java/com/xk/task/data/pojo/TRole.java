package com.xk.task.data.pojo;

public class TRole {
    private int role_Id;       //角色编号
    private String role_Name;  //角色名称
    private String role_Desc;  //角色描述

    public TRole(int role_id, TRole parent) {
    }


    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int role_Id) {
        this.role_Id = role_Id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public String getRole_Desc() {
        return role_Desc;
    }

    public void setRole_Desc(String role_Desc) {
        this.role_Desc = role_Desc;
    }

    @Override
    public String toString() {
        return "TRole{" +
                "role_Id=" + role_Id +
                ", role_Name='" + role_Name + '\'' +
                ", role_Desc='" + role_Desc + '\'' +
                '}';
    }

    public TRole() {
    }

    public TRole(int role_Id) {
        this.role_Id = role_Id;
    }

    public TRole(int role_Id, String role_Name, String role_Desc) {
        this.role_Id = role_Id;
        this.role_Name = role_Name;
        this.role_Desc = role_Desc;
    }


}
