/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileupload;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kevin
 */
@Named(value = "uploadBean")
@SessionScoped
public class UploadBean implements Serializable {

    private String url = "jdbc:mysql://localhost:3306/imagenes";
    private String login = "root";
    private String password = "12345";
//    private Connection cnx = null;

    private UploadedFile file;

    /*---------Setter and Getter----------------------*/
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /*----------------End-----------------------------------*/
    public UploadBean() {
    }

    public void upload() {

        try {
            if (file != null) {
                Class.forName("com.mysql.jdbc.Dirver");
                Connection cn = DriverManager.getConnection(url, login, password);

                PreparedStatement st = cn.prepareStatement("INSERT INTO imagenes.imagen (img) VALUES (?)");
                st.setBinaryStream(1, file.getInputstream());

                st.executeUpdate();
                cn.close();

                FacesMessage message = new FacesMessage("Exito", file.getFileName() + " fue subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Error de conexion.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
