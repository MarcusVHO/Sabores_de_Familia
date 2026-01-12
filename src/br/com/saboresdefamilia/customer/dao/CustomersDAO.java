package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Customer;

import java.sql.*;
import java.util.ArrayList;
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

    public static ArrayList<Customer> selectByName(String name, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM clientes WHERE nome LIKE ?"
            );
            stmt.setString(1, name+"%");
            rs = stmt.executeQuery();

            ArrayList<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("nome"));
                customer.setDescription(rs.getString("descricao"));
                customers.add(customer);
            }

            return customers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Customer customer , Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "UPDATE clientes SET nome = ?, descricao = ? WHERE id = ?;"
            );

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getDescription());
            stmt.setInt(3, customer.getId());
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
