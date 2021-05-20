package controles;

import DAO.VeiculoDAO;
import excecoes.EntradaInadequadaException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gui_o
 */
public class TelaCadastroVeiculoController implements Initializable {
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Constructor">  
    public void inicializaDados() {

    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Variables">  
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAno;
    @FXML
    private TextField tfPlaca;
    @FXML
    private TextField tfRenavan;
    @FXML
    private TextField tfQuilometragem;
    @FXML
    private TextField tfImagem;
    @FXML
    private DatePicker dpManutencao;
    @FXML
    private TextField tfIndenizacao;
    @FXML
    private TextField tfExcedente;
    @FXML
    private TextField tfHora;
    @FXML
    private TextField tfDiaria;
    @FXML
    private TextField tfSemanal;
    @FXML
    private TextField tfQuinzenal;
    @FXML
    private TextField tfMensal;
    @FXML
    private TextArea tfInfo;
    @FXML
    private Button bCadastrar;
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Events">  
    
    @FXML
    private void hundleCadastrar() {
        
        String sMarca = tfMarca.getText();
        String sModelo = tfModelo.getText();
        String sAno = tfAno.getText();
        String sPlaca = tfPlaca.getText();
        String sRenavan = tfRenavan.getText();
        int sQuilometragem = Integer.parseInt(tfQuilometragem.getText());
        String sImagem = tfImagem.getText();
        LocalDate ldManutencao = dpManutencao.getValue();
        double sIndenizacao = Double.parseDouble(tfIndenizacao.getText());
        double sExcedente = Double.parseDouble(tfExcedente.getText());
        double sHora = Double.parseDouble(tfHora.getText());
        double sDiaria = Double.parseDouble(tfDiaria.getText());
        double sSemanal = Double.parseDouble(tfSemanal.getText());
        double sQuinzenal = Double.parseDouble(tfQuinzenal.getText());
        double sMensal = Double.parseDouble(tfMensal.getText());
        String sInfo = tfInfo.getText();
        
        try {
            checaEntradasInvalidas();
            createVeiculo(sRenavan, sDiaria, sSemanal, sQuinzenal, sMensal, sHora, sExcedente, sHora, sPlaca, sMarca, sModelo, sMarca, sIndenizacao, ldManutencao, sQuilometragem, sImagem, sInfo);
            cleanFields();
            bCadastrar.setDisable(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    @FXML
    private void checaCamposVazios(){
        if(
            !tfAno.getText().isBlank() &&
            !tfDiaria.getText().isBlank() &&
            !tfExcedente.getText().isBlank() &&
            !tfHora.getText().isBlank() &&
            !tfImagem.getText().isBlank() &&
            !tfIndenizacao.getText().isBlank() &&
            !tfInfo.getText().isBlank() &&
            !tfMarca.getText().isBlank() &&
            !tfMensal.getText().isBlank() &&
            !tfModelo.getText().isBlank() &&
            !tfPlaca.getText().isBlank() &&
            !tfQuilometragem.getText().isBlank() &&
            !tfQuinzenal.getText().isBlank() &&
            !tfRenavan.getText().isBlank() &&
            !tfSemanal.getText().isBlank() 
            
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
    public void createVeiculo(
        String renavam,
        double diaria,
        double semanal,
        double quinzenal,
        double mensal,
        double hora,
        double horaExcedente,
        double valorSeguro,
        String placa,
        String marca,
        String modelo,
        String anoFabricacao,
        double valorIndenizacao,
        LocalDate proximaManutencao,
        int quilometragemAtual,
        String imagem,
        String informacoesTecnicas
    ){
        // Veiculo veiculo = new Veiculo(null, renavam, diaria, semanal, quinzenal, mensal, hora, horaExcedente, 0, placa, marca, modelo, anoFabricacao, valorIndenizacao, Date.from(Instant.MIN), quilometragemAtual, informacoesTecnicas, imagem);
        
        //TODO
        VeiculoDAO veiculoDao = new VeiculoDAO();
        // veiculoDao.create(veiculo);
    }
    
    public void cleanFields(){
        tfMarca.setText("");
        tfModelo.setText("");
        tfAno.setText("");
        tfPlaca.setText("");
        tfRenavan.setText("");
        tfQuilometragem.setText("");
        tfImagem.setText("");
        //dpManutencao.setValue(LocalDate.MIN);
        tfIndenizacao.setText("");
        tfExcedente.setText("");
        tfHora.setText("");
        tfDiaria.setText("");
        tfSemanal.setText("");
        tfQuinzenal.setText("");
        tfMensal.setText("");
        tfInfo.setText("");
        bCadastrar.setText("");
    }
    
    public void initVariables(){
    }
    
    public void checaEntradasInvalidas() throws EntradaInadequadaException
    {
        //conferePadrao("^[0-9]{11}$", tfTel1, "O Telefone1 deve ter 11 dígitos!");
        //if(!tfTel2.getText().isEmpty())conferePadrao("^[0-9]{11}$", tfTel2, "O Telefone2 deve ter 11 dígitos!");
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
        // TODO
    }    
    
}
