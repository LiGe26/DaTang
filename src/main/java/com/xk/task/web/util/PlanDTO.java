package com.xk.task.web.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//任务数据传输
public class PlanDTO {
    private int search_loginId;
    private String search_Plan_Name; //计划名
    private int search_Task_Id;//所属任务编号
    //查询的开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date search_Begin_Date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date search_Begin_Date2;
    //查询的结束日期范围
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date search_End_Date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date search_End_Date2;
    //查询反馈状态
    private String search_Is_Feedback;

    public PlanDTO() {
    }

    public PlanDTO(int search_loginId, String search_Plan_Name, int search_Task_Id, Date search_Begin_Date1, Date search_Begin_Date2, Date search_End_Date1, Date search_End_Date2, String search_Is_Feedback) {
        this.search_loginId = search_loginId;
        this.search_Plan_Name = search_Plan_Name;
        this.search_Task_Id = search_Task_Id;
        this.search_Begin_Date1 = search_Begin_Date1;
        this.search_Begin_Date2 = search_Begin_Date2;
        this.search_End_Date1 = search_End_Date1;
        this.search_End_Date2 = search_End_Date2;
        this.search_Is_Feedback = search_Is_Feedback;
    }

    @Override
    public String toString() {
        return "PlanDTO{" +
                "search_loginId=" + search_loginId +
                ", search_Plan_Name='" + search_Plan_Name + '\'' +
                ", search_Task_Id=" + search_Task_Id +
                ", search_Begin_Date1=" + search_Begin_Date1 +
                ", search_Begin_Date2=" + search_Begin_Date2 +
                ", search_End_Date1=" + search_End_Date1 +
                ", search_End_Date2=" + search_End_Date2 +
                ", search_Is_Feedback='" + search_Is_Feedback + '\'' +
                '}';
    }

    public int getSearch_loginId() {
        return search_loginId;
    }

    public void setSearch_loginId(int search_loginId) {
        this.search_loginId = search_loginId;
    }

    public String getSearch_Plan_Name() {
        return search_Plan_Name;
    }

    public void setSearch_Plan_Name(String search_Plan_Name) {
        this.search_Plan_Name = search_Plan_Name;
    }

    public int getSearch_Task_Id() {
        return search_Task_Id;
    }

    public void setSearch_Task_Id(int search_Task_Id) {
        this.search_Task_Id = search_Task_Id;
    }

    public Date getSearch_Begin_Date1() {
        return search_Begin_Date1;
    }

    public void setSearch_Begin_Date1(Date search_Begin_Date1) {
        this.search_Begin_Date1 = search_Begin_Date1;
    }

    public Date getSearch_Begin_Date2() {
        return search_Begin_Date2;
    }

    public void setSearch_Begin_Date2(Date search_Begin_Date2) {
        this.search_Begin_Date2 = search_Begin_Date2;
    }

    public Date getSearch_End_Date1() {
        return search_End_Date1;
    }

    public void setSearch_End_Date1(Date search_End_Date1) {
        this.search_End_Date1 = search_End_Date1;
    }

    public Date getSearch_End_Date2() {
        return search_End_Date2;
    }

    public void setSearch_End_Date2(Date search_End_Date2) {
        this.search_End_Date2 = search_End_Date2;
    }

    public String getSearch_Is_Feedback() {
        return search_Is_Feedback;
    }

    public void setSearch_Is_Feedback(String search_Is_Feedback) {
        this.search_Is_Feedback = search_Is_Feedback;
    }
}
