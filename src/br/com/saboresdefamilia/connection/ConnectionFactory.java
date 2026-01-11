package br.com.saboresdefamilia.connection;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Properties props = new Properties();

    static {
        try (InputStream input =
                     ConnectionFactory.class
                             .getClassLoader()
                             .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar db.properties", e);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.pass"));

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao");
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexao");
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        closeConnection(conn);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexao");
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConnection(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexao");
        }
    }
}
