package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Phone;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneDAO {
    public static void create(Phone phone, Connection conn) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO telefone(telefone, clientes_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, phone.getPhone());
            stmt.setInt(2, phone.getCustomers_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                phone.setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(PhoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
