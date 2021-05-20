/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import entidades.Veiculo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 *
 * @author Guilherme Ortiz
 */
public class VeiculoDao {

    // Create
    public void create(Veiculo u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO veiculo(renavam, diaria, semanal, "
                    + "quinzenal, mensal, hora, horaExcedente, valorSeguro, placa, marca, "
                    + "modelo, anoFabricacao, valorIndenizacao, proximaManutencao, quilometragemAtual, "
                    + "informacoesTecnicas, imagem) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getRenavam());
            stmt.setDouble(2, u.getDiaria());
            stmt.setDouble(3, u.getSemanal());
            stmt.setDouble(4, u.getQuinzenal());
            stmt.setDouble(5, u.getMensal());
            stmt.setDouble(6, u.getHora());
            stmt.setDouble(7, u.getHoraExcedente());
            stmt.setDouble(8, u.getValorSeguro());
            stmt.setString(9, u.getPlaca());
            stmt.setString(10, u.getMarca());
            stmt.setString(11, u.getModelo());
            stmt.setString(12, u.getAnoFabricacao());
            stmt.setDouble(13, u.getValorIndenizacao());
            stmt.setDate(14, (Date) u.getProximaManutencao());
            stmt.setDouble(15, u.getQuilometragemAtual());
            stmt.setString(16, u.getImagem()); 
            stmt.setString(17, u.getInformacoesTecnicas());
            

            stmt.executeUpdate();

            //Gson gson = new Gson();
            //System.out.println(gson.toJson(u));
            ConnectionFactory.closeConnection(con, stmt);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
