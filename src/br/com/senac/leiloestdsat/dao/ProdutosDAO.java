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
                    "A Venda");
            prep.executeUpdate();
            JOptionPane.showMessageDialog(
                    null,
                    "DADOS DO PRODUTO: " + produto.getNome().toUpperCase() + " SALVOS COM SUCESSO.",
                    "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "NÃO FOI POSSÍVEL ADICIONAR: " + produto.getNome() + ".\n" + e.getMessage().toUpperCase(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            ConectaDAO.disconnectDB(conn,
                    prep,
                    resultset);
        }
    }

    public static ArrayList<ProdutosDTO> listarProdutos() {
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
            ConectaDAO.disconnectDB(conn,
                    prep,
                    resultset);
        }
        return listagem;
    }

    public static void venderProduto(int produtoId) {
        conn = null;
        String nomeProduto = null;
        try {
            conn = ConectaDAO.connectDB();
            prep = conn.prepareStatement("SELECT nome FROM produtos WHERE id=?");
            prep.setInt(1,
                    produtoId);
            resultset = prep.executeQuery();
            if (resultset.next()) {
                nomeProduto = resultset.getString("nome");
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "PRODUTO NÃO ENCONTRADO.",
                        "ERRO",
                        JOptionPane.WARNING_MESSAGE);
            }
            prep = conn.prepareStatement(
                    "UPDATE produtos SET status=? WHERE id=?");
            prep.setString(1,
                    "Vendido");
            prep.setInt(2,
                    produtoId);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(
                    null,
                    "PRODUTO: '" + nomeProduto.toUpperCase() + "' VENDIDO.",
                    "SUCESSO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "NÃO FOI POSSÍVEL VENDER O PRODUTO.\n" + e.getMessage().toUpperCase(),
                    "ERRO",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            ConectaDAO.disconnectDB(conn,
                    prep,
                    resultset);
        }
    }
}
