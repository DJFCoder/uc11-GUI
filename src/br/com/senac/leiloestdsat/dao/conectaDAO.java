package br.com.senac.leiloestdsat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class ConectaDAO {
    private static Connection conn;

    protected static Connection connectDB() {
        conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/uc11?useSSL=true&serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,
                    "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

    protected static void disconnectDB() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "CONNECTION ERROR: " + e.getMessage(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
