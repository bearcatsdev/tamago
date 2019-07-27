package com.bearcats.tamagoparent.recyclerview;

public class ChildrenModel {
    private String name;
    private int goal_start;
    private int goal_end;
    private int wallet;
    private int saving;
    private int egg;
    private int avatar_type;

    public ChildrenModel(String name, int goal_start, int goal_end, int wallet, int saving, int egg, int avatar_type) {
        this.name = name;
        this.goal_start = goal_start;
        this.goal_end = goal_end;
        this.wallet = wallet;
        this.saving = saving;
        this.egg = egg;
        this.avatar_type = avatar_type;
    }

    public int getAvatar_type() {
        return avatar_type;
    }

    public void setAvatar_type(int avatar_type) {
        this.avatar_type = avatar_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoal_start() {
        return goal_start;
    }

    public void setGoal_start(int goal_start) {
        this.goal_start = goal_start;
    }

    public int getGoal_end() {
        return goal_end;
    }

    public void setGoal_end(int goal_end) {
        this.goal_end = goal_end;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }
}
