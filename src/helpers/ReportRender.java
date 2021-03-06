/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import connection.Database;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mmohl
 */
public class ReportRender {
    
    private final String path = System.getProperty("user.dir");
    private final String fullPath;
    
    public ReportRender(String reportName) {
        fullPath = path + "/src/reports/" + reportName + ".jrxml";
    }
    
    public void makeReport() throws JRException {

        Map<String, Object> param = new HashMap<>();
        File file = new File (fullPath);
        JasperDesign jasperDesign = JRXmlLoader.load(file);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, Database.getConnection());
        JasperViewer.viewReport(jasperPrint, false);
        
    }
    
}
