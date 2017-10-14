
package entities;

import java.io.Serializable;
import java.util.Date;

public class Contratos implements Serializable{
    
   private String nombres;
   private String apellidos;
   private String fecha;

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
   
   
}