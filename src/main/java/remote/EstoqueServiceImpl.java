package remote;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import model.Movimentacao;
import model.Produto;

public class EstoqueServiceImpl extends UnicastRemoteObject implements EstoqueServico {

    private final Connection conn;

    public EstoqueServiceImpl() throws RemoteException {
        super();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:estoque.db");
            inicializarTabelas();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao conectar ao banco", e);
        }
    }

    private void inicializarTabelas() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS produto ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT,"
                    + "preco REAL,"
                    + "unidade TEXT,"
                    + "quantidade INTEGER,"
                    + "qtdMin INTEGER,"
                    + "qtdMax INTEGER,"
                    + "categoria TEXT)");
        }

    }

    // Adicionar
    public void adicionarProduto(Produto p) throws RemoteException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO produto(nome, preco, unidade, quantidade, qtdMin, qtdMax, categoria) VALUES (?,?,?,?,?,?,?)")) {
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPrecoUnitario());
            ps.setString(3, p.getUnidade());
            ps.setInt(4, p.getQuantidade());
            ps.setInt(5, p.getQuantidadeMinima());
            ps.setInt(6, p.getQuantidadeMaxima());
            ps.setString(7, p.getCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao inserir produto", e);
        }
    }

    // Editar
    public void editarProduto(Produto p) throws RemoteException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE produto SET nome=?, preco=?, unidade=?, quantidade=?, qtdMin=?, qtdMax=?, categoria=? WHERE id=?")) {
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPrecoUnitario());
            ps.setString(3, p.getUnidade());
            ps.setInt(4, p.getQuantidade());
            ps.setInt(5, p.getQuantidadeMinima());
            ps.setInt(6, p.getQuantidadeMaxima());
            ps.setString(7, p.getCategoria());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao editar produto", e);
        }
    }

    // Excluir
    public void excluirProduto(int id) throws RemoteException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM produto WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao excluir produto", e);
        }
    }

}
