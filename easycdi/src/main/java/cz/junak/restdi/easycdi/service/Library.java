package cz.junak.restdi.easycdi.service;

import cz.junak.restdi.core.Shelf;
import cz.junak.restdi.core.exception.BookNotFoundException;
import cz.junak.restdi.core.model.Book;
import cz.junak.restdi.core.model.LibraryInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/library")
@ApplicationScoped
public class Library {

    @Inject
    private LibraryInfo info;

    @Inject
    private Map<String, Shelf> shelvesByName;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public LibraryInfo info() {
        return info;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
}
