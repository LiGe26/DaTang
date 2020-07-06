package com.xk.task.data.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TTask {
    /**
     * TASK_ID         NUMBER primary key
     * TASK_NAME       VARCHAR2(50),		--任务名称
     *     BEGIN_DATE      DATE,			--开始时间
     *     END_DATE        DATE,			--结束时间
     *     REAL_BEGIN_DATE DATE,			--实际开始时间
     *     REAL_END_DATE   DATE,			--实际结束时间
     *     STATUS          VARCHAR2(10),		--任务状态
     *     IMPLEMENTOR_ID  NUMBER references T_EMPLOYEE(EMPLOYEE_ID),	--外键，实施人编号，引用员工编号
     *     ASSIGNER_ID     NUMBER references T_EMPLOYEE(EMPLOYEE_ID),		--外键，分配人编号，引用员工编号
     *     TASK_DESC       VARCHAR2(100)		--任务描述
     */
    private int task_Id; //任务编号
    private String task_Name; //任务划名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin_Date;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_Date; //结束时间
    private Date real_Begin_Date;//实际开始时间
    private Date real_End_Date;//实际结束时间
    private String status;//任务状态
    private TEmployee implementor;//实施人
    private TEmployee assigner;//分配人
    private String task_Desc;

    public TTask(int task_Id, String task_Name, Date begin_Date,
                 Date end_Date, Date real_Begin_Date, Date real_End_Date,
                 String status, TEmployee implementor, TEmployee assigner, String task_Desc) {
        this.task_Id = task_Id;
        this.task_Name = task_Name;
        this.begin_Date = begin_Date;
        this.end_Date = end_Date;
        this.real_Begin_Date = real_Begin_Date;
        this.real_End_Date = real_End_Date;
        this.status = status;
        this.implementor = implementor;
        this.assigner = assigner;
        this.task_Desc = task_Desc;
    }

    public TTask() {
    }

    @Override
    public String toString() {
        return "TTask{" +
                "task_Id=" + task_Id +
                ", task_Name='" + task_Name + '\'' +
                ", begin_Date=" + begin_Date +
                ", end_Date=" + end_Date +
                ", real_Begin_Date=" + real_Begin_Date +
                ", real_End_Date=" + real_End_Date +
                ", status='" + status + '\'' +
                ", implementor=" + implementor +
                ", assigner=" + assigner +
                ", task_Desc='" + task_Desc + '\'' +
                '}';
    }

    public int getTask_Id() {
        return task_Id;
    }

    public void setTask_Id(int task_Id) {
        this.task_Id = task_Id;
    }

    public String getTask_Name() {
        return task_Name;
    }

    public void setTask_Name(String task_Name) {
        this.task_Name = task_Name;
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

    public Date getReal_Begin_Date() {
        return real_Begin_Date;
    }

    public void setReal_Begin_Date(Date real_Begin_Date) {
        this.real_Begin_Date = real_Begin_Date;
    }

    public Date getReal_End_Date() {
        return real_End_Date;
    }

    public void setReal_End_Date(Date real_End_Date) {
        this.real_End_Date = real_End_Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TEmployee getImplementor() {
        return implementor;
    }

    public void setImplementor(TEmployee implementor) {
        this.implementor = implementor;
    }

    public TEmployee getAssigner() {
        return assigner;
    }

    public void setAssigner(TEmployee assigner) {
        this.assigner = assigner;
    }

    public String getTask_Desc() {
        return task_Desc;
    }

    public void setTask_Desc(String task_Desc) {
        this.task_Desc = task_Desc;
    }
}
