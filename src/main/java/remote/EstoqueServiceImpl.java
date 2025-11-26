package remote;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import model.Movimentacao;
import model.Produto;

/**
 * Implementação do serviço de estoque via RMI. Realiza operações no banco
 * SQLite como: - adicionar produtos - editar produtos - excluir - movimentar
 * estoque - listar produtos
 */
public class EstoqueServiceImpl extends UnicastRemoteObject implements EstoqueServico {

    private final Connection conn; // Conexão com o banco SQLite

    /**
     * Construtor. Inicia a conexão com o banco e cria tabelas se necessário.
     */
    public EstoqueServiceImpl() throws RemoteException {
        super();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:estoque.db");
            inicializarTabelas();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao conectar ao banco", e);
        }
    }

    /**
     * Cria as tabelas do banco caso não existam.
     */
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

    /**
     * Insere um novo produto no banco.
     */
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

    /**
     * Atualiza os dados de um produto no banco.
     */
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

    /**
     * Remove um produto do banco pelo ID.
     */
    public void excluirProduto(int id) throws RemoteException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM produto WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao excluir produto", e);
        }
    }

    /**
     * Registra uma movimentação de entrada ou saída, atualizando o estoque do
     * produto correspondente.
     */
    public String registrarMovimentacao(Movimentacao m) throws RemoteException {
        try {
            Produto p = null;
            // Consulta do produto
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM produto WHERE id = ?")) {
                ps.setInt(1, m.getIdProduto());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    p = new Produto(
                            rs.getInt("id"), rs.getString("nome"),
                            rs.getDouble("preco"), rs.getString("unidade"),
                            rs.getInt("quantidade"), rs.getInt("qtdMin"),
                            rs.getInt("qtdMax"), rs.getString("categoria"));
                }
            }
            if (p == null) {
                return "Produto não encontrado!";
            }

            // Calcula novo estoque
            int novoEstoque = p.getQuantidade()
                    + (m.getTipo() == Movimentacao.Tipo.ENTRADA ? m.getQuantidade() : -m.getQuantidade());

            // Atualiza estoque
            try (PreparedStatement ps = conn.prepareStatement("UPDATE produto SET quantidade = ? WHERE id = ?")) {
                ps.setInt(1, novoEstoque);
                ps.setInt(2, p.getId());
                ps.executeUpdate();
            }

            // Mensagens de aviso
            if (novoEstoque < p.getQuantidadeMinima()) {
                return "Atenção: produto abaixo do mínimo!";
            }
            if (novoEstoque > p.getQuantidadeMaxima()) {
                return "Atenção: produto acima do máximo!";
            }
            return "Movimentação registrada com sucesso!";
        } catch (SQLException e) {
            throw new RemoteException("Erro na movimentação", e);
        }
    }

    /**
     * Retorna uma lista com todos os produtos cadastrados.
     */
    public List<Produto> listarProdutos() throws RemoteException {
        List<Produto> lista = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM produto ORDER BY nome");
            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"), rs.getString("nome"),
                        rs.getDouble("preco"), rs.getString("unidade"),
                        rs.getInt("quantidade"), rs.getInt("qtdMin"),
                        rs.getInt("qtdMax"), rs.getString("categoria")
                ));
            }
        } catch (SQLException e) {
            throw new RemoteException("Erro ao listar produtos", e);
        }
        return lista;
    }
}
