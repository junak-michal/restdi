package cz.junak.restdi.easycdi;

import cz.junak.restdi.core.Shelf;
import cz.junak.restdi.core.model.LibraryInfo;

import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CDIConfiguration {

    @Produces
    public LibraryInfo libraryInfo() {
        return new LibraryInfo("Public Library", "Brno");
    }

    @Produces
    public Map<String, Shelf> shelvesByName() throws IOException {
        Map<String, Shelf> result = new HashMap<>();
        result.put("shelf1", Shelf.fromCSV("shelf1.csv"));
        result.put("shelf2", Shelf.fromCSV("shelf2.csv"));
        return result;
    }
}
