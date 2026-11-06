package com.senai.javafx.aula01;

import com.senai.javafx.aula01.controllers.DadosOficina;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ConsultarCliente {

    public void mostrar(Stage stage) {

                Label titulo = new Label("CONSULTAR CLIENTE");

                TextField txtCpf = new TextField();
                txtCpf.setPromptText("Digite o CPF");

                ListView<String> lista = new ListView<>();

                Button buscar = new Button("Buscar");
                Button listar = new Button("Listar todos");
                Button voltar = new Button("Voltar ao menu");

                buscar.setOnAction(e -> {
                    lista.getItems().clear();

                    for (DadosOficina.Cliente c :
                            DadosOficina.clientes) {
                        if (c.cpf.equalsIgnoreCase(txtCpf.getText().trim())) {
                            lista.getItems().add(c.toString());
                        }
                    }

                    if (lista.getItems().isEmpty()) {
                        lista.getItems().add("Cliente não encontrado.");
                    }
                });

                listar.setOnAction(e -> {
                    lista.getItems().clear();

                    for (DadosOficina.Cliente c :
                            DadosOficina.clientes) {
                        lista.getItems().add(c.toString());
                    }
                });

                voltar.setOnAction(e ->
                        new HelloApplication().start(stage));

                VBox tela = new VBox(10,
                        titulo, txtCpf, buscar, listar, lista, voltar);

                tela.setStyle("-fx-padding: 20;");
                stage.setTitle("Consultar Cliente");
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
        }



