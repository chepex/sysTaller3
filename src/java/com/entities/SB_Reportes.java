/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author mmixco
 */


@Stateless
public class SB_Reportes {
    
    
    public String saveReporte(String rep, HashMap params,int destino) {
        try {
            String reporPath = "";
            reporPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rep);
            Context ctx= new InitialContext();
            DataSource ds;
            //Desarrollo
            //ds = (DataSource)ctx.lookup("jdbc/desa");
            //Produccion 
            ds = (DataSource)ctx.lookup("jdbc/sysTaller");
            Connection cn = ds.getConnection();
            JasperPrint jasperprint = JasperFillManager.fillReport(reporPath, params, cn);
            HttpServletResponse httpserveltresponse= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpserveltresponse.setContentType("application/pdf");       
            /*ubicacion donde se guarda el archivo*/
            if (destino == 1) {
                OutputStream output = new FileOutputStream(new File("/opt/boleta.pdf")); 
                JasperExportManager.exportReportToPdfStream(jasperprint, output);                        
            } 
            if (destino == 2) {
                OutputStream output1 = new FileOutputStream(new File("/opt/OrdenCompra.pdf")); 
                JasperExportManager.exportReportToPdfStream(jasperprint, output1);                        
            }
        } catch (NamingException | SQLException | JRException | IOException ex) {
            System.out.println("Error"+ex);
            System.out.println("Error"+ex);
            System.out.println("Error"+ex);
        }
        return "";
    }
    
    public String GenerarReporte(String rep, HashMap params) {
        try {
            String reporPath = "";
            reporPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rep);
            Context ctx= new InitialContext();
            //LoginBean lb = new LoginBean();
            //String ip = lb.getServerIP();
            DataSource ds;
            //Desarrollo
            //ds = (DataSource)ctx.lookup("jdbc/desa");
            //Produccion 
            ds = (DataSource)ctx.lookup("jdbc/sysTaller");

            Connection cn = ds.getConnection();
            JasperPrint jasperprint = JasperFillManager.fillReport(reporPath, params, cn);
            HttpServletResponse httpserveltresponse= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpserveltresponse.setContentType("application/pdf");
            ServletOutputStream servletOutputStream = httpserveltresponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException | SQLException | JRException | IOException ex) {
            System.out.println("Error"+ex);
            System.out.println("Error"+ex);
            System.out.println("Error"+ex);
        }
        return "";
    }
    
    public String GenerarReporte2(String rep, HashMap params, int export) throws IOException {
        try {
            String reporPath = "";
            reporPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rep);
            Context ctx= new InitialContext();
            DataSource ds;

            //Desarrollo
            //ds = (DataSource)ctx.lookup("jdbc/desa");
            //Produccion 
            ds = (DataSource)ctx.lookup("jdbc/caricai");

            Connection cn = ds.getConnection();
            JasperPrint jasperprint = JasperFillManager.fillReport(reporPath, params, cn);

            HttpServletResponse httpserveltresponse= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = httpserveltresponse.getOutputStream();

            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperprint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            
            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
                    jasperprint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                    jasperprint.getName());
            
            exporter.exportReport();
            
            httpserveltresponse.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
            httpserveltresponse.setContentType("application/vnd.ms-excel");
            httpserveltresponse.setContentLength(arrayOutputStream.toByteArray().length);
            servletOutputStream.write(arrayOutputStream.toByteArray());
            servletOutputStream.flush();
            servletOutputStream.close();
            
            FacesContext.getCurrentInstance().responseComplete();
        
        } catch ( NamingException | SQLException | JRException ex) {
            System.out.println("Error"+ex);
        }
        return "";
    }
}
