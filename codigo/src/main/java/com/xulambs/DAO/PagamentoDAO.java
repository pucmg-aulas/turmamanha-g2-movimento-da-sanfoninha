package com.xulambs.daos;


import com.xulambs.model.Pagamento;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PagamentoDAO implements Dao<Pagamento> {
    private List<Pagamento> pagamentos = new ArrayList<>();

    @Override
    public void salvar(Pagamento pagamento) {
        if (pagamento.getId() == null) {
            pagamento.setId(UUID.randomUUID());
        }
        pagamentos.add(pagamento);
    }

    @Override
    public List<Pagamento> listarTodos() {
        return new ArrayList<>(pagamentos);
    }

    @Override
    public Pagamento buscarPorId(UUID id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId().equals(id)) {
                return pagamento;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Pagamento pagamento) {
        Pagamento pagamentoExistente = buscarPorId(pagamento.getId());
        if (pagamentoExistente != null) {
            pagamentos.remove(pagamentoExistente);
            pagamentos.add(pagamento);
        } else {
           throw new RuntimeException("Pagamento não encontrado para atualização."); // Trate a exceção adequadamente
        }
    }

    @Override
    public void excluir(UUID id) {
        Pagamento pagamento = buscarPorId(id);
        if (pagamento!= null) {
            pagamentos.remove(pagamento);
        } else {
            throw new RuntimeException("Pagamento não encontrado para exclusão."); // Trate a exceção adequadamente
        }
    }


     //Adicione outros métodos específicos para Pagamento, se necessário.
     //Por exemplo, buscar pagamentos por cliente, período, ou tipo de pagamento.

}