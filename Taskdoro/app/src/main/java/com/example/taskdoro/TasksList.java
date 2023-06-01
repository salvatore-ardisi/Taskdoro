package com.example.taskdoro;

public class TasksList {

    private String taskName;
    private String dateName;
    private String timeName;

    public TasksList(){
        this.taskName = "empty";
        this.dateName = "empty";
        this.timeName = "empty";
    }

    TasksList(String tkName, String dtName, String tmName) {
        this.taskName = tkName;
        this.dateName = dtName;
        this.timeName = tmName;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDateName() {
        return dateName;
    }

    public String getTimeName() {
        return timeName;
    }
}
