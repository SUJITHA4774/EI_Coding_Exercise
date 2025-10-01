package AstronautSchedular;
import java.util.List;

public interface Observer {
    void update(Task newTask, List<Task> existingTasks);
}