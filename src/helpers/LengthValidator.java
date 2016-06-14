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
public class LengthValidator {
    
    private int maxSize = 0, minSize = 0;
    private String text = null;
    private boolean isValid;
    
    public LengthValidator(int minSize, int maxSize, String text) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.text = text;
    }
    
    public boolean validate() {
        int val = this.text.length();
        
        return isValid = (val >= this.minSize) && (val <= this.maxSize);
    }
    
}
