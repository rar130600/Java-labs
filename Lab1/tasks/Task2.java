package tasks;

import interfaceTasks.InterfaceTask2;

import java.security.SecureRandom;

public final class Task2 implements InterfaceTask2 {
    @Override
    public String[] stringFragmentation(String string) {
        String[] result = new String[string.length() / 3];

        for (int i = 0; i < string.length() / 3; i++) {
            char ch = getRandomChar();

            while ((ch == string.charAt(3 * i)) || (ch == string.charAt(3 * i + 2))) {
                ch = getRandomChar();
            }

            result[i] = "" + string.charAt(3 * i) + getRandomChar() + string.charAt(3 * i + 2);
        }

        return result;
    }

    @Override
    public void printFragments(String[] strings) {
        for (String i : strings) {
            System.out.println(i);
        }
    }

    private char getRandomChar() {
        SecureRandom r = new SecureRandom();
        return (char)r.nextInt(127);
    }
}
