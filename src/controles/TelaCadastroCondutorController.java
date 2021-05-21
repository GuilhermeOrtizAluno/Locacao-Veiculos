package controles;

import DAO.CondutorDAO;
import DAO.TipoHabilitacaoDAO;
import entidades.Alerta;
import entidades.Cliente;
import entidades.Condutor;
import entidades.Tipohabilitacao;
import excecoes.EntradaInadequadaException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Guilherme Ortiz
 */
public class TelaCadastroCondutorController implements Initializable {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Constructor">  
    public void inicializaDados(Cliente cliente, ObservableList<Condutor> listaCondutores, Button btCadastrarCliente) {
        this.cliente = cliente;
        this.listaCondutores = listaCondutores;
        this.btCadastrarCliente = btCadastrarCliente;
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Variables">  
    private Cliente cliente;
    private ObservableList<Condutor> listaCondutores;
    private Button btCadastrarCliente;

    @FXML
    private TextField tfHabilitacao,
                        tfEmail, 
                        tfTel1, 
                        tfTel2;
    
    @FXML 
    private ComboBox cbTipoHailitacao;
    
    @FXML
    private Button bCadastrar,
                    bVoltar;
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Events">  
    @FXML
    private void hundleCadastrar() throws Exception{
        String sHab = tfHabilitacao.getText();
        String sEmail = tfEmail.getText();
        String sTel1 = tfTel1.getText();
        String sTel2 = tfTel2.getText();
        //TODO
        String sTipoHab = cbTipoHailitacao.getValue() == null ? "" : cbTipoHailitacao.getValue().toString();
         
        try { 
            checaEntradasInvalidas();
            createCondutor(
                sEmail, 
                sHab, 
                sTel1, 
                sTel2
            );
            cleanFields();
        } catch(EntradaInadequadaException e) {
            Alerta.mostrarCampoInvalido( e.getMessage() );
        }
        catch (Exception e) {
            Alerta.mostrarErroBanco();
        }
        
    }
    
    @FXML
    private void checaCamposVazios(){
        if(
            !tfEmail.getText().isBlank() &&
            !tfHabilitacao.getText().isBlank() &&
            !tfTel1.getText().isBlank() &&
            !tfTel2.getText().isBlank()/*&&
            !cbTipoHailitacao.getEditor().getText().isEmpty()*/
        ) {
            bCadastrar.setDisable(false);
        }
        else {
            bCadastrar.setDisable(true);
        }
    }
    
    @FXML
    private void hundleVoltar(ActionEvent event) {
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Functions">  
    public Tipohabilitacao createHabilitacao(Condutor condutor) throws Exception{
        
        Tipohabilitacao habilitacao = new Tipohabilitacao();
        habilitacao.setIdCondutorHabilitacao(condutor);
        habilitacao.setTipoA(true);
        
        TipoHabilitacaoDAO tipoHabilitacaoDAO = new TipoHabilitacaoDAO();
        tipoHabilitacaoDAO.add(habilitacao);
        
        return habilitacao;
    }
    
    public void createCondutor(String email, String hab, String tel1, String tel2){
        Condutor condutor = new Condutor();
        condutor.setEmail(email);
        condutor.setNumeroHabilitacao(hab);
        condutor.setTelefone1(tel1);
        condutor.setTelefone2(tel2);
        condutor.setIdCliente(cliente);
        
        CondutorDAO condutorDAO = new CondutorDAO();
        try {
            condutorDAO.add(condutor);
            condutor.getTipohabilitacaoCollection().add( createHabilitacao(condutor) );
        } catch (Exception ex) {
            Alerta.mostrarErroBanco();
            return;
        }
        
        listaCondutores.add(condutor);
        btCadastrarCliente.setDisable(false);
    }
    
    public void cleanFields(){
        tfHabilitacao.setText("");
        tfEmail.setText("");
        tfTel1.setText("");
        tfTel2.setText("");
    }
    
    public void initVariables(){
        cbTipoHailitacao.getItems().addAll(
            "A",
            "B",
            "C",
            "D",
            "E" 
        );
    }
    
    public void checaEntradasInvalidas() throws EntradaInadequadaException
    {
        conferePadrao("^[0-9]{11}$", tfTel1, "O Telefone1 deve ter 11 dígitos!");
        if(!tfTel2.getText().isEmpty())conferePadrao("^[0-9]{11}$", tfTel2, "O Telefone2 deve ter 11 dígitos!");
        //TODO
        //conferePadrao("^[0-9]{11}$", txtCpf, "O CPF deve ter 11 dígitos!");
        
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
    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initVariables();
    }  
}
