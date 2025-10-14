package remote;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorMain {
    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        EstoqueServico servico = new EstoqueServiceImpl();
        Naming.rebind("rmi://localhost:1099/EstoqueService", servico);
        System.out.println("Servidor de estoque rodando com SQLite...");
     }
}