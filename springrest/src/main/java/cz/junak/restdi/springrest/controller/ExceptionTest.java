/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.junak.restdi.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pmensik
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "some reason")
public class ExceptionTest extends Exception {

    public ExceptionTest(String msg) {
        super(msg);
    }
    
    
}
