package com.bearcats.tamago.Recycler;

public class Missions_Model {
    private String taskName, taskTime;
    private int parent_type,task_type,egg,money;

    public Missions_Model(int parent_type, int task_type, int egg, int money, String taskName, String taskTime){
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.parent_type = parent_type;
        this.egg = egg;
        this.money = money;
        this.task_type = task_type;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public int getParent_type() {
        return parent_type;
    }

    public void setParent_type(int parent_type) {
        this.parent_type = parent_type;
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }
}
