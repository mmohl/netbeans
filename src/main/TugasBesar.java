/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.FormLogin;

/**
 *
 * @author mmohl
 */
public class TugasBesar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4500);
                } catch (Exception e) {
                }
            }
        });
        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FormLogin form = new FormLogin();
                    form.setLocationRelativeTo(null);
                    form.setVisible(true);
                }
            });
        } catch (UnsupportedLookAndFeelException e) {
            Logger.getLogger(TugasBesar.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
