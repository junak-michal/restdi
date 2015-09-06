package cz.junak.restdi.core.model;

/**
 * Represents a single book with a name an author information.
 */
public class Book {

    private final String name;

    private final String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
