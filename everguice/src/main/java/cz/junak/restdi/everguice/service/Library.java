package cz.junak.restdi.everguice.service;

import cz.junak.restdi.core.Shelf;
import cz.junak.restdi.core.exception.BookNotFoundException;
import cz.junak.restdi.core.model.Book;
import cz.junak.restdi.core.model.LibraryInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/library")
public class Library {

    private final LibraryInfo info;

    private Map<String, Shelf> shelvesByName = new HashMap<>();

    public Library(LibraryInfo info, Map<String, Shelf> shelvesByName) {
        this.info = info;
        this.shelvesByName.putAll(shelvesByName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public LibraryInfo info() {
        return info;
    }

    @GET
    @Path("/book/{shelfName}/{bookId}")
    public Book book(@PathParam("shelfName") String shelfName, @PathParam("bookId") int bookId) {
        Shelf shelf = shelvesByName.get(shelfName);
        if (shelf != null) {
            return shelf.byId(bookId);
        }
        return null;
    }

    @GET
    @Path("/book/error")
    public Book error() throws BookNotFoundException {
        throw new BookNotFoundException(42);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/book/{shelfName}/{bookId}")
    public void putBook(@PathParam("shelfName") String shelfName, @PathParam("bookId") int bookId, Book book) {
        Shelf shelf = shelvesByName.get(shelfName);
        if (shelf != null) {
            shelf.put(bookId, book);
        }
    }
}
