package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    HashMap<Integer, Epic> epicTasks = new HashMap<Integer, Epic>();
    HashMap<Integer, SubTask> subTasks = new HashMap<Integer, SubTask>();
    int countTask = 0;

    public int generateId() {
        return ++countTask;
    }

    public void showInfo() {
        for (Map.Entry<Integer, Task> value : tasks.entrySet()) {
            Task valueTask = value.getValue();
            System.out.println(valueTask.toString());
        }
        for (Map.Entry<Integer, Epic> value : epicTasks.entrySet()) {
            Epic valueTask = value.getValue();
            System.out.println(valueTask.toString());
        }
        for (Map.Entry<Integer, SubTask> value : subTasks.entrySet()) {
            SubTask valueTask = value.getValue();
            System.out.println(valueTask.toString());
        }
    }

    public void removeAllTask() {
        tasks.clear();
        epicTasks.clear();
        subTasks.clear();
    }

    public void showInfoById(int id) {
        if (tasks.containsKey(id)) {
            Task value = tasks.get(id);
            System.out.println(value.toString());
        }
        if (epicTasks.containsKey(id)) {
            Epic value = epicTasks.get(id);
            System.out.println(value.toString());
        }
        if (subTasks.containsKey(id)) {
            SubTask value = subTasks.get(id);
            System.out.println(value.toString());
        }
    }

    public Task create(String name, String description) {
        Task task = new Task(name, description, generateId(), Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask create(String name, String description, Epic epic) {
        SubTask subTask = new SubTask(name, description, generateId(), Status.NEW, epic);
        epic.addSubTask(subTask);
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }

    public Epic create(String name, String description, ArrayList<SubTask> subTaskList) {
        Epic epic = new Epic(name, description, generateId(), Status.NEW, subTaskList);
        for (SubTask subTaskId : subTaskList) {
            subTaskId.epic = epic;
        }
        epicTasks.put(epic.getId(), epic);
        return epic;
    }

    public void update(Epic newEpic) {
        if (epicTasks.containsKey(newEpic.getId())) {
            Epic epic = epicTasks.get(newEpic.getId());
            epic.setName(newEpic.getName());
            epic.setDiscription(newEpic.getDiscription());
            epic.setStatus(newEpic.getStatus());
        }
    }

    public void update(Task newTask) {
        if (tasks.containsKey(newTask.getId())) {
            Task task = tasks.get(newTask.getId());
            task.setName(newTask.getName());
            task.setDiscription(newTask.getDiscription());
            task.setStatus(newTask.getStatus());
        }
    }

    public void update(SubTask newSubTask) {
        if (subTasks.containsKey(newSubTask.getId())) {
            SubTask subTask = subTasks.get(newSubTask.getId());
            subTask.setName(newSubTask.getName());
            subTask.setDiscription(newSubTask.getDiscription());
            subTask.setStatus(newSubTask.getStatus());
        }
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
        epicTasks.remove(id);
        subTasks.remove(id);
    }

    public ArrayList<SubTask> getSubTasksByEpic(Epic epic) {
        return epic.getSubTaskList();
    }


}
