package view;

import javax.swing.*;
import main.java.com.xulambs.controller.ClienteController;
import main.java.com.xulambs.DAO.ClienteDAO;

public class AddClienteView extends JFrame {

    private JPanel jPanel = new JPanel();
    private JToggleButton buttonCancelar = new JToggleButton("Cancelar");
    private JToggleButton buttonSalvar = new JToggleButton("Salvar");
    private JLabel nomeClienteRotulo = new JLabel("Nome Cliente");
    private JLabel cpfClienteRotulo = new JLabel("CPF Cliente");
    private JLabel categoriaClienteRotulo = new JLabel("Categoria Cliente");
    private JTextField textNomeCliente = new JTextField();
    private JTextField textCpfCliente = new JTextField();
    private JTextField textCategoriaCliente = new JTextField();

    public AddClienteView() {
        initComponents();
        setTitle("Xulambs Parking - Adicionar Cliente");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JTextField getTextNomeCliente() {
        return textNomeCliente;
    }

    public JTextField getTextCpfCliente() {
        return textCpfCliente;
    }

    public JTextField getTextCategoriaCliente() {
        return textCategoriaCliente;
    }

    public JToggleButton getButtonCancelar() {
        return buttonCancelar;
    }

    public JToggleButton getButtonSalvar() {
        return buttonSalvar;
    }

    private void initComponents() {
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);

        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textNomeCliente)
                        .addComponent(textCpfCliente)
                        .addComponent(textCategoriaCliente)
                        .addGroup(jPanelLayout.createSequentialGroup()
                            .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nomeClienteRotulo)
                                .addComponent(cpfClienteRotulo)
                                .addComponent(categoriaClienteRotulo))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                            .addGap(0, 112, Short.MAX_VALUE)
                            .addComponent(buttonCancelar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonSalvar)))
                    .addContainerGap())
        );

        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(nomeClienteRotulo)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cpfClienteRotulo)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textCpfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(categoriaClienteRotulo)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textCategoriaCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSalvar)
                        .addComponent(buttonCancelar))
                    .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    public static void main(String[] args) {
        AddClienteView addClienteView = new AddClienteView();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        new ClienteController(addClienteView, clienteDAO);
        
        addClienteView.setVisible(true);
    }
}
