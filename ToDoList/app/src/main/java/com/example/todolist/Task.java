package com.example.todolist;

/**
 * Created by chenxin on 5/31/17.
 */

public class Task {
    private boolean isChecked;
    private int taskId;
    private String title;
    private String subTitle;

    public Task(boolean isChecked, int taskId, String title, String subTitle) {
        this.isChecked = isChecked;
        this.taskId = taskId;
        this.title = title;
        this.subTitle = subTitle;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
