package manager;

import entities.Cliente;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import managedbeansv3.MensajesFormularios;
import services.Conexion;

public class ControladorUsuario extends Conexion implements Serializable {

    private ErrorHandlerApp ep = new ErrorHandlerApp();
    private Conexion cn = new Conexion();
    private Usuario user = new Usuario();
    private MensajesFormularios mensaje = new MensajesFormularios(); //Mensajes de validacion

    public Usuario autenticar(Usuario user) {

       
        
        try {
            rst = getValores("SELECT login,nombres,apellidos,rol FROM usuario WHERE login='" + user.getLogin() + "' AND clave=MD5('" + user.getClave() + "');");
            
        rst.next();
              
            this.user.setLogin(rst.getString("login"));
            this.user.setApellido(rst.getString("apellidos"));
            this.user.setNombre(rst.getString("nombres"));
            this.user.setRol(rst.getString("rol").charAt(0));
            return this.user;
            
         
            
        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            return null;
        } finally {
            closeconexion();
        }

        
    }
    
    public List<Usuario> obtener() {
        List<Usuario> clientes = new ArrayList<Usuario>();
        try {
            Usuario usuario = new Usuario();
            rst = getValores("SELECT * FROM usuario");
            while (rst.next()) {
                usuario = new Usuario();
                usuario.setApellido(rst.getString("apellidos"));
                usuario.setNombre(rst.getString("nombres"));
                usuario.setRol(rst.getString("rol").charAt(0));
                usuario.setLogin(rst.getString("login"));
                usuario.setId(rst.getInt("id_usuario"));
                clientes.add(usuario);
            }
            return clientes;
        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            return null;
        } finally {
            closeconexion();
        }

    }
}
