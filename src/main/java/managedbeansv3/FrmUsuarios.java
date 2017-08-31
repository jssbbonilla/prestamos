package managedbeansv3;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import manager.ControladorCliente;


/**
 *
 * @author kevin
 */
@Named(value = "frmUsuaros")
@ViewScoped
public class FrmUsuarios implements Serializable {

    ControladorCliente ccliente = new ControladorCliente();
    private boolean busquedaActiva = true;
    

    public FrmUsuarios() {
    }

    public void activarBusqueda() {
        setBusquedaActiva(false);
        
    }
    public void desactivarBusqueda(){
        setBusquedaActiva(true);
    }

    /**
     * @return the busquedaActiva
     */
    public boolean isBusquedaActiva() {
        return busquedaActiva;
    }

    /**
     * @param busquedaActiva the busquedaActiva to set
     */
    public void setBusquedaActiva(boolean busquedaActiva) {
        this.busquedaActiva = busquedaActiva;
    }

}
