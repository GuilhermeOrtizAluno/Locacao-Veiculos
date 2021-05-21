/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Cliente;
import entidades.Condutor;
import entidades.Veiculo;
import entidades.enums.TipoTempo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gui_o
 */
public class TelaLocacao1Controller implements Initializable {

    @FXML
    private ComboBox<Condutor> cbCondutor;
    @FXML
    private TextField tfQtde;
    @FXML
    private ComboBox<TipoTempo> cbSemana;
    @FXML
    private Button bProsseguir;
    @FXML
    private Button bCliente;
    @FXML
    private Button bVeiculo;
    
    private Cliente cliente;
    private Veiculo veiculo;
    private Condutor condutor;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbSemana.getItems().addAll(TipoTempo.values());
    }   
    
    public void inicializaDados(){
    }

    @FXML
    private void hundleProsseguir() throws IOException {
        String sCondutor = cbCondutor.getValue()== null ? "" : cbCondutor.getValue().toString();
        String sQtde = tfQtde.getText();
        TipoTempo tipoTempo = cbSemana.getValue();
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaLocacao2.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
        stage.setTitle("Cadastro Locação");
        
        TelaLocacao2Controller locacao2Controller = fxmlLoader.getController();
        locacao2Controller.inicializaDados(veiculo, condutor, tipoTempo, Integer.parseInt(sQtde));

    }

    @FXML
    private void hundleCliente() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaBuscaCliente.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaBuscaClienteController controller = fxmlLoader.getController();
        controller.inicializaDados(cliente, cbCondutor);
        
        Stage janela = new Stage();
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setScene( new Scene(parent) );
        janela.setTitle("Busca de Clientes");
        janela.show();
    }

    @FXML
    private void hundleVeiculo( ) {
    }
    
    @FXML
    private void checaCamposVazios(){
        if(
            cbCondutor.getValue() != null &&
            !tfQtde.getText().isBlank() 
        ) {
            bProsseguir.setDisable(false);
        }
        else {
            bProsseguir.setDisable(true);
        }
    }
}
