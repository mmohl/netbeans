/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author mmohl
 */
public class Status {
    public static final String ACCESS_DENIED = "Aksed Ditolak!";
    public static final String WAITING = "PROCESS";
    
    public static <T> String SuccessText(T o){
        return o.getClass().getName();
    }
    
}
