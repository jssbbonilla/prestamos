package manager;

import entities.Parametro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import managedbeansv3.MensajesFormularios;
import services.Conexion;


public class ControladorParametro extends Conexion implements Serializable{
    
    ErrorHandlerApp ep = new ErrorHandlerApp();
    Conexion cn = new Conexion();
    MensajesFormularios mensaje = new MensajesFormularios(); //Mensajes de validacion
    Parametro param = new Parametro();
    
    public List<Parametro> buscarPrametro(String s) {
        
        List<Parametro> parametros = new ArrayList<Parametro>();
        
        try {
            param = new Parametro();
            rst = getValores("SELECT * FROM prestamos.parametro WHERE id='" + s + "'");
            while (rst.next()) {
                param.setIdParametro(rst.getInt("id"));
                param.setNombre(rst.getString("nombre"));
                param.setValor(rst.getString("valor"));
                parametros.add(param);
            }
            return parametros;

        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            return null;
        } finally {
            closeconexion();
        }
    }
    
    public List<Parametro> obtenerParametros() {

        List<Parametro> parametros = new ArrayList<Parametro>();

        try {
            param = new Parametro();
            rst = getValores("SELECT * FROM parametro");
            while (rst.next()) {
                param = new Parametro();
                param.setIdParametro(rst.getInt("id"));
                param.setNombre(rst.getString("nombre"));
                param.setValor(rst.getString("valor"));
                parametros.add(param);
            }
            return parametros;

        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            return null;
        } finally {
            closeconexion();
        }
    }
    
    public void modificarParametro(Parametro p) {
        try {
            if (p == null) {
                System.err.println("Parametro invalido , verfique los datos");
                
            } else {
                UID("UPDATE prestamos.parametro SET nombre='"+p.nombre+"', valor='"+p.valor+"' WHERE id='"+p.idParametro+"'");
                mensaje.msgModificacion();
            }
        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            mensaje.msgErrorAlModificar();
        }
    }
    
}
