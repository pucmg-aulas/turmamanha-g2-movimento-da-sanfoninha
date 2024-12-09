package com.xulambs.daos;

import com.xulambs.model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class VeiculoDAO implements Dao<Veiculo> {
    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void salvar(Veiculo veiculo) {
        //Não precisa gerar ID pois a placa é o identificador.
        veiculos.add(veiculo);
    }

    @Override
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    @Override
    public Veiculo buscarPorId(UUID id) {
       //Placa é o identificador
        return buscarPorPlaca(id.toString()); //Adaptação -  Idealmente, use a placa como chave em um Map.
    }


    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }


    @Override
    public void atualizar(Veiculo veiculo) {
        //Placa é o identificador
        Veiculo veiculoExistente = buscarPorPlaca(veiculo.getPlaca());
        if (veiculoExistente != null) {
            veiculos.remove(veiculoExistente);
            veiculos.add(veiculo);
        } else {
            throw new RuntimeException("Veiculo não encontrado para atualização."); // Trate a exceção adequadamente
        }
    }


    @Override
    public void excluir(UUID id) {
        //Placa é o identificador
        Veiculo veiculo = buscarPorPlaca(id.toString()); //Adaptação. Idealmente, use a placa como chave em um Map.
        if (veiculo != null) {
            veiculos.remove(veiculo);
        } else {
            throw new RuntimeException("Veiculo não encontrado para exclusão."); // Trate a exceção adequadamente
        }

    }
}