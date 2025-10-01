package AstronautSchedular;
import java.util.List;

public class TaskConflictObserver implements Observer {
    @Override
    public void update(Task newTask, List<Task> existingTasks) {
        for (Task task : existingTasks) {
            if (tasksOverlap(task, newTask)) {
                System.out.println("Error: Task conflicts with existing task \"" + task.getDescription() + "\".");
                return;
            }
        }
    }

    private boolean tasksOverlap(Task task1, Task task2) {
        return !(task1.getEndTime().compareTo(task2.getStartTime()) <= 0 ||
                 task2.getEndTime().compareTo(task1.getStartTime()) <= 0);
    }
}