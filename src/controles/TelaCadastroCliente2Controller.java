/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import DAO.ClienteDAO;
import DAO.CondutorDAO;
import DAO.TipoHabilitacaoDAO;
import entidades.Alerta;
import entidades.Cliente;
import entidades.Condutor;
import entidades.Tipohabilitacao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaCadastroCliente2Controller implements Initializable {

    private Cliente cliente;
    private CondutorDAO condutorDAO;
    private Condutor condutorSelecionado;
    private ObservableList<Condutor> listaCondutores;
    
    @FXML
    private VBox root;   
    @FXML
    private ListView<Condutor> lvCondutores;
    @FXML
    private Button btCadastrarCliente;
    @FXML
    private Button btEditarCondutor;
    @FXML
    private Button btRemoverCondutor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        condutorDAO = new CondutorDAO();
    }    
    
    /**
    *
    */
    public void inicializaDados(Cliente cliente)
    {
        this.cliente = cliente;
        listaCondutores = FXCollections.observableArrayList();
        lvCondutores.setItems(listaCondutores);
    }

    @FXML
    private void cadastrarCliente(ActionEvent event) throws Exception {
//        try {
//            for (Condutor condutor : listaCondutores) condutorDAO.add(condutor);
//        } catch (Exception e) {
//            Alerta.mostrarCampoInvalido( e.getMessage() );
//            return;
//        }
        cliente.setCondutorCollection(listaCondutores);
        for (Condutor condutor : listaCondutores) {
            condutorDAO.add(condutor);
        }
        System.out.println("Terminou o cadastro do cliente");
    }


    @FXML
    private void editarCondutor(ActionEvent event) {
    }

    @FXML
    private void removerCondutor(ActionEvent event) {
        listaCondutores.remove(condutorSelecionado);
        lvCondutores.getSelectionModel().clearSelection();
        condutorSelecionado = null;
        
        btEditarCondutor.setDisable(true);
        btRemoverCondutor.setDisable(true);
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
    private void mudarTelaCadastroCondutor(ActionEvent event)
        throws IOException, Exception {
//        Parent parent = FXMLLoader.load(
//            getClass().getResource("/./telas/TelaCadastroCondutor.fxml")
//        );
//        
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene( new Scene(parent) );
        TipoHabilitacaoDAO tipoHabilitacaoDAO = new TipoHabilitacaoDAO();
        Tipohabilitacao habilitacao = new Tipohabilitacao();
        habilitacao.setTipoA(true);
        tipoHabilitacaoDAO.add(habilitacao);
        
        Condutor condutor = new Condutor(
            null, "12345678910", "12345678900", "jooj");
        condutor.setIdCliente(cliente);
        condutor.setIdTipoHabilitacao(habilitacao);
        
        listaCondutores.add(condutor);
        btCadastrarCliente.setDisable(false);
    }

    @FXML
    private void checaCondutorSelecionado(MouseEvent event) {
        condutorSelecionado =
            lvCondutores.getSelectionModel().getSelectedItem();
        
        btEditarCondutor.setDisable(condutorSelecionado == null);
        btRemoverCondutor.setDisable(condutorSelecionado == null);
    }
}
