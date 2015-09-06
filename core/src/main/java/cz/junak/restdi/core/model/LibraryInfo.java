package cz.junak.restdi.core.model;

/**
 * Holds basic information about a library.
 */
public class LibraryInfo {

    private final String name;

    private final String town;

    public LibraryInfo(String name, String town) {
        this.name = name;
        this.town = town;
    }

    /**
     * Getter for the name of the town where the ibrary is located.
     * @return the name of the town where the ibrary is located.
     */
    public String getTown() {
        return town;
    }

    /**
     * Getter for the name of the library.
     * @return the name of the library;
     */
    public String getName() {
        return name;
    }
}
