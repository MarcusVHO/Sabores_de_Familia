package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Customer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomersDAO {
    public static void create(Customer customer, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO clientes(nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getDescription());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                customer.setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
