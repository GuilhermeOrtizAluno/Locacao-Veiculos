package controles;

import entidades.Locacao;
import entidades.Veiculo;
import entidades.enums.TipoTempo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luan Leme
 * @author Guilherme Ortiz
 */
public class TelaRenovacaoController implements Initializable {

    Locacao locacao;
    Pattern padrao;
    
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbCarro;
    @FXML
    private Label lbDataEmprestimo;
    @FXML
    private Label lbDataVencimento;
    @FXML
    private Label lbTempoLocacao;
    @FXML
    private Label lbCustos;
    @FXML
    private Label lbTotal;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ComboBox<TipoTempo> cbTempos;
    @FXML
    private Button btRenovar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        padrao = Pattern.compile("^[0-9]+$");
    }    

    void inicializaDados(Locacao locacao) {
        this.locacao = locacao;
        Veiculo veiculo = locacao.getIdVeiculo();
        lbCarro.setText(
            "Carro: " + veiculo.getMarca() + " " + veiculo.getModelo() + " " +
            veiculo.getPlaca().toUpperCase()
        );
        cbTempos.getItems().addAll( TipoTempo.values() );
    }
    
    @FXML
    private void checaEntradas() {
        boolean quantidade = padrao.matcher( txtQuantidade.getText() ).find();
        TipoTempo tempo = cbTempos.getSelectionModel().getSelectedItem();
        btRenovar.setDisable(!quantidade || tempo == null);
    }

    @FXML
    private void mudarTelaBuscaLocacao(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaBuscaLocacao.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaBuscaLocacaoController controller = fxmlLoader.getController();
        controller.inicializaDados(true);
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Busca de contrato");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void renovar(ActionEvent event) {
    }
}
