package com.yandex.tasktracker.model;

public class SubTask extends Task {
    public final Integer epic;


    public SubTask(String name, String description, Integer epic) {
        super(name, description);
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epic=" + epic +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
