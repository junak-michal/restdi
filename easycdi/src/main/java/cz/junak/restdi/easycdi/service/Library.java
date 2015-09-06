package cz.junak.restdi.easycdi.service;

import cz.junak.restdi.core.model.LibraryInfo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/library")
@ApplicationScoped
public class Library {

    @Inject
    private LibraryInfo info;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public LibraryInfo info() {
        return info;
    }
}
