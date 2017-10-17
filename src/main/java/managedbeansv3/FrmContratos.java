
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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
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
@RequestScoped
public class FrmContratos implements Serializable{

    public FrmContratos() {
    }
    
    private String parametro1= "forma de pasar paramtros";

    public String getParametro1() {
        return parametro1;
    }

    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }
    
    private List<Contratos> datos = new ArrayList<Contratos>();

    public List<Contratos> getDatos() {
        
        Calendar cal = Calendar.getInstance();
	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        
        
        String primero=null;
        String segundo=null;
        String tercero=null;
        String cuarto= null;
        String cinco= null;
        String datosPersonales= null;
        String conveniente=null;
        
        String nombres="Walter Jacobo";
        String apellidos="Ramirez Lopez";
        String dui="98998882-8";
        String direccion= "Tes palos de coco avenida sur";
        String ciudad="Santa Ana";
        double monto=25660.33;
        String fechaInicio="12-05-2017";
        double interes= 0.5;
        String fechaFin="15-05-2020";
        String fechaActual=String.valueOf(cal.getTime());
        
        
        datosPersonales="Conste por el presente contrato Privado de Préstamo de Dinero que celebramos "
                + "de una parte la Señor/a. "+ nombres +", identificada con DUI. Nro. "+dui+","
                + "domiciliada en "+direccion+" de esta ciudad de "+ciudad+" a quien en adelante "
                + "sele denominara a quien en adelante se le denominará el PRESTATARIO, y de la otra parte "
                + "el PRESTAMISTA.";
        conveniente="Ambas partes llegan a los acuerdos siguientes:";
        
        primero= "LA PRESTAMISTA cede en calidad de PRESTAMO al PRESTATARIO la suma $"+monto+" a la "
                + "Señor/a "+nombres+" "+apellidos+" La de fecha del "+fechaInicio+".";
        segundo= "El prestatario acepta dicho dinero en calidad deprestamo y asegura haber recibido "
                + "el total del dinero a la firma del presente documento, por lo que se compromete "
                + "a devolver dicha suma de dinero, asimismo ambas partes acuerdan quedicho prestamo "
                + "generará el interes de "+interes+" por ciento.";
        tercero= "El capital prestado ha de devolverse en un plazo máximo de "+fechaFin+" años. No obstante"
                + "el restatario podrá amortizar de forma anticipada el capital pendiente en "
                + "cualquier momento. Los intereses se abonrán al efectuar el pago del capital en"
                + "cada uno de los plazos.";
        cuarto="En caso de incumplimiento de parte del PRESTATARIO A LA PRESTAMISTA queda facultada a"
                + " recurrir a las autoridades pertinentes y hacer valer sus derechos,"
                + " por loque el presente documento es suficiente medio probatorio y vale como RECIBO.";
        cinco= "Ambas partes señalan y aseguran que en lacelebración del mismo no ha mediado error, "
                + "dolo de nulidado anulabilidad que pudiera invalidar el contenido del mismo, por lo "
                + "que proceden a firmar en la ciudad de Santa Ana, fecha "+fechaActual+"";
        
        
        Contratos info = new Contratos();
        
        info.setDatosPersonales(datosPersonales);
        info.setPrimero(primero);
        info.setSegundo(segundo);
        info.setTercero(tercero);
        info.setCuarto(cuarto);
        info.setCinco(cinco);
        info.setConveniente(conveniente);
	info.setFecha(fechaActual);
	datos.add(info);
		
	return datos;
    }

    public void setDatos(List<Contratos> datos) {
        this.datos = datos;
    }

    
    
        public void verPDF() throws Exception{
                Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", parametro1);
            
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getDatos()));
		
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0 , bytes.length);
                
		outStream.flush();
		outStream.close();
			
		FacesContext.getCurrentInstance().responseComplete();
	}
    
    	public void exportarPDF() throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", parametro1);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getDatos()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=contrato-sis_prestamos.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarExcel() throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", parametro1);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getDatos()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=contrato-sis_prestamos.xlsx");
		ServletOutputStream stream = response.getOutputStream();
		
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarPPT() throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", parametro1);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getDatos()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=contrato-sis_prestamos.ppt");
		ServletOutputStream stream = response.getOutputStream();
		
		JRPptxExporter exporter = new JRPptxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void exportarDOC() throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", parametro1);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/rptContratos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getDatos()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=contrato-sis_prestamos.doc");
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
