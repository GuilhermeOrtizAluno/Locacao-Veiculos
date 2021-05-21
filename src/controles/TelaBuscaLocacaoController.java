/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Alerta;
import entidades.Locacao;
import excecoes.EntradaInadequadaException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
public class TelaBuscaLocacaoController implements Initializable {

    private boolean isRenovar;
    private Locacao resultado;
    private ObservableList<Locacao> listaLocacoes;
    
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtBusca;
    @FXML
    private ListView<Locacao> lvLocacoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultado = null;
        listaLocacoes = FXCollections.observableArrayList();
        lvLocacoes.setItems(listaLocacoes);
    }    

    public void inicializaDados(boolean isRenovar) {
        this.isRenovar = isRenovar;
    }
    
    /**
     * Verifica se a entrada é numérica e não nula.
     */
    private void checaEntradaInvalida() throws EntradaInadequadaException {
        Pattern padrao;
        Matcher matcher;
        
        padrao = Pattern.compile("^[0-9]+$");
        matcher = padrao.matcher( txtBusca.getText() );
        if( !matcher.find() ) throw new EntradaInadequadaException(
            "O código é numérico e deve ter pelo menos um dígito"
        );
    }
    
    /**
     * Busca no banco de dados a locação com base no código recebido do usuário.
     */
    private void buscaLocacao() {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("LocacaoVeiculoPU");
        EntityManager em = emf.createEntityManager();
        
        String busca = txtBusca.getText();
        
        try {
            checaEntradaInvalida();
            TypedQuery<Locacao> query = em.createNamedQuery(
                "Locacao.findByIdLocacao", Locacao.class
            );
            int codigo = Integer.parseInt(busca);
            resultado =
                query.setParameter("idLocacao", codigo).getSingleResult();
            return;
        } catch(NoResultException e) {
            
        } catch(EntradaInadequadaException e) {
            Alerta.mostrarCampoInvalido( e.getMessage() );
            return;
        }

        Alerta.mostraAlerta(
            "Usuário não encontrado!", "Verifique sua entrada e tente novamente"
        );
    }

    @FXML
    private void mudarTelaInicial(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaInicial.fxml")
        );
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Menu Principal");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTela(ActionEvent event) throws IOException {
        
        buscaLocacao();
        if(resultado == null) return;
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource(
                "/./telas/" + (isRenovar ? "TelaRenovacao" : "TelaDevolucao")
                + ".fxml"
            )
        );
        Parent parent = fxmlLoader.load();
        
        if(isRenovar) {
            ( (TelaRenovacaoController) fxmlLoader.getController() 
            ).inicializaDados(resultado);
        }
        else {
            ( (TelaDevolucaoController) fxmlLoader.getController() 
            ).inicializaDados();
        }
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle(isRenovar ? "Renovação de contrato" : "Devolução");
        stage.setScene( new Scene(parent) );
    }

    
}
