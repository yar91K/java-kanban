package model;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<SubTask> subTaskList;

    public Epic(String name, String discription, int id, Status status, ArrayList<SubTask> subTaskList) {
        super(name, discription, id, status);
        this.subTaskList = subTaskList;
    }

    public Epic() {
        subTaskList = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void addSubTask(SubTask subTask) {
        subTaskList.add(subTask);
    }

    public Status getStatus() {
        if (subTaskList.size() == 0) {
            return Status.NEW;
        } else {
            Status status = subTaskList.get(0).getStatus();
            for (SubTask subTaskId : subTaskList) {
                if (status != subTaskId.getStatus()) {
                    return Status.IN_PROGRESS;
                }
            }
            return status;
        }
    }

    @Override
    public String toString() {
        return "Epic{" + "name='" + name + '\'' +
                ", discription='" + description + '\'' +
                ", id=" + id +
                ", status=" + getStatus() +
                '}';
    }
}
