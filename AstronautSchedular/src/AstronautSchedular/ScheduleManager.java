package AstronautSchedular;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<Observer> observers;
    private ReentrantLock lock = new ReentrantLock();

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        lock.lock();
        try {
            for (Observer observer : observers) {
                observer.update(task, tasks);
            }
            tasks.add(task);
            tasks.sort(Comparator.comparing(Task::getStartTime));
            ConsoleLogger.log("Task added successfully. No conflicts.");
        } finally {
            lock.unlock();
        }
    }

    public void removeTask(String description) {
        lock.lock();
        try {
            boolean removed = tasks.removeIf(task -> task.getDescription().equals(description));
            if (removed) {
                ConsoleLogger.log("Task removed successfully.");
            } else {
                ConsoleLogger.log("Error: Task not found.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void markTaskCompleted(String description) {
        lock.lock();
        try {
            for (Task task : tasks) {
                if (task.getDescription().equals(description)) {
                    task.setCompleted(true);
                    ConsoleLogger.log("Task marked as completed.");
                    return;
                }
            }
            ConsoleLogger.log("Error: Task not found.");
        } finally {
            lock.unlock();
        }
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public List<Task> viewTasksByPriority(String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public void editTask(String description, String newDescription, LocalTime newStartTime, LocalTime newEndTime, String newPriority) {
        lock.lock();
        try {
            for (Task task : tasks) {
                if (task.getDescription().equals(description)) {
                    task = TaskFactory.createTask(newDescription, newStartTime, newEndTime, newPriority);
                    ConsoleLogger.log("Task edited successfully.");
                    return;
                }
            }
            ConsoleLogger.log("Error: Task not found.");
        } finally {
            lock.unlock();
        }
    }
}