package cz.junak.restdi.core;

import cz.junak.restdi.core.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents a real world/physical shelf, which is basically a set of books.
 */
public class Shelf {

    private final ConcurrentMap<Integer, Book> booksById = new ConcurrentHashMap<>();

    private Shelf() {
    }

    public Book byId(int id) {
        return booksById.get(id);
    }

    public void put(int id, Book book) {
        booksById.put(id, book);
    }

    /**
     * Constructs new Shelf from csv with following format: ${id},${book name},${book author}
     * @param csvName Name of the csv file that can be loaded as a resource from this class.
     * @return Shelf representing books in given csv.
     * @throws IOException If there is a problem reading the csv.
     */
    public static Shelf fromCSV(String csvName) throws IOException {
        Shelf result = new Shelf();
        try (Reader csvReader = new InputStreamReader(Shelf.class.getClassLoader().getResource(csvName).openStream())) {
           CSVParser csvParser = CSVFormat.DEFAULT.parse(csvReader);
            putRecordsToShelve(result, csvParser.getRecords());
        }
        return result;
    }

    private static void putRecordsToShelve(Shelf shelf, List<CSVRecord> records) throws IOException {
        for (CSVRecord curRecord : records) {
            shelf.booksById.putIfAbsent(Integer.parseInt(curRecord.get(0)), new Book(curRecord.get(1), curRecord.get(2)));
        }
    }
}
