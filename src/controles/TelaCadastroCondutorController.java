/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import temp.CondutorDAO;
import temp.ConnectionFactory;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaCadastroCondutorController implements Initializable {

    public TelaCadastroCondutorController() {
    }

    private String hab, email, tel1, tel2, tipo; 

    @FXML
    private TextField tfHabilitacao,
                        tfEmail, 
                        tfTel1, 
                        tfTel2;
    
    @FXML 
    private ComboBox cbTipoHailitacao;
    
    @FXML
    private Button bCadastrar;
    
    @FXML
    private void hundleCadastrar(){
        String sHab = tfHabilitacao.getText();
        String sEmail = tfEmail.getText();
        String sTel1 = tfTel1.getText();
        String sTel2 = tfTel2.getText();
        String sTipoHab = cbTipoHailitacao.getValue() == null ? "" : cbTipoHailitacao.getValue().toString();
        
        Connection con = ConnectionFactory.getConnection();
        
        if(con == null) {
            JOptionPane.showMessageDialog(
                null, 
                "Error when trying to connect to the database", 
                "Error SQL", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if("".equals(sHab) || 
           "".equals(sEmail) ||
           "".equals(sTel1) ||
           "".equals(sTel2) //||
           //"".equals(sTipoHab)
        ){
            JOptionPane.showMessageDialog(
                null, 
                "Invalid fields, please try again", 
                "Invalid field", 
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        hab = sHab;
        email = sEmail;
        tel1 = sTel1;
        tel2 = sTel2;
        tipo = sTipoHab;
        
        CondutorDAO condutorDAO = new CondutorDAO();
        condutorDAO.create(hab, email, tel1, tel2, tipo);
    }
    
        public String getHab() {
        return hab;
    }

    public String getEmail() {
        return email;
    }

    public String getTel1() {
        return tel1;
    }

    public String getTel2() {
        return tel2;
    }
    
    public void cleanFields(){
        tfHabilitacao.setText("");
        tfEmail.setText("");
        tfTel1.setText("");
        tfTel2.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
