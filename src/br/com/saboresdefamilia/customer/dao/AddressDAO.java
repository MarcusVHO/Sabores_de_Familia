package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDAO {
    public static void create (Address address, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO endereco(endereco, numero, complemento, clientes_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, address.getAddress());
            stmt.setInt(2, address.getNumber());
            stmt.setString(3, address.getComplement());
            stmt.setInt(4, address.getCustomers_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                address.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.closeConnection(null, stmt);
        }

    }

    public static ArrayList<Address> readAddresses(int clientID, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM endereco WHERE clientes_id = ?"
            );
            stmt.setInt(1, clientID);
            rs = stmt.executeQuery();
            ArrayList<Address> addresses = new ArrayList<>();
            while (rs.next()) {
                Address address = new Address();
                address.setAddress(rs.getString("endereco"));
                address.setNumber(rs.getInt("numero"));
                address.setComplement(rs.getString("complemento"));
                address.setId(rs.getInt("id"));
                address.setCustomers_id(rs.getInt("clientes_id"));
                addresses.add(address);
            }
            return addresses;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }


    public static void update(Address address , Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "UPDATE endereco SET endereco = ?, numero = ?, complemento = ? WHERE id = ?;"
            );

            stmt.setString(1, address.getAddress());
            stmt.setInt(2, address.getNumber());
            stmt.setString(3, address.getComplement());
            stmt.setInt(4, address.getId());
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }

    public static void deleteByCustomerID(int customerID , Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "DELETE FROM endereco WHERE clientes_id = ?;"
            );

            stmt.setInt(1, customerID);
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Database error: " + ex);
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
