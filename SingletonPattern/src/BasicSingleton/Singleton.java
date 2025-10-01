package BasicSingleton;

class Singletondemo {
    // Step 1: Declare static variable
    private static Singletondemo instance;

    // Step 2: Private constructor – restricts object creation
    private Singletondemo() {  // Private constructor
        System.out.println("Singleton instance created!");
    }

    // Step 3: Provide static method to get an instance
    public static Singletondemo getInstance() {
        if (instance == null) {  // Create instance only if it doesn't exist
            instance = new Singletondemo();
        }
        return instance;
    }
}

public class Singleton {
    public static void main(String[] args) {
        Singletondemo obj1 = Singletondemo.getInstance();
        Singletondemo obj2 = Singletondemo.getInstance();

        System.out.println(obj1 == obj2);  // Output: true (Same instance)
    }
}


/**
 * Issues with This Approach
 * Not Thread-safe: Multiple threads may create multiple instances simultaneously.
 */
//now we get the output as true even when we create a multiple object. here Singleton is not violated