package com.senai.javafx.aula01;

import java.util.ArrayList;

public class OrdemServico {
    int numeroOrdem;
    String status;
    Veiculo veiculo;
    ArrayList<Servico> servicos = new ArrayList<>();

    public OrdemServico(int numeroOrdem, String status, Veiculo veiculo) {
        this.numeroOrdem = numeroOrdem;
        this.status = status;
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "com.senai.javafx.aula01.OrdemServico{" +
                "numeroOrdem=" + numeroOrdem +
                ", status='" + status + '\'' +
                ", veiculo=" + veiculo +
                '}';
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public void removerServico(Servico servico) {
        servicos.remove(servico);
    }

    public void listarServicos() {
        System.out.println("Serviços da ordem " + numeroOrdem);

        for (int i = 0; i < servicos.size(); i++) {
            System.out.println(
                    servicos.get(i).descricao + servicos.get(i).valor
            );
        }
    }

    public double calcularTotal() {
        double total = 0;

        for (int i = 0; i < servicos.size(); i++) {
            total = total + servicos.get(i).valor;
        }

        return total;
    }

    public void alterarStatus(String novoStatus) {
        status = novoStatus;
    }
}
