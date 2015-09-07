/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.junak.restdi.easycdi.service;

import javax.ws.rs.FormParam;

/**
 *
 * @author pmensik
 */
public class MyBean {

    @FormParam("firstName")
    private String firstName;
    
    @FormParam("lastName")
    private String lastName;

    public MyBean() {
    }

    public MyBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
