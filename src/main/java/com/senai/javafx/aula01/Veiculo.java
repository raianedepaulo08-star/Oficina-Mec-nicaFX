package com.senai.javafx.aula01;

import java.util.ArrayList;

public class Veiculo {
    int id;
    String marca;
    String modelo;
    String placa;
    Cliente cliente;

    ArrayList<OrdemServico> ordens = new ArrayList<>();

    public Veiculo(int i, String corolla, String abc123, String toyota, Cliente cliente1) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "com.senai.javafx.aula01.Veiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", cliente=" + cliente +
                '}';
    }

    public void listarOrdens() {
    }
}
