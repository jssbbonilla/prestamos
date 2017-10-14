
package managedbeansv3;

import entities.Contratos;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

@Named(value = "frmContratos")
@ViewScoped
public class FrmContratos implements Serializable{

    public FrmContratos() {
    }
    
    private List<Contratos> personas = new ArrayList<Contratos>();

	public List<Contratos> getPersonas() {
		Contratos per = new Contratos();
		per.setNombres("Kevin Vicente");
		per.setApellidos("Martinez");
		Calendar cal = Calendar.getInstance();
		cal.set(1991, 1, 21);
		per.setFecha(String.valueOf(cal.getTime()));
		personas.add(per);
		
		per = new Contratos();
		per.setNombres("Walter");
		per.setApellidos("Ramirez");
		cal = Calendar.getInstance();
		cal.set(1990, 5, 21);
		per.setFecha(String.valueOf(cal.getTime()));
		personas.add(per);
		
		return personas;
	}

    public void setPersonas(List<Contratos> personas) {
        this.personas = personas;
    }
    
        public void verPDF(ActionEvent actionEvent) throws Exception{
                Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Cacheton");
            
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getPersonas()));
		
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0 , bytes.length);
                
		outStream.flush();
		outStream.close();
			
		FacesContext.getCurrentInstance().responseComplete();
	}
    
    	public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Cacheton");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getPersonas()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Cacheton");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getPersonas()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.xlsx");
		ServletOutputStream stream = response.getOutputStream();
		
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarPPT(ActionEvent actionEvent) throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Cacheton");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getPersonas()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.ppt");
		ServletOutputStream stream = response.getOutputStream();
		
		JRPptxExporter exporter = new JRPptxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarDOC(ActionEvent actionEvent) throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Cacheton");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getPersonas()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.doc");
		ServletOutputStream stream = response.getOutputStream();
		
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
}
