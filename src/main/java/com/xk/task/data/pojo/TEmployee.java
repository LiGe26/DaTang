package com.xk.task.data.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TEmployee {
    private int employee_Id;
    private String employee_Name;
    private String password;
    private String reaL_Name; //真实姓名
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String duty; //职业信息
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrolldate;//入职时间
    private String education; //学历信息
    private String major; //专业信息
    private String experience; //行业经验
    private TRole role; //角色
    private TEmployee parent; //上司

    public TEmployee() {
    }
    public TEmployee(int employee_Id) {
        this.employee_Id=employee_Id;
    }

    public TEmployee(String employee_Name, String password) {
        this.employee_Name = employee_Name;
        this.password = password;
    }

    public TEmployee(int employee_Id, String reaL_Name) {
        this.employee_Id = employee_Id;
        this.reaL_Name = reaL_Name;
    }

    @Override
    public String toString() {
        return "TEmployee{" +
                "employee_Id=" + employee_Id +
                ", employee_Name='" + employee_Name + '\'' +
                ", password='" + password + '\'' +
                ", reaL_Name='" + reaL_Name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", duty='" + duty + '\'' +
                ", enrolldate=" + enrolldate +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", experience='" + experience + '\'' +
                ", role=" + role +
                ", parent=" + parent +
                '}';
    }

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getEmployee_Name() {
        return employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReaL_Name() {
        return reaL_Name;
    }

    public void setReaL_Name(String reaL_Name) {
        this.reaL_Name = reaL_Name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if(sex.equals("m")){
            this.sex = "男";
        }else{
            this.sex = "女";
        }


    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Date getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(Date enrolldate) {
        this.enrolldate = enrolldate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public TRole getRole() {

        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }

    public TEmployee getParent() {

        return parent;
    }

    public void setParent(TEmployee parent) {
        this.parent = parent;
    }

    public TEmployee(int employee_Id, String employee_Name, String password, String reaL_Name, String sex, Date birthday, String duty, Date enrolldate, String education, String major, String experience, TRole role, TEmployee parent) {
        this.employee_Id = employee_Id;
        this.employee_Name = employee_Name;
        this.password = password;
        this.reaL_Name = reaL_Name;
        this.sex = sex;
        this.birthday = birthday;
        this.duty = duty;
        this.enrolldate = enrolldate;
        this.education = education;
        this.major = major;
        this.experience = experience;
        this.role = role;
        this.parent = parent;
    }
}
