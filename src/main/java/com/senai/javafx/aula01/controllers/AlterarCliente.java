package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

        public class AlterarCliente {

            public void mostrar(Stage stage) {
                ListView<DadosOficina.Cliente> lista =
                        new ListView<>();

                lista.getItems().setAll(DadosOficina.clientes);

                TextField nome = new TextField();
                nome.setPromptText("Nome");

                TextField cpf = new TextField();
                cpf.setPromptText("CPF");

                TextField telefone = new TextField();
                telefone.setPromptText("Telefone");

                lista.getSelectionModel().selectedItemProperty()
                        .addListener((obs, anterior, c) -> {
                            if (c != null) {
                                nome.setText(c.nome);
                                cpf.setText(c.cpf);
                                telefone.setText(c.telefone);
                            }
                        });

                Button salvar = new Button("Salvar alterações");
                Button voltar = new Button("Voltar ao menu");

                salvar.setOnAction(e -> {
                    DadosOficina.Cliente c =
                            lista.getSelectionModel().getSelectedItem();

                    if (c == null) {
                        mostrarAviso("Selecione um cliente.");
                        return;
                    }

                    if (nome.getText().trim().isEmpty()
                            || cpf.getText().trim().isEmpty()
                            || telefone.getText().trim().isEmpty()) {
                        mostrarAviso("Preencha todos os campos.");
                        return;
                    }

                    String novoCpf = cpf.getText().trim();

                    for (DadosOficina.Cliente outro :
                            DadosOficina.clientes) {
                        if (outro != c
                                && outro.cpf.equalsIgnoreCase(novoCpf)) {
                            mostrarAviso("Esse CPF já está cadastrado.");
                            return;
                        }
                    }

                    String cpfAntigo = c.cpf;

                    c.nome = nome.getText().trim();
                    c.cpf = novoCpf;
                    c.telefone = telefone.getText().trim();

                    for (DadosOficina.Veiculo v :
                            DadosOficina.veiculos) {
                        if (v.cpfCliente.equalsIgnoreCase(cpfAntigo)) {
                            v.cpfCliente = novoCpf;
                        }
                    }

                    lista.refresh();
                    mostrarAviso("Cliente alterado com sucesso.");
                });

                voltar.setOnAction(e ->
                        new HelloApplication().start(stage));

                VBox tela = new VBox(10,
                        new Label("ALTERAR CLIENTE"),
                        lista, nome, cpf, telefone, salvar, voltar);

                tela.setStyle("-fx-padding: 20;");
                stage.setTitle("Alterar cliente");

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

            public void mostrarAviso(String mensagem) {
                new Alert(Alert.AlertType.INFORMATION, mensagem)
                        .showAndWait();
            }
        }




