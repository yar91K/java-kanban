package com.yandex.tasktracker.service;

import com.yandex.tasktracker.model.Epic;
import com.yandex.tasktracker.model.Status;
import com.yandex.tasktracker.model.SubTask;
import com.yandex.tasktracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    private final HashMap<Integer, Epic> epicTasks = new HashMap<Integer, Epic>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<Integer, SubTask>();
    private int countTask = 0;

    public String showInfo() {
        String returnValue = "";

        for (Map.Entry<Integer, Task> value : tasks.entrySet()) {
            Task valueTask = value.getValue();
            returnValue += valueTask.toString() + '\n';
        }
        for (Map.Entry<Integer, Epic> value : epicTasks.entrySet()) {
            Epic valueTask = value.getValue();
            returnValue += valueTask.toString() + '\n';
        }
        for (Map.Entry<Integer, SubTask> value : subTasks.entrySet()) {
            SubTask valueTask = value.getValue();
            returnValue += valueTask.toString() + '\n';
        }
        return returnValue;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpicTasks() {
        epicTasks.clear();
    }

    public void removeAllSubTasks() {
        subTasks.clear();
    }

    public String showInfoById(int id) {
        String returnValue = "";

        if (tasks.containsKey(id)) {
            Task value = tasks.get(id);
            returnValue = value.toString();
        }
        if (epicTasks.containsKey(id)) {
            Epic value = epicTasks.get(id);
            returnValue = value.toString();
        }
        if (subTasks.containsKey(id)) {
            SubTask value = subTasks.get(id);
            returnValue = value.toString();
        }
        return returnValue;
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask create(SubTask subTask) {
        subTask.setId(generateId());
        epicTasks.get(subTask.epic).addSubTask(subTask.getId());
        subTasks.put(subTask.getId(), subTask);
        epicTasks.get(subTask.epic).setStatus(calculateStatus(epicTasks.get(subTask.epic)));
        return subTask;
    }

    public Epic create(Epic epic) {
        epic.setId(generateId());
        epicTasks.put(epic.getId(), epic);
        return epic;
    }

    public Epic update(Epic newEpic) {
        if (epicTasks.containsKey(newEpic.getId())) {
            Epic epic = epicTasks.get(newEpic.getId());
            epic.setName(newEpic.getName());
            epic.setDescription(newEpic.getDescription());
            epic.setStatus(newEpic.getStatus());
            return epic;
        }
        return null;
    }

    public Task update(Task newTask) {
        if (tasks.containsKey(newTask.getId())) {
            Task task = tasks.get(newTask.getId());
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.getStatus());
            return task;
        }
        return null;
    }

    public SubTask update(SubTask newSubTask) {
        if (subTasks.containsKey(newSubTask.getId())) {
            SubTask subTask = subTasks.get(newSubTask.getId());
            subTask.setName(newSubTask.getName());
            subTask.setDescription(newSubTask.getDescription());
            subTask.setStatus(newSubTask.getStatus());
            epicTasks.get(subTask.epic).setStatus(calculateStatus(epicTasks.get(subTask.epic)));
            return subTask;
        }
        return null;
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubTaskById(int id) {
        epicTasks.get(subTasks.get(id).epic).removeSubTask(id);
        subTasks.remove(id);
    }

    public void removeEpicById(int id) {
        for (Integer i : epicTasks.get(id).getSubTaskList()) {
            subTasks.remove(i);
        }
        epicTasks.remove(id);
    }

    public ArrayList<Integer> getSubTasksByEpic(Epic epic) {
        return epic.getSubTaskList();
    }

    public Status calculateStatus(Epic epic) {
        if (epic.getSubTaskList().size() == 0) {
            return Status.NEW;
        } else {
            Status status = subTasks.get(epic.getSubTaskList().get(0)).getStatus();
            for (Integer subTaskId : epic.getSubTaskList()) {
                if (status != subTasks.get(subTaskId).getStatus()) {
                    return Status.IN_PROGRESS;
                }
            }
            return status;
        }
    }

    private int generateId() {
        return ++countTask;
    }
}
