/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gui_o
 */
public class CondutorDAO {
    
    public boolean create(String hab, String email, String tel1, String tel2, String tipo) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO condutor (numeroHabilitacao, telefone1, telefone2, email, idCliente, idTipoHabilitacao)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, hab);
            stmt.setString(2, tel1);
            stmt.setString(3, tel2);
            stmt.setString(4, email);
            stmt.setString(5, "1");
            stmt.setString(6, "1");

            stmt.executeUpdate();

            ConnectionFactory.closeConnection(con, stmt);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
}
