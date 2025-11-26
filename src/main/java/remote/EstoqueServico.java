package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Movimentacao;
import model.Produto;

/**
 * Interface RMI do serviço de estoque. Define os métodos que podem ser
 * acessados remotamente pelo cliente.
 */
public interface EstoqueServico extends Remote {
    void adicionarProduto(Produto p) throws RemoteException;
    String registrarMovimentacao(Movimentacao m) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    void editarProduto(Produto p) throws RemoteException;
    void excluirProduto(int id) throws RemoteException;
}
