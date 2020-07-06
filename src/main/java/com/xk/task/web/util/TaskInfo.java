package com.xk.task.web.util;

import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.pojo.TTask;

import java.util.List;

/**
 * 任务信息
 */
public class TaskInfo {

    private TTask task; //任务
    private List<TPlan> plans;

    public TaskInfo() {
    }

    public TaskInfo(TTask task, List<TPlan> plans) {
        this.task = task;
        this.plans = plans;
    }

    public TTask getTask() {
        return task;
    }

    public void setTask(TTask task) {
        this.task = task;
    }

    public List<TPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<TPlan> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "task=" + task +
                ", plans=" + plans +
                '}';
    }
}
