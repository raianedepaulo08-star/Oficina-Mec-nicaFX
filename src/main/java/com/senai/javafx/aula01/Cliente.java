package com.senai.javafx.aula01;

import java.util.ArrayList;
public class Cliente {

        int id;
        String nome;
        String cpf;
        String telefone;

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        @Override
        public String toString() {
            return "com.senai.javafx.aula01.Cliente{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", cpf='" + cpf + '\'' +
                    ", telefone='" + telefone + '\'' +
                    '}';
        }

        public Cliente(int id, String nome, String cpf, String telefone) {
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.telefone = telefone;
        }

        public void adicionarVeiculo(Veiculo veiculo) {
            veiculos.add(veiculo);
        }

        public void listarVeiculos() {
            System.out.println("Veiculos do cliente " + nome);

            for (int i = 0; i < veiculos.size(); i++) {
                System.out.println(veiculos.get(i).modelo);
            }


        }
    }

