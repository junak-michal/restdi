package cz.junak.restdi.springrest;

import cz.junak.restdi.core.model.LibraryInfo;
import cz.junak.restdi.springrest.controller.Library;
import cz.junak.restdi.core.Shelf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class SpringRESTConfiguration {

    @Bean
    public Library library() throws IOException {
        Map<String, Shelf> shelves = new HashMap<>();
        shelves.put("shelf1", firstShelf());
        shelves.put("shelf2", secondShelf());
        return new Library(libraryInfo(), shelves);
    }

    @Bean
    public LibraryInfo libraryInfo() {
        return new LibraryInfo("Public Library", "Brno");
    }

    @Bean
    public Shelf firstShelf() throws IOException {
        return Shelf.fromCSV("shelf1.csv");
    }

    @Bean
    public Shelf secondShelf() throws IOException {
        return Shelf.fromCSV("shelf2.csv");
    }
}
