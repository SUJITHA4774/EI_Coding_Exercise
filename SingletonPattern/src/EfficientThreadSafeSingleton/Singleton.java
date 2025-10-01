package EfficientThreadSafeSingleton;

public class Singleton {
    private static volatile Singleton instance;  // Volatile keyword ensures visibility

    private Singleton() { }

    public static Singleton getInstance() {
        Singleton localInstance = instance;
        if (localInstance == null) {  // First check (No Lock)
            synchronized (Singleton.class) {  // Thread safety
                if (localInstance == null) {  // Second check (Inside Lock)
                    localInstance = new Singleton();
                }
                instance = localInstance;
            }
        }
        return localInstance;
    }
}

