/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeansv3;

import entities.Cliente;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import manager.ControladorCliente;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevin
 */
@Named(value = "frmClientes")
@ViewScoped
public class FrmClientes implements Serializable{

    ControladorCliente ccliente = new ControladorCliente();
    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    private List<Cliente> lcliente = new ArrayList<Cliente>();
    private Cliente scliente = new Cliente();
    private Cliente nuevoCliente = new Cliente();
    private String busqueda;
    private boolean busquedaActiva = false;
    private String imagenNuevo;
    MensajesFormularios mensaje = new MensajesFormularios(); //Mensajes de validacion
    MetodosShare metodo = new MetodosShare();
    ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
            .getExternalContext().getContext();
            String realPath = ctx.getRealPath("/");
    
    
    public void upload(FileUploadEvent event) throws IOException{
     if(event.getFile() != null) {
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            File destFile= new File(realPath+"resources/images/","somefile.png");
            
            FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), destFile);

       }
    }
            
            
    public FrmClientes() {
    }

    public void activarBusqueda() {
        busquedaActiva = true;
    }

    public void crearCliente() throws Exception {
        try {
            if (nuevoCliente.Validar()) {
                nuevoCliente.fechaNacimiento = metodo.utilDatetoSqlDate(nuevoCliente.getFechaNacimiento().toString());
                ccliente.agregar(nuevoCliente);
                nuevo();
                  try{
                     File fd = new File(realPath+"resources/images/","somefile.png");
                fd.delete();
                }catch(Exception e){}
            } else {
                mensaje.msgFaltanCampos();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    public void eliminarCliente() throws Exception {
        if (scliente.getDui().isEmpty() != true) {
            ccliente.eliminar(scliente);
            scliente = new Cliente();
        }
    }

    public void cambiarSeleccion(SelectEvent e) {
        this.scliente = (Cliente) e.getObject();

    }

    public void nuevo() {
        this.nuevoCliente = new Cliente();
    }

    public Cliente buscar(String esta) {
        return ccliente.buscar(esta).get(0);
    }

   
    
    /*-------------------------Getter and Setter----------------------------*/
    
    
    public List<Cliente> getLcliente() {
        if (busquedaActiva) {
            lcliente = ccliente.buscar(busqueda);

        } else {
            lcliente = ccliente.obtener();

        }
        return lcliente;
    }

    public void setLcliente(List<Cliente> lcliente) {
        this.lcliente = lcliente;
    }

    /**
     * @return the scliente
     */
    public Cliente getScliente() {
        return scliente;
    }

    /**
     * @param scliente the scliente to set
     */
    public void setScliente(Cliente scliente) {
        this.scliente = scliente;
    }

    /**
     * @return the busqueda
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     * @param busqueda the busqueda to set
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     * @return the nuevoCliente
     */
    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    /**
     * @param nuevoCliente the nuevoCliente to set
     */
    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public ControladorCliente getCcliente() {
        return ccliente;
    }

    public void setCcliente(ControladorCliente ccliente) {
        this.ccliente = ccliente;
    }

    public boolean isBusquedaActiva() {
        return busquedaActiva;
    }

    public void setBusquedaActiva(boolean busquedaActiva) {
        this.busquedaActiva = busquedaActiva;
    }

    /**
     * @return the imagenNuevo
     */
    public String getImagenNuevo() {
        File imgNuevo = new File(realPath+"resources/images/","somefile.png");
        if(imgNuevo.exists()){
            
           
        
            return "/images/somefile.png";
        
        }else{
        return "/images/hombre128x128.png";
        }
        
    }
  

    /**
     * @param imagenNuevo the imagenNuevo to set
     */
    public void setImagenNuevo(String imagenNuevo) {
        this.imagenNuevo = imagenNuevo;
    }
    

}
