package cz.junak.restdi.easycdi.service;

import cz.junak.restdi.easycdi.service.second.OlomoucLibrary;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/easycdi")
@ApplicationScoped
public class EasyCDIApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<Class<?>>();
        result.add(LibraryEndpoint.class);
        result.add(OlomoucLibrary.class);
        return result;
    }
    
}
