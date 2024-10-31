import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private static final List<Cliente> clientes = new ArrayList<>();

    // Bloco estático para inicializar alguns clientes na lista
    static {
        clientes.add(new Cliente("123", "João"));
        clientes.add(new Cliente("456", "Maria"));
        clientes.add(new Cliente("789", "Carlos"));
    }

    // Método para obter todos os clientes
    public static List<Cliente> getClientes() {
        return clientes;
    }
}
