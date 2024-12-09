package com.xulambs.daos;

import com.xulambs.model.Estacionamento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstacionamentoDAO implements Dao<Estacionamento> {
    private List<Estacionamento> estacionamentos = new ArrayList<>();

    @Override
    public void salvar(Estacionamento estacionamento) {
        if (estacionamento.getId() == null) {
            estacionamento.setId(UUID.randomUUID());
        }
        estacionamentos.add(estacionamento);
    }
    

    @Override
    public List<Estacionamento> listarTodos() {
        return new ArrayList<>(estacionamentos);
    }

    @Override
    public Estacionamento buscarPorId(UUID id) {
        for (Estacionamento estacionamento : estacionamentos) {
            if (estacionamento.getId().equals(id)) {
                return estacionamento;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Estacionamento estacionamento) {
        Estacionamento estacionamentoExistente = buscarPorId(estacionamento.getId());
        if (estacionamentoExistente != null) {
            estacionamentos.remove(estacionamentoExistente);
            estacionamentos.add(estacionamento);
        } else {
            throw new RuntimeException("Estacionamento não encontrado para atualização."); // Trate a exceção adequadamente
        }
    }

    @Override
    public void excluir(UUID id) {
        Estacionamento estacionamento = buscarPorId(id);
        if (estacionamento != null) {
            estacionamentos.remove(estacionamento);
        } else {
            throw new RuntimeException("Estacionamento não encontrado para exclusão."); // Trate a exceção adequadamente
        }
    }

    // Adicione outros métodos específicos para Estacionamento, se necessário
}