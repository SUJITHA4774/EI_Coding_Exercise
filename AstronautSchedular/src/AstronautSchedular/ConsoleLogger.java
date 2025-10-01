package AstronautSchedular;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void log(String message) {
        System.out.println(dtf.format(LocalDateTime.now()) + " INFO: " + message);
    }

    public static void error(String message) {
        System.err.println(dtf.format(LocalDateTime.now()) + " ERROR: " + message);
    }
}