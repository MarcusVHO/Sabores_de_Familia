package br.com.saboresdefamilia.menu.dao;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.menu.model.Menu;

import java.sql.*;
import java.util.ArrayList;

public class MenuDAO {
    public static void create(Menu menu, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                    "INSERT INTO cardapio(nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, menu.getName());
            stmt.setString(2, menu.getDescription());
            stmt.executeUpdate();


            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                menu.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }
    }

    public static ArrayList<Menu> selectAllMenus(Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Menu> menus = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                    "SELECT * FROM cardapio"
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setName(rs.getString("nome"));
                menu.setDescription(rs.getString("descricao"));
                menus.add(menu);
            }

            return menus;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }
    }

    public static void updateMenu(Menu menu, Connection conn) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
                    "UPDATE cardapio set nome = ?, descricao = ? WHERE id = ?"
            );
            stmt.setString(1, menu.getName());
            stmt.setString(2, menu.getDescription());
            stmt.setInt(3, menu.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }

    public static void deleteMenu(Menu menu, Connection conn) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(
                    "DELETE FROM cardapio WHERE id = ?"
            );
            stmt.setInt(1, menu.getId());
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }  finally {
            ConnectionFactory.closeConnection(null, stmt);
        }
    }
}
