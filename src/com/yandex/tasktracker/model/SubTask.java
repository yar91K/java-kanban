package com.yandex.tasktracker.model;

public class SubTask extends Task {
    private final Integer epicId;

    public SubTask(String name, String description, Integer epic) {
        super(name, description);
        this.epicId = epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epic=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + getStatus() +
                '}';
    }

    public Integer getEpicId() {
        return epicId;
    }
}
