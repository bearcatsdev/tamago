package com.bearcats.tamago;

public class Task_Model{
    private String taskName, taskReward, taskTime,taskSource;

    public Task_Model(String taskName, String taskReward, String taskTime, String taskSource){
        this.taskName = taskName;
        this.taskReward = taskReward;
        this.taskTime = taskTime;
        this.taskSource = taskSource;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskReward() {
        return taskReward;
    }

    public void setTaskReward(String taskReward) {
        this.taskReward = taskReward;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }
}
