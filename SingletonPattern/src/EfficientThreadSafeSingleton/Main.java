package EfficientThreadSafeSingleton;
class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " → " + instance.hashCode());
        };

        // Start two threads at the same time
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}

/**
 * Why is this better?
 * Only synchronizes the first time (reduces performance overhead).
 * Ensures instance is created safely in a multi-threaded environment.
 */