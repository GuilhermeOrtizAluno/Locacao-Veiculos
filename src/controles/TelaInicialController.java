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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaInicialController implements Initializable {

    @FXML
    private BorderPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mudarTelaCadastroCliente1(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaCadastroCliente1.fxml")
        );
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaBuscaCliente(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaBuscaCliente.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaBuscaClienteController controller = fxmlLoader.getController();
        controller.inicializaDados();
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }
    
}
