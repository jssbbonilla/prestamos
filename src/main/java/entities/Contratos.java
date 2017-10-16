
package entities;

import java.io.Serializable;
import java.util.Date;

public class Contratos implements Serializable{
    
   private String nombres;
   private String apellidos;
   private String fecha;
   private String primero;
   private String segundo;
   private String tercero;
   private String cuarto;
   private String cinco;
   private String datosPersonales;
   private String conveniente;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrimero() {
        return primero;
    }

    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getTercero() {
        return tercero;
    }

    public void setTercero(String tercero) {
        this.tercero = tercero;
    }

    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public String getCinco() {
        return cinco;
    }

    public void setCinco(String cinco) {
        this.cinco = cinco;
    }

    public String getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(String datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public String getConveniente() {
        return conveniente;
    }

    public void setConveniente(String conveniente) {
        this.conveniente = conveniente;
    }
    
    
   
}