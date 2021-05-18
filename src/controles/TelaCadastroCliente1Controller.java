/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.TipoStatus;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class TelaCadastroCliente1Controller implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private DatePicker dateNascimento;
    @FXML
    private TextField txtEndereco;
    @FXML
    private ComboBox<TipoStatus> cbStatus;
    @FXML
    private ToggleGroup tgTipoPessoa;
    @FXML
    private TextField txtRegistro;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtInscricaoEstadual;
    @FXML
    private TextArea txtObservacoes;
    @FXML
    private RadioButton rbFisica;
    @FXML
    private RadioButton rbJuridica;
    @FXML
    private VBox root;

    @FXML
    private void mudarTelaInicial(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaInicial.fxml")
        );
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroCliente2(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaCadastroCliente2.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaCadastroCliente2Controller controller = fxmlLoader.getController();
        controller.inicializaDados();
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }
    
    private class MudaTipoCadastro implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t) {
            txtRegistro.clear();
            txtNomeFantasia.setDisable( rbFisica.isSelected() );
            txtNomeFantasia.clear();
            txtInscricaoEstadual.setDisable( rbFisica.isSelected() );
            txtInscricaoEstadual.clear();
            txtCpf.setDisable( rbJuridica.isSelected() );
            txtCpf.clear();
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbStatus.getItems().addAll( TipoStatus.values() );
        
        rbFisica.setOnAction(e -> {
            MudaTipoCadastro mtc = new MudaTipoCadastro();
            mtc.handle(e);
            txtRegistro.setPromptText("RG");
        });
        rbJuridica.setOnAction(e -> {
            MudaTipoCadastro mtc = new MudaTipoCadastro();
            mtc.handle(e);
            txtRegistro.setPromptText("CNPJ");
        });
    }    
    
    /**
     *
     */
    public void inicializaDados()
    {
    }
}
