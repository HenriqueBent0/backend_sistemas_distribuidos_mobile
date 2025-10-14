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

}
