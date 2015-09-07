package cz.junak.restdi.easycdi.service;

import cz.junak.restdi.core.Shelf;
import cz.junak.restdi.core.model.Book;
import cz.junak.restdi.core.model.LibraryInfo;
import cz.junak.restdi.easycdi.service.second.Library;
import cz.junak.restdi.easycdi.service.second.OlomoucLibrary;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;

@Path("/library")
@ApplicationScoped
public class LibraryEndpoint {

    @Inject
    private LibraryInfo info;

    @Inject
    private Map<String, Shelf> shelvesByName;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public LibraryInfo info() {
        return new LibraryInfo("abc", "asd");
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
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/httpInfo")
    public String httpHeaders(@HeaderParam("User-Agent") String userAgent, @Context HttpHeaders all) {
        StringBuilder response = new StringBuilder();
        response.append("User agent is ").append(userAgent);
        for(String header : all.getRequestHeaders().keySet()) {
            response.append(header).append(" : ").append(all.getRequestHeader(header).get(0)).append("\n");
        }
        return response.toString();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/bean")
    public String bean(@BeanParam MyBean bean) {
        return bean.getFirstName() + ", " + bean.getLastName();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/cookies")
    public String cookies(@CookieParam("") Cookie cookie) {
        return "";
    }
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/enum/{name}")
    public String conversion(@PathParam("name") Color color) {
        return color.toString();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/response/{type}")
    public Response exceptions(@PathParam("type") int type) throws IOException {
        switch(type) {
            case 0:
                throw new WebApplicationException(Response.Status.CONFLICT);
            case 1:
                throw new NotAuthorizedException("Bearer");
            case 2:
                throw new IOException("some bad io exception");
            default:
                Response.ResponseBuilder builder = Response.ok("zdarec", MediaType.APPLICATION_JSON);
                return builder.cookie(new NewCookie("a", "value")).encoding("UTF-8").build();
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/cache")
    public Response cache() throws IOException {
        CacheControl cache = new CacheControl();
        cache.setMaxAge(50);
        cache.setPrivate(true);
        Response.ResponseBuilder builder = Response.ok();
        builder.cacheControl(cache);
        return builder.build();
    }
    
    @Path("/other/{other-library-id}")
    public Library getOtherLibrary(@PathParam("other-library-id") int id) {
        if(id == 0) {
            return new OlomoucLibrary();
        }
        return null;
    }
}
