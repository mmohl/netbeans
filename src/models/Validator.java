/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.LengthValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mmohl
 */
abstract class Validator {
    
    protected String[] attributes = new String[] {};
    protected Map<String, String[]> beValidate = new HashMap<>();
    protected List<String> errorList = new ArrayList<>();
    boolean isValid;
    
    public boolean doValidation() {
          int i = 0;
          
          for (String attrib : attributes) {
              String[] x = beValidate.get(attrib);
              int minSize = Integer.parseInt(x[0]);
              int maxSize = Integer.parseInt(x[1]);
              String text = x[2];
              
              boolean isInvalid = new LengthValidator(minSize, maxSize, text).validate();
              
              if (text.isEmpty()) {
                  errorList.add(attrib + " must be filled!");
              }
              
              else if (isInvalid == false) {
                 errorList.add(attrib + " minimal " + minSize + " and maximal " + maxSize);
              }
          }
          
          isValid = errorList.isEmpty();
          
          return isValid;
    }
    
    protected void inisialisasi() {
        
    }
    
}
