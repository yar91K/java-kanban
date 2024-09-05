package com.yandex.tasktracker.model;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subTaskList = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public ArrayList<Integer> getSubTaskList() {
        return subTaskList;
    }

    public void addSubTask(Integer subTask) {
        subTaskList.add(subTask);
    }

    public void removeSubTask(Integer id) {
        subTaskList.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + getStatus() +
                '}';
    }
}
