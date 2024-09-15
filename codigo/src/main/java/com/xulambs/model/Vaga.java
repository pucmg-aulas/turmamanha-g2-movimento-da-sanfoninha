package com.xulambs.model;

public class Vaga {
    private String id;
    private boolean disponivel;

    public Vaga(int fila, int numero) {
        this.id = fila + "-" + numero;
        this.disponivel = true;
    }

    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean sair() {
        disponivel = true;
        return true;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getId() {
        return id;
    }
}