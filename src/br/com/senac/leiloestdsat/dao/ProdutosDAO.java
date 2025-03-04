package br.com.senac.leiloestdsat.dao;

/**
 *
 * @author Adm
 */
import br.com.senac.leiloestdsat.dto.ProdutosDTO;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    private static Connection conn;
    private static PreparedStatement prep;
    private static ResultSet resultset;
    private static final ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public static void cadastrarProduto(ProdutosDTO produto) {
        conn = null;
        try {
            conn = conectaDAO.connectDB();
            prep = conn.prepareStatement(
                    "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            prep.setString(1,
                    produto.getNome());
            prep.setInt(2,
                    produto.getValor());
            prep.setString(3,
                    produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(
                    null,
                    "DADOS DO PRODUTO: " + produto.getNome() + " SALVOS COM SUCESSO.",
                    "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível adicionar: " + produto.getNome() + ".\n" + e.getMessage().toUpperCase(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            conectaDAO.discConnectDB();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
}
