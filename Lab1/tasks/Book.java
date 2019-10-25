package tasks;

import javax.naming.Name;

public final class Book {
    public Book(int id, String author, String name, int date) {
        if (id < 0) {
            throw new IllegalArgumentException("Id is negative");
        }
        if (date < 0) {
            throw new IllegalArgumentException("Date is negative");
        }

        this.id = id;
        this.author = author;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Book: " + id + " " + author + " " + name + " " + date;
    }

    private int id;
    private String author;
    private String name;
    private int date;
}
