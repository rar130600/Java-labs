import animals.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        try {
            ArrayList<Animal> list = new ArrayList<Animal>();
            list.add(new Omnivore(7, "Raccoon"));
            list.add(new Predator(2, "Bear"));
            list.add(new Herbivore(3, "Horse", "APPLE", 45));
            list.add(new Predator(1, "Lion"));
            list.add(new Herbivore(5, "Sheep"));
            list.add(new Omnivore(6, "Mouse", "CHEESE", 10));
            list.add(new Herbivore(4, "Horse",  new Food("APPLE", 55)));


            //sorting
            list.sort((o1, o2) -> (o1.getFood() != o2.getFood()) ?
                o2.getFood().getAmount() - o1.getFood().getAmount() :
                o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));


            //print all list
            //list.forEach(System.out::println);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
            System.out.println();

            //print first 5 elements
            for (int i = 0; i < 5; i++) {
                System.out.println(list.get(i).getName());
            }
            System.out.println();

            //print last 3 id
            for (int i = list.size() - 3; i < list.size(); i++) {
                System.out.println(list.get(i).getId());
            }
            System.out.println();

            //writing to file
            writeToFile(list, "animal");

            //reading from file
            ArrayList<Animal> list2 = new ArrayList<Animal>();
            readFromFile(list2, "animal");
            for (int i = 0; i < list2.size(); i++) {
                System.out.println(list2.get(i).toString());
            }

        } catch (IllegalArgumentException exept) {
            exept.printStackTrace();
        } catch (IOException exept) {
            System.err.println("File error");
        } catch (ClassNotFoundException exept) {
            exept.printStackTrace();
        }
    }

    static void writeToFile(List<Animal> list, String fileName) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
        for (Animal i : list) {
            output.writeObject(i);
        }
        output.close();
    }

    static void readFromFile(List<Animal> list, String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
        while (true) {
            try {
                list.add((Animal) input.readObject());
            } catch (EOFException exept) {
                input.close();
                break;
            }
        }
    }
}
