/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author mmohl
 */
public class DbMaintenance {
    
    public static void Backupdbtosql() {
    try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        CodeSource codeSource = DbMaintenance.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();

        /*NOTE: Creating Database Constraints*/
        Process runtimeProcess;
        String dbName;
        String dbUser;
        String dbPass;
        String fileName;
        String savePath;
        
        dbName = "pvl_tb_kel";
        dbUser = "root";
        dbPass = null;
        Date date = Calendar.getInstance().getTime();
        savePath = System.getProperty("user.dir") + "/Backup/";
        fileName = "backup_" + (date.getYear() + 1900) + ":" + (date.getMonth() + 1) + ":" + date.getDate() + "_"
                + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + ".sql";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();
        
        String os = System.getProperty("os.name");
        String executeCmd1 = null;
        String executeCmd2 = null;
        
        switch (os) {
            case "Linux" :
                executeCmd1 = "/opt/lampp/bin/mysqldump -u" + dbUser + " -p" + dbPass + " -B '" + dbName + "' -r " + savePath + fileName;
                executeCmd2 = "/opt/lampp/bin/mysqldump -u " + dbUser + " -B " + dbName + " -r " + savePath + fileName;
                break;
            case "Windows" :
                executeCmd1 = "C:/xampp/bin/mysqldump -u" + dbUser + " -p" + dbPass + " -B '" + dbName + "' -r " + savePath + fileName;
                executeCmd2 = "C:/xampp/bin/mysqldump -u " + dbUser + " -B " + dbName + " -r " + savePath + fileName;
                break;
        }

        /*NOTE: Used to create a cmd command*/
        

        /*NOTE: Executing the command here*/

        runtimeProcess = Runtime.getRuntime().exec(executeCmd2);

        int processComplete = runtimeProcess.waitFor();
        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("Backup Complete");
            
        } else {
            System.out.println("Backup Failure");
        }
        

    } catch (URISyntaxException | IOException | InterruptedException ex) {
        JOptionPane.showMessageDialog(null, "Error at Backuprestore " + ex.getMessage());
    }
}
    
    public static void Restoredbfromsql(String s, String path) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = DbMaintenance.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "pvl_tb_kel";
             String dbUser = "root";
             String dbPass = "";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = null;
            String os = System.getProperty("os.name");
        
            switch (os) {
                case "Linux" :
                    executeCmd = new String[]{"/opt/lampp/bin/mysql", dbName, "-u" + dbUser, "-e", " source " + path};
                    break;
                case "Windows" :
                    executeCmd = new String[]{"C:/xampp/bin/mysql", dbName, "-u" + dbUser, "-e", " source " + path};
                    break;
            }

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql " + ex.getMessage());
        }

    }
    
}
