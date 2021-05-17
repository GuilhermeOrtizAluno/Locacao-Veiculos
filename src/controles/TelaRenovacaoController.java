package controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Luan Leme
 * @author Guilherme Ortiz
 */
public class TelaRenovacaoController implements Initializable {
    
    private String hab, email, tel1, tel2, tipo; 

    @FXML
    private TextField tfHabilitacao, 
                        tfEmail, 
                        tfTel1, 
                        tfTel2;
    
    @FXML ComboBox cbTipoHailitacao;
    
    @FXML Button bCadastrar;
    
    @FXML
    private void hundleCadastar(){
        String sHab = tfHabilitacao.getText();
        String sEmail = tfEmail.getText();
        String sTel1 = tfTel1.getText();
        String sTel2 = tfTel2.getText();
        String sTipoHab = cbTipoHailitacao.getValue() == null ? "" : cbTipoHailitacao.getValue().toString();
        
        if("".equals(sHab) || 
           "".equals(sEmail) ||
           "".equals(sTel1) ||
           "".equals(sTel2) ||
           "".equals(sTipoHab)
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
    }
    
    public void cleanFields(){
        tfHabilitacao.setText("");
        tfEmail.setText("");
        tfTel1.setText("");
        tfTel2.setText("");
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
    
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
