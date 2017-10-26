package manager;

import entities.Cliente;
import entities.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import managedbeansv3.MensajesFormularios;
import services.Conexion;

public class ControladorUsuario extends Conexion implements Serializable {

    private ErrorHandlerApp ep = new ErrorHandlerApp();
    private Conexion cn = new Conexion();
    private Usuario user = new Usuario();
    private MensajesFormularios mensaje = new MensajesFormularios(); //Mensajes de validacion

    public Usuario autenticar(Usuario user) {

       
        
        try {
            rst = getValores("SELECT id_usuario,login,nombres,apellidos,rol FROM usuario WHERE login='" + user.getLogin() + "' AND clave=MD5('" + user.getClave() + "');");
            
        rst.next();
            this.user.setId(rst.getInt("id_usuario"));
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
    
    
     public void agregar(Usuario user) {

        try{
                PreparedStatement pstmt = conexion().prepareStatement("INSERT INTO usuario (login,nombres,apellidos,clave,rol) VALUES (?,?,?,MD5(?),?)");
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getNombre());
                pstmt.setString(3, user.getApellido());
                pstmt.setString(4, user.getClave());
                pstmt.setString(5, String.valueOf(user.getRol()));

                String sql = pstmt.toString();
                System.out.println("Succesful "+ sql);
                UID(pstmt);
                pstmt.close();
                
            mensaje.msgCreadoExito();
        } catch (Exception e) {
            ep.nuevo("Error", e.getStackTrace().toString(), e.getMessage());
            
            mensaje.msgErrorAlCrear();
        }

    }
  
    
    
}
