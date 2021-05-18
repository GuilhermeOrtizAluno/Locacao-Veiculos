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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaEdicaoCliente2Controller implements Initializable {

    @FXML
    private VBox root;


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
    void inicializaDados() {
    }

    @FXML
    private void mudarTelaEdicaoCliente1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaEdicaoCliente1.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaEdicaoCliente1Controller controller = fxmlLoader.getController();
        controller.inicializaDados();
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void editarCliente(ActionEvent event) {
    }

    @FXML
    private void cadastrarCondutor(ActionEvent event) {
    }

    @FXML
    private void editarCondutor(ActionEvent event) {
    }

    @FXML
    private void removerCondutor(ActionEvent event) {
    }
    
}
