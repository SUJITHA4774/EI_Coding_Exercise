package BillPugh;

public class Main {
    public static void main(String[] args) {
        System.out.println("Calling getInstance first time:");
        Singleton s1 = Singleton.getInstance();

        System.out.println("Calling getInstance second time:");
        Singleton s2 = Singleton.getInstance();

        System.out.println("Are both instances same? " + (s1 == s2));
    }
}
