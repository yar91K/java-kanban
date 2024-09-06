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

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public ArrayList<Epic> getEpic() {
        return new ArrayList<>(epicTasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpicTasks() {
        epicTasks.clear();
        removeAllSubTasks();
    }

    public void removeAllSubTasks() {
        for (Map.Entry<Integer, Epic> value : epicTasks.entrySet()) {
            Epic valueEpic = value.getValue();
            valueEpic.clearSubTaskList();
            calculateStatus(valueEpic);
        }
        subTasks.clear();
    }

    public Task getByIdTask(int id) {
        Task returnValue = null;

        if (tasks.containsKey(id)) {
            Task value = tasks.get(id);
            returnValue = value;
        }
        return returnValue;
    }

    public SubTask getByIdSubTask(int id) {
        SubTask returnValue = null;
        if (subTasks.containsKey(id)) {
            SubTask value = subTasks.get(id);
            returnValue = value;
        }
        return returnValue;
    }

    public Epic getByIdEpic(int id) {
        Epic returnValue = null;
        if (epicTasks.containsKey(id)) {
            Epic value = epicTasks.get(id);
            returnValue = value;
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
        epicTasks.get(subTask.getEpicId()).addSubTask(subTask.getId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epicTasks.get(subTask.getEpicId());
        calculateStatus(epic);
        return subTask;
    }

    public Epic create(Epic epic) {
        epic.setId(generateId());
        epicTasks.put(epic.getId(), epic);
        return epic;
    }

    public Epic update(Epic newEpic) {
        final Epic epic = epicTasks.get(newEpic.getId());
        if (epic != null) {
            epic.setName(newEpic.getName());
            epic.setDescription(newEpic.getDescription());
            calculateStatus(epic);
        }
        return epic;
    }

    public Task update(Task newTask) {
        final Task task = tasks.get(newTask.getId());
        if (task != null) {
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.getStatus());
        }
        return task;
    }

    public SubTask update(SubTask newSubTask) {
        final SubTask subTask = subTasks.get(newSubTask.getId());
        if (subTask != null) {
            subTask.setName(newSubTask.getName());
            subTask.setDescription(newSubTask.getDescription());
            subTask.setStatus(newSubTask.getStatus());
            Epic epic = epicTasks.get(subTask.getEpicId());
            calculateStatus(epic);
        }
        return subTask;
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubTaskById(int id) {
        final SubTask subTask = subTasks.remove(id);
        Epic epic = epicTasks.get(subTask.getEpicId());
        epic.removeSubTask(id);
        calculateStatus(epic);

    }

    public void removeEpicById(int id) {
        final Epic epic = epicTasks.remove(id);
        for (Integer subTaskId : epic.getSubTaskList()) {
            subTasks.remove(subTaskId);
        }
        epicTasks.remove(id);
    }

    public ArrayList<Integer> getSubTasksByEpic(Integer epicId) {
        return epicTasks.get(epicId).getSubTaskList();
    }

    private void calculateStatus(Epic epic) {
        if (epic.getSubTaskList().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }
        Status status = subTasks.get(epic.getSubTaskList().get(0)).getStatus();
        for (Integer subTaskId : epic.getSubTaskList()) {
            if (status != subTasks.get(subTaskId).getStatus()) {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
        }
        epic.setStatus(status);
    }

    private int generateId() {
        return ++countTask;
    }
}
