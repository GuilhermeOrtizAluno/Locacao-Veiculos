/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.enums.TipoFiltragem;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanl
 */
public class TelaInicialController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<TipoFiltragem> cbFiltrarGráfico;
    @FXML
    private BarChart<CategoryAxis, NumberAxis> grafico;
    
    /**
     * Adiciona ao gráfico os dados corretos, dependendo da filtragem escolhida.
     * @param tipo da filtragem
     */
    public void filtrarPor(TipoFiltragem tipo) {
        
        ObservableList<XYChart.Data> listaDados;
        
        if(cbFiltrarGráfico.getValue() == TipoFiltragem.QUANTIDADE) {
            listaDados =
            FXCollections.observableArrayList(
                new XYChart.Data("Hyundai HB20", 35),
                new XYChart.Data("Fiat Uno", 20),
                new XYChart.Data("Toyota Bandeirante", 20),
                new XYChart.Data("Fiat Punto", 15)
            );
            grafico.getYAxis().setLabel("Quantidade de locações");
        }
        else {
            listaDados =
            FXCollections.observableArrayList(
                new XYChart.Data("Hyundai HB20", 118),
                new XYChart.Data("Toyota Bandeirante", 82),
                new XYChart.Data("Fiat Punto", 72),
                new XYChart.Data("Fiat Uno", 29)
            );
            grafico.getYAxis().setLabel("Horas emprestadas");
        }
        
        XYChart.Series serieDados = new XYChart.Series(listaDados);
        grafico.getData().clear();
        grafico.getData().add(serieDados);
        grafico.legendVisibleProperty().set(false);
        grafico.autosize();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbFiltrarGráfico.getItems().addAll( TipoFiltragem.values() );
        cbFiltrarGráfico.setValue(TipoFiltragem.QUANTIDADE);
        filtrarPor(TipoFiltragem.QUANTIDADE);
        grafico.getXAxis().setLabel("Modelos");
    }
    
    /**
     * Chama a tela de busca de locação, chamada na devolução e renovação.
     * @param isRenovar enviado ao a tela para saber a tela certa para 
     * retornar a locação
     */
    private void mudarTelaBuscaLocacao(boolean isRenovar) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/./telas/TelaBuscaLocacao.fxml")
        );
        Parent parent = fxmlLoader.load();
        
        TelaBuscaLocacaoController controller = fxmlLoader.getController();
        controller.inicializaDados(isRenovar);
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Busca de contrato");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroCliente1(ActionEvent event)
        throws IOException {
        
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaCadastroCliente1.fxml")
        );
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Cadastro do cliente");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaCadastroVeiculo(ActionEvent event)
        throws IOException {
        
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaCadastroVeiculo.fxml")
        );
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Cadastro de veículo");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void mudarTelaLocacao1(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(
            getClass().getResource("/./telas/TelaLocacao1.fxml")
        );
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Nova locação");
        stage.setScene( new Scene(parent) );
    }

    @FXML
    private void devolver(ActionEvent event) throws IOException {
        mudarTelaBuscaLocacao(false);
    }

    @FXML
    private void renovar(ActionEvent event) throws IOException {
        mudarTelaBuscaLocacao(true);
    }

    @FXML
    private void mudarFiltragem(ActionEvent event) {
        filtrarPor( cbFiltrarGráfico.getValue() );
    }
    
}
