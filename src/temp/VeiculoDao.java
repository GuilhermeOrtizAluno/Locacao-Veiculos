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
                    + "quinzenal, mensal, valorSeguro, placa, marca, "
                    + "modelo, anoFabricacao, valorIndenizacao, quilometragemAtual, "
                    + "informacoesTecnicas, imagem) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getRenavam());
            stmt.setDouble(2, u.getDiaria());
            stmt.setDouble(3, u.getSemanal());
            stmt.setDouble(4, u.getQuinzenal());
            stmt.setDouble(5, u.getMensal());
            stmt.setDouble(6, u.getValorSeguro());
            stmt.setString(7, u.getPlaca());
            stmt.setString(8, u.getMarca());
            stmt.setString(9, u.getModelo());
            stmt.setString(10, u.getAnoFabricacao());
            stmt.setDouble(11, u.getValorIndenizacao());
            stmt.setDouble(12, u.getQuilometragemAtual());
            stmt.setString(13, u.getImagem()); 
            stmt.setString(14, u.getInformacoesTecnicas());


            stmt.executeUpdate();

            //Gson gson = new Gson();
            //System.out.println(gson.toJson(u));
            ConnectionFactory.closeConnection(con, stmt);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}