/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private VBox root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
    *
    */
    public void inicializaDados()
    {
    }



    @FXML
    private void mudarTelaInicial(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaInicial.fxml")
        );
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaEdicaoCliente2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaEdicaoCliente2.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaEdicaoCliente2Controller controller = fxmlLoader.getController();
        controller.inicializaDados();
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }
    
}
