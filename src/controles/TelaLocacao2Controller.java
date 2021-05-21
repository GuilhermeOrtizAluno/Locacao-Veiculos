/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Condutor;
import entidades.Locacao;
import entidades.Veiculo;
import entidades.enums.TipoTempo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import temp.LocacaoDao;

/**
 * FXML Controller class
 *
 * @author gui_o
 */
public class TelaLocacao2Controller implements Initializable {

    @FXML
    private Label lbMarca;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbAno;
    @FXML
    private Label lbPlaca;
    @FXML
    private Label lbRenavan;
    @FXML
    private Label lbKm;
    @FXML
    private Label lbSemana;
    @FXML
    private Label lbLocacao;
    @FXML
    private Label lbSeguro;
    @FXML
    private TextField tfAcrescimo;
    @FXML
    private Label lbTotal;
    @FXML
    private TextArea tfObs;
    @FXML
    private Button bConcluir;
    @FXML
    private ImageView ivCarro;
    
    private Locacao locacao;
    private Condutor condutor;
    private Veiculo veiculo;
    private TipoTempo tipoTempo;
    private Double total;
    private int qtde;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void inicializaDados(
        Veiculo veiculo,
        Condutor condutor,
        TipoTempo tipoTempo,
        int qtde
    ) {
        
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.tipoTempo = tipoTempo;
        this.qtde = qtde;
        
         preencheDados();
    }
    
    private void preencheDados(){
        double vLocacao = tipoTempo == TipoTempo.DIARIA ? veiculo.getDiaria() : 
            tipoTempo == TipoTempo.SEMANA ? veiculo.getSemanal() : 
            tipoTempo == TipoTempo.QUINZENA ? veiculo.getQuinzenal() :
            veiculo.getMensal();
        
        total = veiculo.getValorSeguro() + vLocacao;
         
        lbMarca.setText("Marca: "+veiculo.getMarca());
        lbModelo.setText("Modelo: "+veiculo.getModelo());
        lbAno.setText("Ano: "+veiculo.getAnoFabricacao());
        lbPlaca.setText("Nº Placa: "+veiculo.getPlaca());
        lbRenavan.setText("Renavan: "+ veiculo.getRenavam());
        lbKm.setText("Quilometragem: "+veiculo.getQuilometragemAtual());
        lbSemana.setText("Tempo de locação: "+veiculo.getSemanal());
        lbSeguro.setText("Valor do seguro: "+veiculo.getValorSeguro());
        //ivCarro.setImage(iCarro);
        lbLocacao.setText("Valor da locação: "+vLocacao);
        lbTotal.setText("Valor Total: "+ total);
    }
    
    private void createLocacao(String obs, String acrescimo){
        locacao = new Locacao(null, true,  veiculo.getDiaria(), 
            veiculo.getSemanal(), veiculo.getQuinzenal(), veiculo.getMensal(), 
            veiculo.getValorSeguro(), tipoTempo == TipoTempo.DIARIA ? qtde : 0, 
            tipoTempo == TipoTempo.SEMANA ? qtde : 0, tipoTempo == TipoTempo.QUINZENA ? qtde : 0, 
            tipoTempo == TipoTempo.MES ? qtde : 0, null);
        
        locacao.setAcresimo(Double.parseDouble(acrescimo));
        locacao.setTexto(obs);
        locacao.setIdCondutorLocacao(condutor);
        locacao.setIdVeiculo(veiculo);
        
        LocacaoDao locacaoDao = new LocacaoDao();
        locacaoDao.create(locacao);
    }

    @FXML
    private void hundleConcluir () {
        String sObs = tfObs.getText();
        String sAcrescimo = tfAcrescimo.getText();

        createLocacao(sObs, sAcrescimo);
    }
    
    @FXML
    private void verificaAcrescimo(){
        String acrescimo = tfAcrescimo.getText(); 
        
        double novoTotal = !acrescimo.isBlank() ? total + Double.parseDouble(acrescimo) : total; 
        lbTotal.setText("Valor Total: "+novoTotal);
    }
    
}
