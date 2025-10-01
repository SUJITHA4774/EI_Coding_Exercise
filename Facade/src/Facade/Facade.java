package Facade;
import java.util.Scanner;

// Subsystem classes
class CPU {
    public void freeze() {
        System.out.println("Freezing CPU");
    }

    public void jump(long position) {
        System.out.println("Jumping to position " + position);
    }

    public void execute() {
        System.out.println("Executing CPU instructions");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Loading data into memory at position " + position);
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("Reading " + size + " bytes from position " + lba + " on hard drive");
        return new byte[size];
    }
}

// Facade class
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        memory.load(0L, hardDrive.read(0L, 1024));
        cpu.jump(0L);
        cpu.execute();
        System.out.println("Computer started");
    }

    public void shutdown() {
        System.out.println("Shutting down computer");
    }
}

// Client
public class Facade {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: 1 - Start Computer, 2 - Shutdown Computer, 0 - Exit");
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    computer.start();
                    break;
                case 2:
                    computer.shutdown();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}