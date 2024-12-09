public package com.xulambs.daos;

import java.util.List;
import java.util.UUID;

public interface Dao<T> {
    void salvar(T objeto);
    List<T> listarTodos();
    T buscarPorId(UUID id);
    void atualizar(T objeto);
    void excluir(UUID id);
} InterfaceDAO {
    
}
