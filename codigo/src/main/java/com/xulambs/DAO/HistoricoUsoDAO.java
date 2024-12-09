package com.xulambs.daos;

import com.xulambs.model.HistoricoUso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoricoUsoDAO implements Dao<HistoricoUso> {
    private List<HistoricoUso> historicos = new ArrayList<>();

    @Override
    public void salvar(HistoricoUso historicoUso) {
        if (historicoUso.getId() == null) {
            historicoUso.setId(UUID.randomUUID());
        }
        historicos.add(historicoUso);
    }

    @Override
    public List<HistoricoUso> listarTodos() {
        return new ArrayList<>(historicos);
    }

    @Override
    public HistoricoUso buscarPorId(UUID id) {
        for (HistoricoUso historico : historicos) {
            if (historico.getId().equals(id)) {
                return historico;
            }
        }
        return null;
    }

    @Override
    public void atualizar(HistoricoUso historicoUso) {
        HistoricoUso historicoExistente = buscarPorId(historicoUso.getId());
        if (historicoExistente != null) {
            historicos.remove(historicoExistente);
            historicos.add(historicoUso);
        } else {
            throw new RuntimeException("Histórico de Uso não encontrado para atualização."); // Trate a exceção adequadamente
        }
    }

    @Override
    public void excluir(UUID id) {
        HistoricoUso historico = buscarPorId(id);
        if (historico != null) {
            historicos.remove(historico);
        } else {
            throw new RuntimeException("Histórico de Uso não encontrado para exclusão."); // Trate a exceção adequadamente
        }
    }


    // Adicione outros métodos específicos para HistoricoUso, se necessário.
    // Por exemplo, buscar histórico por cliente, veículo ou período.
}