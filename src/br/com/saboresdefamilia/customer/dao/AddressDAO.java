package br.com.saboresdefamilia.customer.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.model.Address;

import java.sql.*;

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
}
