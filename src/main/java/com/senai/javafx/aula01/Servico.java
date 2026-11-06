package com.senai.javafx.aula01;

public class Servico {
    String descricao;
    Double valor;

    public Servico(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "com.senai.javafx.aula01.Servico{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
