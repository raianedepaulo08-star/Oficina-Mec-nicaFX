package com.senai.javafx.aula01.controllers;

import java.util.ArrayList;


    public class DadosOficina {

        public static ArrayList<Cliente> clientes =
                new ArrayList<>();

        public static ArrayList<Veiculo> veiculos =
                new ArrayList<>();

        public static ArrayList<OrdemServico> ordens =
                new ArrayList<>();

        public static int proximoIdCliente = 1;
        public static int proximoIdVeiculo = 1;
        public static int proximoIdOrdem = 1;

        public static class Cliente {
            public int id;
            public String nome;
            public String cpf;
            public String telefone;

            public Cliente(int id, String nome,
                           String cpf, String telefone) {
                this.id = id;
                this.nome = nome;
                this.cpf = cpf;
                this.telefone = telefone;
            }

            @Override
            public String toString() {
                return id + " | " + nome
                        + " | CPF: " + cpf
                        + " | Telefone: " + telefone;
            }
        }

        public static class Veiculo {
            public int id;
            public String modelo;
            public String placa;
            public String marca;
            public String cpfCliente;

            public Veiculo(int id, String modelo,
                           String placa, String marca,
                           String cpfCliente) {
                this.id = id;
                this.modelo = modelo;
                this.placa = placa;
                this.marca = marca;
                this.cpfCliente = cpfCliente;
            }

            @Override
            public String toString() {
                return id + " | " + modelo
                        + " | " + marca
                        + " | Placa: " + placa
                        + " | CPF do cliente: " + cpfCliente;
            }
        }

        public static class Servico {
            public String descricao;
            public double valor;

            public Servico(String descricao, double valor) {
                this.descricao = descricao;
                this.valor = valor;
            }

            @Override
            public String toString() {
                return descricao + " | R$ "
                        + String.format("%.2f", valor);
            }
        }

        public static class OrdemServico {
            public int id;
            public String placa;
            public String status = "Aberta";
            public ArrayList<Servico> servicos =
                    new ArrayList<>();

            public OrdemServico(int id, String placa) {
                this.id = id;
                this.placa = placa;
            }

            public double calcularTotal() {
                double total = 0;

                for (Servico servico : servicos) {
                    total += servico.valor;
                }

                return total;
            }

            @Override
            public String toString() {
                return "OS " + id
                        + " | Placa: " + placa
                        + " | Status: " + status
                        + " | Total: R$ "
                        + String.format("%.2f", calcularTotal());
            }
        }
    }


