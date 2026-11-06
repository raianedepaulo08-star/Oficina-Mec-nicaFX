package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

            public class CadastroVeiculo {

                public void mostrar(Stage stage) {
                    TextField modelo = new TextField();
                    modelo.setPromptText("Modelo");

                    TextField placa = new TextField();
                    placa.setPromptText("Placa");

                    TextField marca = new TextField();
                    marca.setPromptText("Marca");

                    TextField cpfCliente = new TextField();
                    cpfCliente.setPromptText("CPF do cliente");

                    ListView<DadosOficina.Veiculo> lista =
                            new ListView<>();

                    lista.getItems().setAll(DadosOficina.veiculos);

                    Button cadastrar = new Button("Cadastrar veículo");
                    Button voltar = new Button("Voltar ao menu");

                    cadastrar.setOnAction(e -> {
                        String m = modelo.getText().trim();
                        String p = placa.getText().trim().toUpperCase();
                        String ma = marca.getText().trim();
                        String cpf = cpfCliente.getText().trim();

                        if (m.isEmpty() || p.isEmpty()
                                || ma.isEmpty() || cpf.isEmpty()) {
                            avisar("Preencha todos os campos.");
                            return;
                        }

                        boolean clienteExiste = false;

                        for (DadosOficina.Cliente c :
                                DadosOficina.clientes) {
                            if (c.cpf.equalsIgnoreCase(cpf)) {
                                clienteExiste = true;
                                break;
                            }
                        }

                        if (!clienteExiste) {
                            avisar("Cadastre primeiro o cliente.");
                            return;
                        }

                        for (DadosOficina.Veiculo v :
                                DadosOficina.veiculos) {
                            if (v.placa.equalsIgnoreCase(p)) {
                                avisar("Esta placa já está cadastrada.");
                                return;
                            }
                        }

                        DadosOficina.Veiculo v =
                                new DadosOficina.Veiculo(
                                        DadosOficina.proximoIdVeiculo++,
                                        m, p, ma, cpf);

                        DadosOficina.veiculos.add(v);
                        lista.getItems().add(v);

                        modelo.clear();
                        placa.clear();
                        marca.clear();
                        cpfCliente.clear();

                        avisar("Veículo cadastrado!");
                    });

                    lista.setCellFactory(lv -> new ListCell<>() {
                        @Override
                        protected void updateItem(
                                DadosOficina.Veiculo v, boolean vazio) {
                            super.updateItem(v, vazio);
                            setText(vazio || v == null ? null : v.toString());
                        }
                    });

                    voltar.setOnAction(e ->
                            new HelloApplication().start(stage));

                    VBox tela = new VBox(10,
                            new Label("CADASTRO DE VEÍCULO"),
                            modelo, placa, marca, cpfCliente,
                            cadastrar, lista, voltar);

                    tela.setStyle("-fx-padding: 20;");
                    stage.setTitle("Cadastro de Veículo");

                    Scene scene = new Scene(tela, 1200, 800);

                    scene.getStylesheets().add(
                            getClass().getResource("/style.css").toExternalForm()
                    );

                    stage.setTitle("Oficina Mecânica - Dashboard");
                    stage.setScene(scene);
                    stage.show();

                    Platform.runLater(() -> {
                        stage.setMaximized(false);
                        stage.setMaximized(true);
                    });
                }

                public void avisar(String mensagem) {
                    new Alert(Alert.AlertType.INFORMATION, mensagem)
                            .showAndWait();
                }
            }
