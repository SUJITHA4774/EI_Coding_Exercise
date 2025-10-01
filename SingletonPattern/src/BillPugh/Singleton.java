package BillPugh;

public class Singleton {
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
