import main.java.com.xulambs.controller.ClienteController;
import main.java.com.xulambs.DAO.ClienteDAO;
import view.AddClienteView;

public class Main {
    public static void main(String[] args) {
        AddClienteView addClienteView = new AddClienteView();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        new ClienteController(addClienteView, clienteDAO);
        
        addClienteView.setVisible(true);
    }
}

