/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import DAO.CondutorDAO;
import DAO.TipoHabilitacaoDAO;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Cliente;
import entidades.Condutor;
import entidades.Tipohabilitacao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaCadastroCliente2Controller implements Initializable {

    private Cliente cliente;
    private ObservableList<Condutor> listaCondutores;
    private CondutorDAO condutorDAO;
    private Condutor condutorSelecionado;
    TipoHabilitacaoDAO tipoHabilitacaoDAO;
    
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
        tipoHabilitacaoDAO = new TipoHabilitacaoDAO();
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
    private void mudarTelaInicial(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load( 
            getClass().getClassLoader().getResource("telas/TelaInicial.fxml") 
        );
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Menu Principal");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void editarCondutor(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void removerCondutor(ActionEvent event) {
        Object[] habilitacao = condutorSelecionado.getTipohabilitacaoCollection().toArray();
        listaCondutores.remove(condutorSelecionado);
        try {
            tipoHabilitacaoDAO.remove(
                ((Tipohabilitacao) habilitacao[0]).getIdTipoHabilitacao()
            );
            condutorDAO.remove( condutorSelecionado.getIdCondutor() );
        } catch (NonexistentEntityException e) {
            return;
        }
        lvCondutores.getSelectionModel().clearSelection();
        condutorSelecionado = null;
        
        btEditarCondutor.setDisable(true);
        btRemoverCondutor.setDisable(true);
        if( listaCondutores.isEmpty() ) btCadastrarCliente.setDisable(true);
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
        stage.setTitle("Cadastro do cliente");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroCondutor(ActionEvent event)
        throws IOException, NonexistentEntityException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaCadastroCondutor.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaCadastroCondutorController controller = fxmlLoader.getController();
        controller.inicializaDados(cliente, listaCondutores, btCadastrarCliente);
        
        Stage janela = new Stage();
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setScene( new Scene(parent) );
        janela.setTitle("Cadastro do condutor");
        janela.show();
    }

    @FXML
    private void checaCondutorSelecionado(MouseEvent event) {
        condutorSelecionado =
            lvCondutores.getSelectionModel().getSelectedItem();
        
        btEditarCondutor.setDisable(condutorSelecionado == null);
        btRemoverCondutor.setDisable(condutorSelecionado == null);
    }
}
