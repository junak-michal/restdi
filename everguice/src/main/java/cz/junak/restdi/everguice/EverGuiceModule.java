package cz.junak.restdi.everguice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import cz.junak.restdi.core.Shelf;
import cz.junak.restdi.core.model.LibraryInfo;
import cz.junak.restdi.everguice.service.Library;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EverGuiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        try {
            LibraryInfo info = new LibraryInfo("Public Library", "Brno");
            Map<String, Shelf> shelves = new HashMap<>();
            shelves.put("shelf1", Shelf.fromCSV("shelf1.csv"));
            shelves.put("shelf2", Shelf.fromCSV("shelf2.csv"));
            binder.bind(LibraryInfo.class).toInstance(info);
            binder.bind(new TypeLiteral<Map<String, Shelf>>(){}).toInstance(shelves);
            binder.bind(Library.class).toConstructor(Library.class.getConstructor(LibraryInfo.class, Map.class));
        } catch (NoSuchMethodException | IOException exc) {
            exc.printStackTrace();
        }
    }
}
