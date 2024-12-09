package com.xulambs.daos;

import com.xulambs.model.Vaga;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VagaDAO implements Dao<Vaga> {
    private List<Vaga> vagas = new ArrayList<>();

    @Override
    public void salvar(Vaga vaga) {
        if (vaga.getId() == null) {
            vaga.setId(UUID.randomUUID());
        }
        vagas.add(vaga);
    }

    @Override
    public List<Vaga> listarTodos() {
        return new ArrayList<>(vagas);
    }

    @Override
    public Vaga buscarPorId(UUID id) {
        for (Vaga vaga : vagas) {
            if (vaga.getId().equals(id)) {
                return vaga;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Vaga vaga) {
        Vaga vagaExistente = buscarPorId(vaga.getId());
        if (vagaExistente != null) {
            vagas.remove(vagaExistente);
            vagas.add(vaga);
        } else {
            throw new RuntimeException("Vaga não encontrada para atualização."); // Trate a exceção adequadamente
        }
    }

    @Override
    public void excluir(UUID id) {
        Vaga vaga = buscarPorId(id);
        if (vaga != null) {
            vagas.remove(vaga);
        } else {
            throw new RuntimeException("Vaga não encontrada para exclusão."); // Trate a exceção adequadamente
        }
    }

    public Vaga buscarPorIdentificacao(String identificacao) {
        // Exemplo simples para procurar a vaga pela identificação
        // Suponha que você tenha uma lista de vagas em memória ou um banco de dados
        for (Vaga vaga : listarTodos()) { // listarTodos() pode retornar todas as vagas
            if (vaga.getIdentificacao().equals(identificacao)) {
                return vaga;
            }
        }
        return null; // Retorna null caso não encontre a vaga
    }
    

     // Adicione outros métodos específicos para Vaga, se necessário.  Por exemplo, buscar vagas disponíveis.


}