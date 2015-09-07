package cz.junak.restdi.core.exception;

/**
 * Dummy exception that someone would guess is thrown when book is not found in the "strorage" (csv), but it is really there
 * just to test exception mapping of REST frameworks.
 */
public class BookNotFoundException extends Exception {

    private static final long serialVersionUID = 1;

    public BookNotFoundException(int bookId) {
        super("Oh my god, where is that book?! Book with id " + bookId + " was not found!");
    }
}
