package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Phone;

import java.sql.*;
import java.util.ArrayList;
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

    public static ArrayList<Phone> readPhones(int customerID, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM telefone WHERE clientes_id = ?"
            );
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();
            ArrayList<Phone> phones = new ArrayList<>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setPhone(rs.getInt("telefone"));
                phone.setId(rs.getInt("id"));
                phone.setCustomers_id(rs.getInt("clientes_id"));
                phones.add(phone);
            }
            return phones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }

    public static void update(Phone phone , Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "UPDATE telefone SET telefone = ? WHERE id = ?;"
            );

            stmt.setInt(1, phone.getPhone());
            stmt.setInt(2, phone.getId());
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }

    public static void deleteByCustomerID(int CustomerID , Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "DELETE FROM telefone WHERE clientes_id = ?;"
            );

            stmt.setInt(1, CustomerID);
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
