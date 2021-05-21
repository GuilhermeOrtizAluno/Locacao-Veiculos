package temp;

import entidades.Locacao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 *
 * @author Guilherme Ortiz
 */
public class LocacaoDao {

    // Create
    public void create(Locacao u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO locacao(contratoAberto, "
                    + "diaria, semanal, quinzenal, mensal, seguro, acresimo, "
                    + "texto, idCondutorLocacao, idVeiculo, diarias, semanas, "
                    + "quinzenas, meses, dataEmprestimo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '2021-05-20')");
            stmt.setBoolean(1, u.getContratoAberto());
            stmt.setDouble(2, u.getDiaria());
            stmt.setDouble(3, u.getSemanal());
            stmt.setDouble(4, u.getQuinzenal());
            stmt.setDouble(5, u.getMensal());
            stmt.setDouble(6, u.getSeguro());
            stmt.setDouble(7, u.getAcresimo());
            stmt.setString(8, u.getTexto());
            stmt.setInt(9, 1);
            stmt.setInt(10, 1);
            stmt.setDouble(11, u.getDiarias());
            stmt.setDouble(12, u.getSemanas());
            stmt.setDouble(13, u.getQuinzenas()); 
            stmt.setDouble(14, u.getMeses());


            stmt.executeUpdate();

            //Gson gson = new Gson();
            //System.out.println(gson.toJson(u));
            ConnectionFactory.closeConnection(con, stmt);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}