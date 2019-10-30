package tasks;

import interfaceTasks.InterfaceTask3;

import java.io.InputStreamReader;
import java.util.Scanner;

public final class Task3 implements InterfaceTask3 {
    private static Scanner scanner = new Scanner(new InputStreamReader(System.in));

    @Override
    public void startTask() throws IllegalArgumentException, ArrayIndexOutOfBoundsException, NullPointerException {
        System.out.println("Available commands:");
        System.out.println("\"add\" to add new book");
        System.out.println("\"updateByIndex\" to update book by index");
        System.out.println("\"deleteByIndex\" to delete book");
        System.out.println("\"readByIndex\" to read book by index");
        System.out.println("\"findById\" to find books by id");
        System.out.println("\"findByAuthor\" to find books by author");
        System.out.println("\"findByName\" to find book by name");
        System.out.println("\"findByDate\" to find books by release date");
        System.out.println("\"printAll\" to print all books");
        System.out.println("\"exit\" to exit from menu");

        startMenu();

    }

    private void startMenu() throws IllegalArgumentException, ArrayIndexOutOfBoundsException, NullPointerException {
        BookList bookList = new BookList();

        while (true) {
            String command = this.readCommand();
            switch (command) {
                case "add":
                    bookList.addBook(readBook());
                    break;

                case "updateByIndex":
                    System.out.println("Type book's index to update: ");
                    int indexUpdate = scanner.nextInt();
                    bookList.updateByIndex(indexUpdate, readBook());
                    break;

                case "deleteByIndex":
                    System.out.println("Type book's index to delete: ");
                    int indexDelete = scanner.nextInt();
                    bookList.deleteByIndex(indexDelete);
                    break;

                case "readByIndex":
                    System.out.println("Type book's index to print: ");
                    int indexRead = scanner.nextInt();
                    System.out.println(bookList.getByIndex(indexRead));
                    break;

                case "findById":
                    System.out.println("Type book's id to print: ");
                    int indexFindId = scanner.nextInt();
                    BookList booksById = new BookList(bookList.getById(indexFindId));
                    booksById.printAll();
                    break;

                case "findByAuthor":
                    System.out.println("Type book's author to print: ");
                    String author = scanner.next();
                    BookList bookByAuthor = new BookList(bookList.getByAuthor(author));
                    bookByAuthor.printAll();
                    break;

                case "findByName":
                    System.out.println("Type book's name to print: ");
                    String name = scanner.next();
                    BookList bookByName = new BookList(bookList.getByName(name));
                    bookByName.printAll();
                    break;

                case "findByDate":
                    System.out.println("Type book's release date to print: ");
                    int date = scanner.nextInt();
                    BookList bookByDate = new BookList(bookList.getByDate(date));
                    bookByDate.printAll();
                    break;

                case "printAll":
                    bookList.printAll();
                    break;

                case "exit":
                    return;

                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    private String readCommand() {
        return scanner.next();
    }

    private Book readBook() {
        System.out.println("Insert a book:");

        System.out.print("Id: ");
        int id = scanner.nextInt();

        System.out.print("Author: ");
        String author = scanner.next();

        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Release date: ");
        int date = scanner.nextInt();

        return new Book(id, author, name, date);
    }
}
