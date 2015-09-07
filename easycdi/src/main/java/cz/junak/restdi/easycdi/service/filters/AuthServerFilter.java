/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.junak.restdi.easycdi.service.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import org.apache.http.HttpHeaders;

/**
 *
 * @author pmensik
 */
@PreMatching
@Provider
public class AuthServerFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String authHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authHeader == null) {
            crc.getHeaders().add("MyHeader", "Not authorized");
        }
    }
    
}
