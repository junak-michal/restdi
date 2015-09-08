package cz.junak.restdi.springrest.controller;

import cz.junak.restdi.core.exception.BookNotFoundException;
import cz.junak.restdi.core.model.Book;
import cz.junak.restdi.core.model.LibraryInfo;
import cz.junak.restdi.core.Shelf;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

@ResponseBody
@RequestMapping("/library")
public class Library {

    private final LibraryInfo info;

    private final Map<String, Shelf> shelvesByName = new HashMap<>();

    public Library(LibraryInfo info, Map<String, Shelf> shelvesByName) {
        this.info = info;
        this.shelvesByName.putAll(shelvesByName);
    }

    @RequestMapping(value = "/info", produces = "application/json", method = RequestMethod.GET)
    public LibraryInfo info() {
        return info;
    }

    @RequestMapping(value = "/book/{shelfName}/{bookId}", method = RequestMethod.GET, produces = "application/json")
    public Book book(@PathVariable String shelfName, @PathVariable int bookId) {
        Shelf shelf = shelvesByName.get(shelfName);
        if (shelf != null) {
            return shelf.byId(bookId);
        }
        return null;
    }

    @RequestMapping(value = "/book/error", method = RequestMethod.GET)
    public Book error() throws BookNotFoundException {
        throw new BookNotFoundException(42);
    }
    
    @RequestMapping(value = "/headers")
    public String httpHeaders(@RequestHeader("User-Agent") String userAgent, @RequestHeader Map<String, String> all) {
        StringBuilder response = new StringBuilder();
        response.append("User agent is ").append(userAgent);
        for(String header : all.keySet()) {
            response.append(header).append(" : ").append(all.get(header)).append("\n");
        }
        return response.toString();
    }

    @RequestMapping(value = "/book/{shelfName}/{bookId}", consumes = "application/json", method = RequestMethod.PUT)
    public void putBook(@PathVariable String shelfName, @PathVariable int bookId, @RequestBody Book book) {
        Shelf shelf = shelvesByName.get(shelfName);
        if (shelf != null) {
            shelf.put(bookId, book);
        }
    }
}
