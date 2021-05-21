/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Alerta;
import entidades.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaBuscaClienteController implements Initializable {

    private Cliente cliente;
    private ObservableList<Cliente> listaClientes;
    
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private Button btBuscar;
    @FXML
    private ListView<Cliente> lvClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaClientes = FXCollections.observableArrayList();
        lvClientes.setItems(listaClientes);
    }    

    /**
     *
     */
    void inicializaDados(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void buscaCliente(ActionEvent event) {
        listaClientes.clear();
        
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("LocacaoVeiculoPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Cliente> query = em.createNamedQuery(
            "Cliente.findByNome", Cliente.class
        ).setParameter( "nome", txtNomeCliente.getText() );
        
        ArrayList<Cliente> resultados =
            new ArrayList<>( query.getResultList() );
        
        if( !resultados.isEmpty() ) {
            listaClientes.addAll( resultados );
        }
        else {
            Alerta.mostraAlerta(
                "Usuário não encontrado!", "Verifique sua entrada e tente novamente"
            );
        }
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
    private void checaEntrada(KeyEvent event) {
        btBuscar.setDisable( txtNomeCliente.getText().isBlank() );
    }

    @FXML
    private void checaClienteSelecionado(MouseEvent event) {
        Cliente cliente = lvClientes.getSelectionModel().getSelectedItem();
        if(cliente != null) this.cliente = cliente;
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
