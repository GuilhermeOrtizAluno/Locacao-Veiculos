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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme Ortiz
 */
public class TelaCadastroCondutorController implements Initializable {

    @FXML
    private VBox root;

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
    private CheckBox cbA, 
                        cbB, 
                        cbC, 
                        cbD, 
                        cbE;
    
    @FXML
    private Button bCadastrar;
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Events">  
    @FXML
    private void hundleCadastrar() throws Exception{
        String sHab = tfHabilitacao.getText();
        String sEmail = tfEmail.getText();
        String sTel1 = tfTel1.getText();
        String sTel2 = tfTel2.getText();
         
        try { 
            checaEntradasInvalidas();
            createCondutor(
                sEmail, 
                sHab, 
                sTel1, 
                sTel2
            );
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
            (cbA.isSelected() || cbB.isSelected() || cbC.isSelected() || cbD.isSelected() || cbD.isSelected() || cbE.isSelected())
        ) {
            bCadastrar.setDisable(false);
        }
        else {
            bCadastrar.setDisable(true);
        }
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Functions">  
    public Tipohabilitacao createHabilitacao(Condutor condutor) throws Exception{
        
        Tipohabilitacao habilitacao = new Tipohabilitacao();
        habilitacao.setIdCondutorHabilitacao(condutor);
        habilitacao.setTipoA(cbA.isSelected());
        habilitacao.setTipoB(cbB.isSelected());
        habilitacao.setTipoC(cbC.isSelected());
        habilitacao.setTipoD(cbD.isSelected());
        habilitacao.setTipoE(cbE.isSelected());
        
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
        
        cliente.getCondutorCollection().add(condutor);
        listaCondutores.add(condutor);
        btCadastrarCliente.setDisable(false);
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
    public void checaEntradasInvalidas() throws EntradaInadequadaException
    {
        conferePadrao("^[0-9]{11}$", tfTel1, "O Telefone1 deve ter 11 dígitos!");
        if(!tfTel2.getText().isEmpty())conferePadrao("^[0-9]{11}$", tfTel2, "O Telefone2 deve ter 11 dígitos!");
        
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
    }  
}
