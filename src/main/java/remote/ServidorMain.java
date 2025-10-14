package remote;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorMain {
    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        System.out.println("Servidor RMI iniciado na porta 1099...");
    }
}
