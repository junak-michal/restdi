package cz.junak.restdi.core.model;

/**
 * Represents a single book with a name an author information.
 *
 * <p>
 * XXX: This class has to have an empty constructor and setters because of JSON deserialization.
 * I would prefer to let JSON deserializer use the constructor with name and author as parameters but that is impossible, because:
 * <ul>
 *     <li>resteasy-jackson-provider-3.0.12.Final uses old jackson 1.9.12 but spring-web-4.2.1.RELEASE uses new jackson 2.6.1.
 *     That literally means annotating the constructor with two @JsonCreator annotations (and constructor parameters too, of course).</li>
 *     <li>everrest has its own built in JSON deserialization which needs the class to have an empty constructor and setters.</li>
 * </ul>
 * </p>
 */
public class Book {

    private String name;

    private String author;

    public Book() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
