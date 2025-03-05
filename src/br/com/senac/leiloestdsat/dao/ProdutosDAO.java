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
            conn = ConectaDAO.connectDB();
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
                    "DADOS DO PRODUTO: " + produto.getNome().toUpperCase() + " SALVOS COM SUCESSO.",
                    "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível adicionar: " + produto.getNome() + ".\n" + e.getMessage().toUpperCase(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            ConectaDAO.disconnectDB();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem.clear();
        conn = null;
        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO prod = new ProdutosDTO();
                prod.setId(resultset.getInt("id"));
                prod.setNome(resultset.getString("nome"));
                prod.setValor(resultset.getInt("valor"));
                prod.setStatus(resultset.getString("status"));
                listagem.add(prod);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "CONNECTION ERROR: " + e.getMessage(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
            return new ArrayList<>();
        } finally {
            ConectaDAO.disconnectDB();
        }
        return listagem;
    }
}
