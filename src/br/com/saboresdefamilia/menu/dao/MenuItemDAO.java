package br.com.saboresdefamilia.menu.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.menu.model.MenuItem;

import java.sql.*;
import java.util.ArrayList;

public class MenuItemDAO {
    public static void create(MenuItem menuItem, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO items_cardapio(nome, cardapio_id, categoria) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, menuItem.getName());
            stmt.setInt(2, menuItem.getMenuID());
            stmt.setString(3, menuItem.getCategory());
            stmt.executeUpdate();


            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                menuItem.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }
    }

    public static ArrayList<MenuItem> selectMenuItems(int menuID, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM items_cardapio WHERE cardapio_id = ?"
            );
            stmt.setInt(1, menuID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt("id"));
                menuItem.setName(rs.getString("nome"));
                menuItem.setCategory(rs.getString("categoria"));
                menuItems.add(menuItem);
            }

            return menuItems;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }
    }

    public static void deleteByMenuID(int menuID, Connection conn) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
                    "DELETE FROM items_cardapio WHERE cardapio_id = ?"
            );
            stmt.setInt(1, menuID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
