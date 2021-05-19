/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaCadastroCliente2Controller implements Initializable {

    @FXML
    private VBox root;
    
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
    *
    */
    public void inicializaDados(Cliente cliente)
    {
        this.cliente = cliente;
    }


    @FXML
    private void cadastrarCliente(ActionEvent event) {
    }


    @FXML
    private void editarCondutor(ActionEvent event) {
    }

    @FXML
    private void removerCondutor(ActionEvent event) {
    }

    @FXML
    private void mudarTelaCadastroCliente1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaCadastroCliente1.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaCadastroCliente1Controller controller = fxmlLoader.getController();
        controller.inicializaDados(cliente);
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroCondutor(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaCadastroCondutor.fxml")
        );
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }
}
