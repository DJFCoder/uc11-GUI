package br.com.senac.leiloestdsat.dao;

/**
 *
 * @author Adm
 */
import br.com.senac.leiloestdsat.dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    private static Connection conn;
    private static PreparedStatement prep;
    private static ResultSet resultset;
    private static final ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public static void cadastrarProduto (ProdutosDTO produto){
        conectaDAO.connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

