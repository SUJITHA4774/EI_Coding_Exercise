package AstronautSchedular;
import java.time.LocalTime;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean isCompleted;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description + " [" + priority + "]" + (isCompleted ? " (Completed)" : "");
    }
}