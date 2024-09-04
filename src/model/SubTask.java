package model;

public class SubTask extends Task {
    public Epic epic;


    public SubTask(String name, String discription, int id, Status status, Epic epic) {
        super(name, discription, id, status);
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epic=" + epic +
                ", name='" + name + '\'' +
                ", discription='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
