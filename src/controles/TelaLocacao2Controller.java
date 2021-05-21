/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Cliente;
import entidades.Condutor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializaDados(
        String sMarca,
        String sModelo,
        String sAno,
        String sPlaca,
        String sRenavan,
        String sKm,
        String sSemana,
        String sLocacao,
        String sSeguro,
        String sTotal,
        Image iCarro
    ) {
        lbMarca.setText("Marca: "+sMarca);
        lbModelo.setText("Modelo: "+sModelo);
        lbAno.setText("Ano: "+sAno);
        lbPlaca.setText("Nº Placa: "+sPlaca);
        lbRenavan.setText("Renavan: "+sRenavan);
        lbKm.setText("Quilometragem: "+sKm);
        lbSemana.setText("Tempo de locação: "+sSemana);
        lbLocacao.setText("Valor da locação: "+sLocacao);
        lbSeguro.setText("Valor do seguro: "+sSeguro);
        lbTotal.setText("Valor Total: "+sTotal);
        ivCarro.setImage(iCarro);
    }

    @FXML
    private void hundleConcluir () {
        String sObs = tfObs.getText();
        String sAcrescimo = tfAcrescimo.getText();
    }
    
}
