package com.senai.javafx.aula01.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField digiteAqui;
    @FXML
    private Label nomedaPessoa;
    @FXML
    protected void onHelloButtonClick(){
        welcomeText.setText("Welcome to JavaFX Applícation");
    }
    @FXML
    public  void cadastrarPessoa() {
        String nome = digiteAqui.getText();
        nomedaPessoa.setText("Olá" + nome);
    }

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtCliente;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ListView<String> listaVeiculos;

    @FXML
    private Label lblMensagem;

    @FXML
    private void cadastrarVeiculo() {

        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();
        String marca = txtMarca.getText();
        String cliente = txtCliente.getText();

        if (modelo.isEmpty() || placa.isEmpty()
                || marca.isEmpty() || cliente.isEmpty()) {

            lblMensagem.setText(
                    "Preencha todos os campos!"
            );
            return;
        }

        String veiculo = "Modelo: " + modelo
                + " | Placa: " + placa
                + " | Marca: " + marca
                + " | Cliente: " + cliente;

        listaVeiculos.getItems().add(veiculo);

        lblMensagem.setText(
                "Veículo cadastrado com sucesso!"
        );

        txtModelo.clear();
        txtPlaca.clear();
        txtMarca.clear();
        txtCliente.clear();




    }
}






