package remote;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Classe principal responsável por iniciar o servidor RMI. Cria o registro RMI,
 * instancia o serviço e o disponibiliza.
 */
public class ServidorMain {
    public static void main(String[] args) throws Exception {
        // Cria o registro padrão do RMI na porta 1099
        LocateRegistry.createRegistry(1099);
        // Instancia o serviço
        EstoqueServico servico = new EstoqueServiceImpl();
        // Registra o serviço com o nome "EstoqueService"
        Naming.rebind("rmi://localhost:1099/EstoqueService", servico);
        System.out.println("Servidor de estoque rodando com SQLite...");
    }
}
