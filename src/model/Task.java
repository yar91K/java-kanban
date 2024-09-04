package model;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected Status status;

    public Task(String name, String discription, int id, Status status) {
        this.name = name;
        this.description = discription;
        this.id = id;
        this.status = status;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", discription='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else {
            System.out.println("Передано некорректное id");
        }
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        } else {
            System.out.println("Передано некоректное название!");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
