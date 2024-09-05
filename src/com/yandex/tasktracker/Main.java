package com.yandex.tasktracker;

import com.yandex.tasktracker.model.Epic;
import com.yandex.tasktracker.model.Status;
import com.yandex.tasktracker.model.SubTask;
import com.yandex.tasktracker.model.Task;
import com.yandex.tasktracker.service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Первая", "Высокая");
        Task task2 = new Task("Вторая", "Высокая");

        taskManager.create(task1);
        taskManager.create(task2);

        Epic epic1 = new Epic("Третья", "Средняя");
        Epic epic2 = new Epic("Четвертая", "низкая");

        taskManager.create(epic1);
        taskManager.create(epic2);

        SubTask subTask1 = new SubTask("Третья+1", "Средняя", epic1.getId());
        SubTask subTask2 = new SubTask("Третья+2", "Средняя", epic1.getId());
        SubTask subTask3 = new SubTask("Четвертая+1", "Средняя", epic2.getId());

        taskManager.create(subTask1);
        taskManager.create(subTask2);
        taskManager.create(subTask3);
        System.out.println(taskManager.showInfo());

        SubTask updatedSubTask = new SubTask(subTask2.getName(), subTask2.getDescription(), subTask2.epic);
        updatedSubTask.setId(subTask2.getId());
        updatedSubTask.setStatus(Status.DONE);
        taskManager.update(updatedSubTask);
        System.out.println(taskManager.showInfo());

        taskManager.removeTaskById(1);
        taskManager.removeSubTaskById(5);
        System.out.println(taskManager.showInfo());


    }
}
