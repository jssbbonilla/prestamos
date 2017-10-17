package managedbeansv3;

import entities.Bitacora;
import entities.Cliente;
import entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import javax.inject.Named;
import manager.ControladorUsuario;

@Named(value = "frmUsuarios")
@RequestScoped
public class FrmUsuario implements Serializable {

    private Usuario usuario = new Usuario();
    private ControladorUsuario userman = new ControladorUsuario();
    private List<Usuario> lsuarios = new ArrayList<Usuario>();
    private Usuario susario = new Usuario();

      public void initialize( ComponentSystemEvent cse ) throws IOException {
      if ( FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user") ) {
                   

      }else{
              FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

      }
   }
    
    
    public void login() throws IOException {
        usuario = userman.autenticar(usuario);
        if ((usuario) != null) {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Redirigiendo");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso denegado", "Usuario o contraseña incorrecta");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the lsuarios
     */
    public List<Usuario> getLsuarios() {
        this.lsuarios = userman.obtener();
        return lsuarios;
    }

    /**
     * @param lsuarios the lsuarios to set
     */
    public void setLsuarios(List<Usuario> lsuarios) {
        this.lsuarios = lsuarios;
    }

    /**
     * @return the susario
     */
    public Usuario getSusario() {
        return susario;
    }

    /**
     * @param susario the susario to set
     */
    public void setSusario(Usuario susario) {
        this.susario = susario;
    }

}
