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
    
    public static void mostrarErroBanco()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(
            "Erro com o banco de dados!"
        );
        alert.setHeaderText(
            "Erro na comunicação com o banco de dados, dado não foi inserido."
        );
        alert.showAndWait();
    }
    
    public static void mostrarCampoInvalido(String header)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo inválido!");
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
