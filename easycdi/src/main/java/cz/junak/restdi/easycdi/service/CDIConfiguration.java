package cz.junak.restdi.easycdi.service;

import cz.junak.restdi.core.model.LibraryInfo;

import javax.enterprise.inject.Produces;

public class CDIConfiguration {

    @Produces
    public LibraryInfo libraryInfo() {
        return new LibraryInfo("Public Library", "Brno");
    }
}
