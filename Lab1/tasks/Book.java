package tasks;

import javax.naming.Name;

public final class Book {
    public Book(String id, String author, String name, int date) {
        if (date < 0) {
            throw new IllegalArgumentException("Date is negative");
        }

        this.id = id;
        this.author = author;
        this.name = name;
        this.date = date;
    }

    public String getId() {
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

    private String id;
    private String author;
    private String name;
    private int date;
}
