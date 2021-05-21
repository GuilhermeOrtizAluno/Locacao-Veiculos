/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Alerta;
import entidades.Veiculo;
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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaBuscaVeiculoController implements Initializable {

    private Veiculo veiculo;
    private ObservableList<Veiculo> listaVeiculos;
    
    @FXML
    private AnchorPane root;
    @FXML
    private Button btBuscar;
    @FXML
    private ListView<Veiculo> lvVeiculos;
    @FXML
    private TextField txtVeiculo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaVeiculos = FXCollections.observableArrayList();
        lvVeiculos.setItems(listaVeiculos);
    }    

    /**
     *
     */
    void inicializaDados(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @FXML
    private void buscaVeiculo(ActionEvent event) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("LocacaoVeiculoPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createNamedQuery(
            "Veiculo.findByPlaca", Veiculo.class
        ).setParameter( "placa", txtVeiculo.getText() );
        
        try {
            Veiculo resultado = query.getSingleResult();
            listaVeiculos.add(resultado);
        } catch (NoResultException e) {
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
        btBuscar.setDisable( txtVeiculo.getText().isBlank() );
    }

    @FXML
    private void checaVeiculoSelecionado(MouseEvent event) {
        Veiculo veiculo = lvVeiculos.getSelectionModel().getSelectedItem();
        if(veiculo != null) this.veiculo = veiculo;
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
