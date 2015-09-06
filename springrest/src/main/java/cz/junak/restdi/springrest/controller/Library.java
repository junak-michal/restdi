package cz.junak.restdi.springrest.controller;

import cz.junak.restdi.core.model.Book;
import cz.junak.restdi.core.model.LibraryInfo;
import cz.junak.restdi.core.Shelf;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@ResponseBody
@RequestMapping("/library")
public class Library {

    private final LibraryInfo info;

    private final Map<String, Shelf> shelvesByName = new HashMap<>();

    public Library(LibraryInfo info, Map<String, Shelf> shelvesByName) {
        this.info = info;
        this.shelvesByName.putAll(shelvesByName);
    }

    @RequestMapping(value = "/info", produces = "application/json")
    public LibraryInfo info() {
        return info;
    }

    @RequestMapping(value = "/book/{shelfName}/{bookId}", produces = "application/json")
    public Book book(@PathVariable String shelfName, @PathVariable int bookId) {
        Shelf shelf = shelvesByName.get(shelfName);
        if (shelf != null) {
            return shelf.byId(bookId);
        }
        return null;
    }
}
