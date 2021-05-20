/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import entidades.Condutor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Guilherme Ortiz
 */
public class CondutorDao {

    // Create
    public void create(Condutor u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO condutor (numeroHabilitacao, telefone1, telefone2, email, idCliente)VALUES(?,?,?,?,?)");
            stmt.setString(1, u.getNumeroHabilitacao());
            stmt.setString(2, u.getTelefone1());
            stmt.setString(3, u.getTelefone2());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, 1);

            stmt.executeUpdate();

            //Gson gson = new Gson();
            //System.out.println(gson.toJson(u));
            ConnectionFactory.closeConnection(con, stmt);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    //Read
    public void read()  {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                //JSONObject user = new JSONObject();

                //user.put("usuario", rs.getString("usuario"));
                //user.put("nome", rs.getString("nome"));
                //user.put("tipo_usuario",
                        //rs.getBoolean("is_admin") ? "admin"
                        //: rs.getBoolean("is_monitor") ? "monitor"
                       // : "aluno"
               // );
               //users.put(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CondutorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }

    //Update
    public void update(Condutor u) {

        /*Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {

            if (u.getNovo_usuario() != null) {
                stmt = con.prepareStatement("UPDATE usuario SET usuario = ? WHERE usuario = ?");
                stmt.setString(1, u.getNovo_usuario());
                stmt.setString(2, u.getUsuario());
                
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("UPDATE aluno_monit SET usuario_aluno = ? WHERE usuario_aluno = ?");
                stmt.setString(1, u.getNovo_usuario());
                stmt.setString(2, u.getUsuario());

                stmt.executeUpdate();

                stmt = con.prepareStatement("UPDATE monitoria SET usuario_monitor = ? WHERE usuario_monitor = ?");
                stmt.setString(1, u.getNovo_usuario());
                stmt.setString(2, u.getUsuario());

                stmt.executeUpdate();
            }
            
            if(u.getNome() != null){
                stmt = con.prepareStatement("UPDATE usuario SET nome = ? WHERE usuario = ?");
                stmt.setString(1, u.getNome());
                if(u.getNovo_usuario() == null){
                    stmt.setString(2, u.getUsuario());
                }else{
                    stmt.setString(2, u.getNovo_usuario());
                }
                
                stmt.executeUpdate();
            }

            if(u.getSenha() != null){
                stmt = con.prepareStatement("UPDATE usuario SET senha = ? WHERE usuario = ?");
                stmt.setString(1, u.getSenha());
                if(u.getNovo_usuario() == null){
                    stmt.setString(2, u.getUsuario());
                }else{
                    stmt.setString(2, u.getNovo_usuario());
                }
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            ConnectionFactory.closeConnection(con, stmt);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;*/
    }

    //Delete
    public void delete(Condutor u) {

        /*Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE usuario = ?");
            //stmt.setInt(1, u.getPk_usuario());
            stmt.setString(1, u.getUsuario());

            stmt.executeUpdate();

            if (u.getUsuario() != null) {
                stmt = con.prepareStatement("DELETE from aluno_monit WHERE usuario_aluno = ?");
                stmt.setString(1, u.getUsuario());

                stmt.executeUpdate();

                stmt = con.prepareStatement("UPDATE monitoria SET usuario_monitor = ? WHERE usuario_monitor = ?");
                stmt.setString(1, "");
                stmt.setString(2, u.getUsuario());

                stmt.executeUpdate();
            }

            //JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;
    }

    //search
    public User search(User u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        User user = new User();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND senha = ?");
            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getSenha());
            rs = stmt.executeQuery();

            while (rs.next()) {
                user.setPk_usuario(rs.getInt("pk_usuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setNome(rs.getString("nome"));
                user.setIs_admin(rs.getBoolean("is_admin"));
                user.setIs_monitor(rs.getBoolean("is_monitor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return user;*/
    }
}
