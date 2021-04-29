/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaEdicaoCliente1Controller implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private DatePicker dateNascimento;
    @FXML
    private TextField txtEndereco;
    @FXML
    private ComboBox<?> cbStatus;
    @FXML
    private RadioButton rbFisica;
    @FXML
    private ToggleGroup tgTipoPessoa;
    @FXML
    private RadioButton rbJuridica;
    @FXML
    private TextField txtRegistro;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtInscricaoEstadual;
    @FXML
    private TextArea txaObservacoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
