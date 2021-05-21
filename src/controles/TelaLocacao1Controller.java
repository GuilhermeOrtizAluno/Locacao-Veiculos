/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gui_o
 */
public class TelaLocacao1Controller implements Initializable {

    @FXML
    private ComboBox<?> cbCondutor;
    @FXML
    private TextField tfVeiculo;
    @FXML
    private TextField tdQtde;
    @FXML
    private ComboBox<?> cbSemana;
    @FXML
    private Button bProsseguir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hundleProsseguir(ActionEvent event) {
        
    }
    
}
