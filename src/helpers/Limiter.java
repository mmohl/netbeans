/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author mmohl
 */
public class Limiter {
    byte length;
    PlainDocument filter;
    
    public Limiter(byte length) {
        this.length = length;
    }
    
    public PlainDocument getFilter(final JTextField inputan) {
        filter = new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                StringBuffer buff = new StringBuffer();
                int c = 0;
                char[] upp = str.toCharArray();
                
                for (int i = 0; i < upp.length; i++) {
                    upp[i] = Character.valueOf(upp[i]);
                        boolean isOnlyAngka = Character.isDigit(upp[i]);
                        boolean isOnlyLetter = Character.isLetter(upp[i]);
                        boolean isOnlySpasi = Character.isSpaceChar(upp[i]);
                        
                        if (isOnlyLetter == true) {
                            upp[c] = upp[i];
                            c++;
                        } else if (isOnlyAngka == true) {
                            upp[c] = upp[i];
                            c++;
                        } else {
                            upp[c] = upp[i];
                            c++;
                        }
                }
                buff.append(upp, 0, c);
                int x = inputan.getText().length();
                if (x < length) {
                    super.insertString(offs, new String(buff), a);
                }
            }
        };
        return filter;
    }
    
    public PlainDocument getOnlyAngka(final JTextField inputan) {
        filter = new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                StringBuffer buff = new StringBuffer();
                int c = 0;
                char[] upp = str.toCharArray();
                
                for (int i = 0; i < upp.length; i++) {
                    upp[i] = Character.valueOf(upp[i]);
                        boolean isOnlyAngka = Character.isDigit(upp[i]);
                        if (isOnlyAngka == true) {
                            upp[c] = upp[i];
                            c++;
                        }
                }
                
                buff.append(upp, 0, c);
                int x = inputan.getText().length();
                if (x < length) {
                    super.insertString(offs, new String(buff), a);
                }
            }
        };
        return filter;
    }
    
    public PlainDocument getKata(final JTextField inputan) {
        filter = new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                StringBuffer buff = new StringBuffer();
                int c = 0;
                char[] upp = str.toCharArray();
                
                for (int i = 0; i < upp.length; i++) {
                    upp[i] = Character.valueOf(upp[i]);
                        boolean isOnlyAngka = Character.isDigit(upp[i]);
                        boolean isOnlyLetter = Character.isLetter(upp[i]);
                        boolean isOnlySpasi = Character.isSpaceChar(upp[i]);
                        
                        if (isOnlyLetter == true) {
                            upp[c] = upp[i];
                            c++;
                        } else if (isOnlyAngka == true) {
                            upp[c] = upp[i];
                            c++;
                        } else {
                            upp[c] = upp[i];
                            c++;
                        }
                }
                buff.append(upp, 0, c);
                int x = inputan.getText().length();
                if (x < length) {
                    super.insertString(offs, new String(buff).replace("'", "").replaceAll("\\\\", ""), a);
                }
            }
        };
        return filter;
    }
    
    
}
