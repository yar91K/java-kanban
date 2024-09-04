import model.Epic;
import model.Status;
import model.SubTask;
import service.TaskManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        taskManager.create("Первая", "Важная");
        taskManager.create("Вторая", "Важная");

        SubTask subTask1 = taskManager.create("Третья+1", "Средняя", new Epic());
        SubTask subTask2 = taskManager.create("Третья+2", "Средняя", new Epic());
        ArrayList<SubTask> subTasksList = new ArrayList<>();
        subTasksList.add(subTask1);
        subTasksList.add(subTask2);
        taskManager.create("Третья", "Средняя", subTasksList);

        SubTask SubTask3 = taskManager.create("Четвертая+1", "Низкая", new Epic());
        ArrayList<SubTask> subTasksListId = new ArrayList<>();
        subTasksListId.add(SubTask3);
        taskManager.create("Четвертая", "Низкая", subTasksListId);

        taskManager.showInfo();

        taskManager.update(new SubTask(subTask2.getName(), subTask2.getDiscription(), subTask2.getId(),
                Status.DONE, subTask2.epic));
        taskManager.showInfo();

        taskManager.removeTaskById(1);
        taskManager.removeTaskById(5);
        taskManager.showInfo();


    }
}
