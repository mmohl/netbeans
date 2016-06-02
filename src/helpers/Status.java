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
    public static final String ACCESS_DENIED = "Access Denied";
    public static final String WAITING = "PROCESS";
    public static final String MESSAGE_DELETE = "Are you sure want to delete this ?";
    public static final String SUCCESS_UPDATE = "Data was updated successfully";
    public static final String SUCCESS_DELETE = "Data was deleted successfully";
    public static final String SUCCESS_INSERT = "Data was added successfully";
    public static final String TITLE_DELETE = "Delete Option";
    public static final String FAILED_SEARCH = "Data was not found";
    public static final String NO_KEYWWORD = "Keyword was not defined";
    public static final String WELCOME_MESSAGE = "Welcome";
    public static final String LOGIN_FAILED = "Password Mismatch, Please Try Again";
    public static final String LOGIN_SUCCESS = "Welcome";
    
    public static <T> String SuccessText(T o){
        return o.getClass().getName();
    }
    
}
