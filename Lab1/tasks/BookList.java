package tasks;

import java.util.ArrayList;

public final class BookList {
    private ArrayList<Book> list;

    public BookList() {
        list = new ArrayList<Book>();
    }

    public BookList(ArrayList<Book> list) {
        this.list = list;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book is null");
        }
        list.add(book);
    }

    public void updateByIndex(int index, Book book) {
        if ((index < 0) || (index > list.size())) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        if (book == null) {
            throw new NullPointerException("Book is null");
        }

        list.set(index, book);
    }

    public void deleteByIndex(int index) {
        if ((index < 0) || (index > list.size())) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }

        list.remove(index);
    }

    public Book getByIndex(int index) {
        if ((index < 0) || (index > list.size())) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }

        return list.get(index);
    }

    public ArrayList<Book> getById(int id) {
        ArrayList<Book> result = new ArrayList<Book>();

        for (Book i : list) {
            if (i.getId() == id) {
                result.add(i);
            }
        }

        return result;
    }

    public ArrayList<Book> getByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<Book>();

        for (Book i : list) {
            if (i.getAuthor().equals(author)) {
                result.add(i);
            }
        }

        return result;
    }

    public ArrayList<Book> getByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();

        for(Book i : list) {
            if (i.getName().equals(name)) {
                result.add(i);
            }
        }

        return result;
    }

    public ArrayList<Book> getByDate(int date) {
        if (date < 0) {
            throw new IllegalArgumentException("Date is negative");
        }

        ArrayList<Book> result = new ArrayList<Book>();

        for (Book i : list) {
            if (i.getDate() == date) {
                result.add(i);
            }
        }

        return result;
    }

    public void printAll() {
        for (Book i : list) {
            System.out.println(i);
        }
    }
}
