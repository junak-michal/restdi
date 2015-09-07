/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.junak.restdi.easycdi.service.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pmensik
 */
@Provider
public class CacheControlFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext response) throws IOException {
        if(crc.getMethod().equals("GET")) {
            CacheControl cache = new CacheControl();
            cache.setMaxAge(100);
            response.getHeaders().add("Cache-Control", cache);
        }
    }

    
}
