package ThreadSafeSingleton;

public class Singleton {
    private static Singleton instance;

    private Singleton() { }

    public static synchronized Singleton getInstance() {  // Synchronization added
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

