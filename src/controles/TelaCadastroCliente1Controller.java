/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import DAO.ClienteDAO;
import entidades.Alerta;
import entidades.Cliente;
import entidades.TipoCliente;
import entidades.TipoMotivoBloqueio;
import entidades.TipoStatus;
import excecoes.EntradaInadequadaException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.ChoiceDialog;
/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaCadastroCliente1Controller implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private DatePicker dateNascimento;
    @FXML
    private TextField txtEndereco;
    @FXML
    private ComboBox<TipoStatus> cbStatus;
    @FXML
    private ToggleGroup tgTipoPessoa;
    @FXML
    private TextField txtRegistro;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtInscricaoEstadual;
    @FXML
    private TextArea txtObservacoes;
    @FXML
    private RadioButton rbFisica;
    @FXML
    private RadioButton rbJuridica;
    @FXML
    private VBox root;
    @FXML
    private Button btAvancar;
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    @FXML
    private void mudarTelaInicial(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaInicial.fxml")
        );
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroCliente2(ActionEvent event)
        throws IOException
    {
        
        try {
            checaEntradasInvalidas();
            cadastraCliente();
        } catch (EntradaInadequadaException e) {
            Alerta.mostrarCampoInvalido( e.getMessage() );
            return;
        }
        catch (NullPointerException e) {
            return;
        }
        catch(Exception e) {
            Alerta.mostrarErroBanco();
            return;
        }
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaCadastroCliente2.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaCadastroCliente2Controller controller = fxmlLoader.getController();
        controller.inicializaDados(cliente);
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene( new Scene(parent) );
    }
    
    @FXML
    private void checaCamposVazios()
    {
        if(
            !txtNome.getText().isBlank() &&
            !dateNascimento.getEditor().getText().isEmpty() &&
            !txtEndereco.getText().isBlank() &&
            cbStatus.getValue() != null &&
            !txtRegistro.getText().isBlank() && (
                !txtCpf.getText().isBlank() ||
                !txtInscricaoEstadual.getText().isBlank() )
        ) {
            btAvancar.setText("Avançar");
            btAvancar.setDisable(false);
        }
        else {
            btAvancar.setText("Preencha os campos para avançar");
            btAvancar.setDisable(true);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbStatus.getItems().addAll( TipoStatus.values() );
        rbFisica.setUserData(TipoCliente.FISICA);
        rbFisica.setOnAction( new MudaTipoCadastro("RG (somente dígitos)") );
        rbJuridica.setUserData(TipoCliente.JURIDICA);
        rbJuridica.setOnAction( new MudaTipoCadastro("CNPJ (somente dígitos)") );
        
        clienteDAO = new ClienteDAO();
    }    
    
    private class MudaTipoCadastro implements EventHandler<ActionEvent> {
        
        private final String texto;

        public MudaTipoCadastro(String texto) {
            this.texto = texto;
        }
        
        @Override
        public void handle(ActionEvent t) {
            txtRegistro.clear();
            txtInscricaoEstadual.setDisable( rbFisica.isSelected() );
            txtInscricaoEstadual.clear();
            txtCpf.setDisable( rbJuridica.isSelected() );
            txtCpf.clear();
            txtRegistro.setPromptText(texto);
            checaCamposVazios();
        }
        
    }
    
    public void conferePadrao(String regex, TextField campo, String msgErro)
        throws EntradaInadequadaException
    {
        Pattern padrao;
        Matcher matcher;
        
        padrao = Pattern.compile(regex);
        matcher = padrao.matcher( campo.getText() );
        if( !matcher.find() ) throw new EntradaInadequadaException(msgErro);
    }
    
    public void checaEntradasInvalidas() throws EntradaInadequadaException
    {
        if( rbFisica.isSelected() )
        {
            conferePadrao("^[0-9]{8}[0-9]?$", txtRegistro, 
                "O RG deve ter 8 ou 9 dígitos!"
            );
            conferePadrao("^[0-9]{11}$", txtCpf, "O CPF deve ter 11 dígitos!");
        }
        else
        {
            conferePadrao("^[0-9]{10}$", txtRegistro,
                "O CNPJ deve ter 10 dígitos!"
            );
            conferePadrao("^[0-9]{14}$", txtInscricaoEstadual,
                "A inscrição estadual deve ter 14 dígitos!"
            );
        }
    }
    
    /**
     *
     * @param cliente
     */
    public void inicializaDados(Cliente cliente)
    {
        this.cliente = cliente;
        txtNome.setText( cliente.getNome() );
        dateNascimento.setValue(
            Instant.ofEpochMilli(
                cliente.getDataNascimento().getTime()
            ).atZone(ZoneId.systemDefault()).toLocalDate()
        );
        txtEndereco.setText( cliente.getEndereco() );
        cbStatus.setValue(
            TipoStatus.valueOf( cliente.getStatus().toUpperCase() )
        );

        if( cliente.getTipoCliente().equals("Física") ) {
            tgTipoPessoa.selectToggle(rbFisica);
            MudaTipoCadastro mtc = new MudaTipoCadastro("RG (somente dígitos)");
            mtc.handle(null);
            txtRegistro.setText( cliente.getRg() );
            txtCpf.setText( cliente.getCpf() );
           
        }
        else{
            tgTipoPessoa.selectToggle(rbJuridica );
            MudaTipoCadastro mtc = new MudaTipoCadastro("CNPJ (somente dígitos)");
            mtc.handle(null);
            txtRegistro.setText( cliente.getCnpj() );
            txtInscricaoEstadual.setText( cliente.getInscricaoEstadual() );
        }
        
        txtObservacoes.setText(
            cliente.getObservacao() != null ? cliente.getObservacao() : "" 
        );
        btAvancar.setText("Avançar");
        btAvancar.setDisable(false);
    }
    
    private TipoMotivoBloqueio chamarSelecaoMotivoBloqueio() {
        
        ChoiceDialog<TipoMotivoBloqueio> dialog = new ChoiceDialog<>(
            TipoMotivoBloqueio.INADIMPLÊNCIA, TipoMotivoBloqueio.values()
        );
        
        dialog.setTitle("Motivo do bloqueio");
        dialog.setHeaderText("Selecione o motivo do bloqueio:");
        dialog.showAndWait();
        
        return dialog.getResult();
    }
    
    private void cadastraCliente() throws Exception {
        
        TipoMotivoBloqueio motivoBloqueio = null;
        LocalDate data = dateNascimento.getValue();
        TipoCliente tipoCliente = (TipoCliente)
            ( (RadioButton) tgTipoPessoa.getSelectedToggle() ).getUserData();
        String observacoes = txtObservacoes.getText();
        
        if( cbStatus.getValue() == TipoStatus.BLOQUEADO )
        {
            motivoBloqueio = chamarSelecaoMotivoBloqueio();
            if(motivoBloqueio == null) throw new NullPointerException();
        }

        cliente = new Cliente();
        
        cliente.setNome( txtNome.getText() );
        cliente.setDataNascimento(
            Date.from(
                data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
            )
        );
        cliente.setEndereco( txtEndereco.getText() );
        cliente.setStatus( cbStatus.getValue().toString() );
        if(motivoBloqueio != null)
            cliente.setMotivoBloqueio(motivoBloqueio.tipo);
        
        cliente.setTipoCliente( tipoCliente.pessoa );
        if(tipoCliente == TipoCliente.FISICA) {
            cliente.setCpf( txtCpf.getText() );
            cliente.setRg( txtRegistro.getText() );
        }
        else {
            cliente.setCnpj(txtRegistro.getText() );
            cliente.setInscricaoEstadual( txtInscricaoEstadual.getText() );
        }
        
        if( !observacoes.isBlank() ) cliente.setObservacao( observacoes );
        
        clienteDAO.add(cliente);
    }
}
