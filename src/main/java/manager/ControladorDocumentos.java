
package manager;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Conexion;


public class ControladorDocumentos extends Conexion implements Serializable {

    public int obtenerMaxId(String DUI) {
        int id = 0;
        ResultSet resultado;
        try {
            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT correlativo From documento WHERE dui='" + DUI + "'");
            
            if (resultado.first()) {
                resultado.last();
                id = resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        id = id + 1;
        return id;
    }
}
