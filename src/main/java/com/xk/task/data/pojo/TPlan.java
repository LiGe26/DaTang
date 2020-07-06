package com.xk.task.data.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TPlan {
    /**
     *   PLAN_ID       NUMBER primary key ,		--计划编号，主键序列
     *     PLAN_NAME     VARCHAR2(50),	--计划名称
     *     STATUS        VARCHAR2(10),		--计划状态
     *     IS_FEEDBACK   VARCHAR2(5),	--是否反馈
     *     BEGIN_DATE    DATE,			--开始时间
     *     END_DATE      DATE,			--结束时间
     *     TASK_ID       NUMBER references T_TASK(TASK_ID) ,	--外键，所属任务，引用任务编号
     *     FEEDBACK_INFO VARCHAR2(100),	--反馈信息
     *     PLAN_DESC     VARCHAR2(100)		--计划描述
     */
    private int plan_Id;
    private String plan_Name;
    private String status;
    private String is_Feedback;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin_Date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_Date;
    private int task_Id;
    private String feedback_Info;
    private String plan_Desc;

    public TPlan(int plan_Id, String plan_Name, String status, String is_Feedback, Date begin_Date, Date end_Date, int task_Id, String feedback_Info, String plan_Desc) {
        this.plan_Id = plan_Id;
        this.plan_Name = plan_Name;
        this.status = status;
        this.is_Feedback = is_Feedback;
        this.begin_Date = begin_Date;
        this.end_Date = end_Date;
        this.task_Id = task_Id;
        this.feedback_Info = feedback_Info;
        this.plan_Desc = plan_Desc;
    }

    public TPlan() {
    }

    @Override
    public String toString() {
        return "TPlan{" +
                "plan_Id=" + plan_Id +
                ", plan_Name='" + plan_Name + '\'' +
                ", status='" + status + '\'' +
                ", is_Feedback='" + is_Feedback + '\'' +
                ", begin_Date=" + begin_Date +
                ", end_Date=" + end_Date +
                ", task_Id=" + task_Id +
                ", feedback_Info='" + feedback_Info + '\'' +
                ", plan_Desc='" + plan_Desc + '\'' +
                '}';
    }

    public int getPlan_Id() {
        return plan_Id;
    }

    public void setPlan_Id(int plan_Id) {
        this.plan_Id = plan_Id;
    }

    public String getPlan_Name() {
        return plan_Name;
    }

    public void setPlan_Name(String plan_Name) {
        this.plan_Name = plan_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        switch (status){
            case "0":
                this.status="未实施";
                break;
            case "1":
                this.status="实施中";
                break;
            case "2":
                this.status="已完成";
                break;
            default:
                this.status="未实施";
                break;
        }
    }

    public String getIs_Feedback() {
        return is_Feedback;
    }

    public void setIs_Feedback(String is_Feedback) {
        if(is_Feedback.equals("no")){
            this.is_Feedback = "否";
        }else{
            this.is_Feedback = "是";
        }

    }

    public Date getBegin_Date() {
        return begin_Date;
    }

    public void setBegin_Date(Date begin_Date) {
        this.begin_Date = begin_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }

    public int getTask_Id() {
        return task_Id;
    }

    public void setTask_Id(int task_Id) {
        this.task_Id = task_Id;
    }

    public String getFeedback_Info() {
        return feedback_Info;
    }

    public void setFeedback_Info(String feedback_Info) {
        this.feedback_Info = feedback_Info;
    }

    public String getPlan_Desc() {
        return plan_Desc;
    }

    public void setPlan_Desc(String plan_Desc) {
        this.plan_Desc = plan_Desc;
    }
}
