/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.junak.restdi.springrest.controller;

/**
 *
 * @author pmensik
 */
public class ServerErrorInfo {
    
    private String exceptionMsg;

    public ServerErrorInfo(String exceptionMsg) {
        this.exceptionMsg = "exceptioooooooon\n" + exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
    
    
}
