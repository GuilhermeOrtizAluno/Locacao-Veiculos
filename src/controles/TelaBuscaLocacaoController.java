/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import DAO.LocacaoDAO;
import entidades.Alerta;
import entidades.Locacao;
import excecoes.EntradaInadequadaException;
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
    private LocacaoDAO locacaoDAO;
    private Locacao resultado;
    private ObservableList<Locacao> listaLocacoes;
    
    @FXML
    private AnchorPane root;
    private Button btbuscaLocacao;
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
    
//    private void checaEntradaInvalida() throws EntradaInadequadaException {
//        Pattern padrao;
//        Matcher matcher;
//        
//        padrao = Pattern.compile("^[0-9]+$");
//        matcher = padrao.matcher( txtBusca.getText() );
//        if( !matcher.find() ) throw new EntradaInadequadaException(
//            "O código é numérico e deve ter pelo menos um dígito"
//        );
//    }
    
    private void buscaLocacao() {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("LocacaoVeiculoPU");
        EntityManager em = emf.createEntityManager();
        
        String busca = txtBusca.getText();
        
        // Primeiro tento por código da locação
        try {
            TypedQuery<Locacao> query = em.createNamedQuery(
                "Locacao.findByIdLocacao", Locacao.class
            );
            int codigo = Integer.parseInt(busca);
            resultado =
                query.setParameter("idLocacao", codigo).getSingleResult();
            return;
        } catch (NoResultException | NumberFormatException e) {}
        
//        Query query = em.createQuery(
//                "SELECT * FROM locacao l INNER JOIN condutor co ON co.idCondutor = l.idCondutorLocacao INNER JOIN cliente cl ON cl.idCliente = co.idCliente WHERE cl.nome = :nomeCliente AND l.contratoAberto"
//            ).setParameter("nomeCliente", busca);
//        
//        // Se nao der, tentoo pelo nome do cliente
//        ArrayList<Locacao> lista = new ArrayList<>(
//            query.setParameter("nomeCliente", busca).getResultList()
//        );
        
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
