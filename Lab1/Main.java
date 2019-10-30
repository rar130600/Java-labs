import interfaceTasks.InterfaceTask1;
import interfaceTasks.InterfaceTask2;
import interfaceTasks.InterfaceTask3;
import tasks.Task1;
import tasks.Task2;
import tasks.Task3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task1:");
        InterfaceTask1 task1 = new Task1();
        task1.printMatrix(task1.createMatrix(4, 8));
        System.out.println();

        System.out.println("Task2:");
        InterfaceTask2 task2 = new Task2();
        task2.printFragments(task2.stringFragmentation("RandomString"));
        System.out.println();

        System.out.println("Task3:");
        InterfaceTask3 task3 = new Task3();
        try {
            task3.startTask();
        } catch (IllegalArgumentException exept) {
            exept.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException exept) {
            exept.printStackTrace();
        } catch (NullPointerException exept) {
            exept.printStackTrace();
        }
    }
}
