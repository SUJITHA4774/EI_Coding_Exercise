package AstronautSchedular;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private ScheduleManager scheduleManager;
    private Scanner scanner;

    public App() {
        scheduleManager = ScheduleManager.getInstance();
        scanner = new Scanner(System.in);
        scheduleManager.addObserver(new TaskConflictObserver());
    }

    public void run() {
        boolean running = true;
        while (running) {
            try {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        removeTask();
                        break;
                    case 3:
                        viewTasks();
                        break;
                    case 4:
                        markTaskCompleted();
                        break;
                    case 5:
                        viewTasksByPriority();
                        break;
                    case 6:
                        editTask();
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        ConsoleLogger.log("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                ConsoleLogger.error("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            } catch (Exception e) {
                ConsoleLogger.error("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        ConsoleLogger.log("1. Add Task");
        ConsoleLogger.log("2. Remove Task");
        ConsoleLogger.log("3. View Tasks");
        ConsoleLogger.log("4. Mark Task as Completed");
        ConsoleLogger.log("5. View Tasks by Priority");
        ConsoleLogger.log("6. Edit Task");
        ConsoleLogger.log("7. Exit");
        ConsoleLogger.log("Enter your choice: ");
    }

    private void addTask() {
        try {
            ConsoleLogger.log("Enter description: ");
            String description = scanner.nextLine();
            ConsoleLogger.log("Enter start time (HH:mm): ");
            String startTime = scanner.nextLine();
            ConsoleLogger.log("Enter end time (HH:mm): ");
            String endTime = scanner.nextLine();
            ConsoleLogger.log("Enter priority (High/Medium/Low): ");
            String priority = scanner.nextLine();

            validateTimeFormat(startTime);
            validateTimeFormat(endTime);
            validatePriority(priority);

            Task task = TaskFactory.createTask(description, LocalTime.parse(startTime), LocalTime.parse(endTime), priority);
            scheduleManager.addTask(task);
        } catch (DateTimeParseException e) {
            ConsoleLogger.error("Error: Invalid time format.");
        } catch (IllegalArgumentException e) {
            ConsoleLogger.error(e.getMessage());
        } catch (Exception e) {
            ConsoleLogger.error("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void removeTask() {
        ConsoleLogger.log("Enter description of task to remove: ");
        String description = scanner.nextLine();
        scheduleManager.removeTask(description);
    }

    private void viewTasks() {
        List<Task> tasks = scheduleManager.getTasks();
        if (tasks.isEmpty()) {
            ConsoleLogger.log("No tasks scheduled for the day.");
        } else {
            for (Task task : tasks) {
                ConsoleLogger.log(task.toString());
            }
        }
    }

    private void markTaskCompleted() {
        ConsoleLogger.log("Enter description of task to mark as completed: ");
        String description = scanner.nextLine();
        scheduleManager.markTaskCompleted(description);
    }

    private void viewTasksByPriority() {
        ConsoleLogger.log("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();
        List<Task> tasks = scheduleManager.viewTasksByPriority(priority);
        if (tasks.isEmpty()) {
            ConsoleLogger.log("No tasks with the specified priority.");
        } else {
            for (Task task : tasks) {
                ConsoleLogger.log(task.toString());
            }
        }
    }

    private void editTask() {
        try {
            ConsoleLogger.log("Enter description of task to edit: ");
            String description = scanner.nextLine();
            ConsoleLogger.log("Enter new description: ");
            String newDescription = scanner.nextLine();
            ConsoleLogger.log("Enter new start time (HH:mm): ");
            String newStartTime = scanner.nextLine();
            ConsoleLogger.log("Enter new end time (HH:mm): ");
            String newEndTime = scanner.nextLine();
            ConsoleLogger.log("Enter new priority (High/Medium/Low): ");
            String newPriority = scanner.nextLine();

            validateTimeFormat(newStartTime);
            validateTimeFormat(newEndTime);
            validatePriority(newPriority);

            scheduleManager.editTask(description, newDescription, LocalTime.parse(newStartTime), LocalTime.parse(newEndTime), newPriority);
        } catch (DateTimeParseException e) {
            ConsoleLogger.error("Error: Invalid time format.");
        } catch (IllegalArgumentException e) {
            ConsoleLogger.error(e.getMessage());
        } catch (Exception e) {
            ConsoleLogger.error("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void validateTimeFormat(String time) {
        LocalTime.parse(time);
    }

    private void validatePriority(String priority) {
        if (!priority.equalsIgnoreCase("High") && 
            !priority.equalsIgnoreCase("Medium") && 
            !priority.equalsIgnoreCase("Low")) {
            throw new IllegalArgumentException("Error: Invalid priority level.");
        }
    }
}