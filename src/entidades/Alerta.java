/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javafx.scene.control.Alert;

/**
 *
 * @author luanl
 */
public class Alerta {
    
    public static void mostraAlerta(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo inválido!");
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    
    public static void mostrarErroBanco() {
        mostraAlerta(
            "Erro com o banco de dados!", 
            "Erro na comunicação com o banco de dados, dado não foi inserido."
        );
    }
    
    public static void mostrarCampoInvalido(String header) {
        mostraAlerta("Campo inválido!", header);
    }
}
