package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroServico {

    public void mostrar(Stage stage) {
        ComboBox<DadosOficina.OrdemServico> ordens =
                new ComboBox<>();

        ordens.getItems().setAll(DadosOficina.ordens);

        TextField descricao = new TextField();
        descricao.setPromptText("Descrição do serviço");

        TextField valor = new TextField();
        valor.setPromptText("Valor (ex.: 150,00)");

        ListView<DadosOficina.Servico> servicos =
                new ListView<>();

        ordens.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();
            servicos.getItems().clear();

            if (os != null) {
                servicos.getItems().setAll(os.servicos);
            }
        });

        Button adicionar = new Button("Adicionar serviço");
        Button remover = new Button("Remover serviço");
        Button voltar = new Button("Voltar ao menu");

        adicionar.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();

            if (os == null) {
                avisar("Selecione uma ordem.");
                return;
            }

            if (os.status.equals("Finalizada")) {
                avisar("A ordem está finalizada.");
                return;
            }

            String d = descricao.getText().trim();
            String textoValor = valor.getText().trim()
                    .replace(",", ".");

            if (d.isEmpty() || textoValor.isEmpty()) {
                avisar("Preencha descrição e valor.");
                return;
            }

            try {
                double v = Double.parseDouble(textoValor);

                if (!Double.isFinite(v) || v < 0) {
                    avisar("Informe um valor válido.");
                    return;
                }

                DadosOficina.Servico s =
                        new DadosOficina.Servico(d, v);

                os.servicos.add(s);
                servicos.getItems().add(s);

                descricao.clear();
                valor.clear();
            } catch (NumberFormatException ex) {
                avisar("Valor inválido. Exemplo: 150,00");
            }
        });

        remover.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();
            DadosOficina.Servico s =
                    servicos.getSelectionModel().getSelectedItem();

            if (os == null || s == null) {
                avisar("Selecione a ordem e o serviço.");
                return;
            }

            if (os.status.equals("Finalizada")) {
                avisar("A ordem está finalizada.");
                return;
            }

            os.servicos.remove(s);
            servicos.getItems().remove(s);
            avisar("Serviço removido.");
        });

        voltar.setOnAction(e ->
                new HelloApplication().start(stage));

        VBox tela = new VBox(10,
                new Label("SERVIÇOS DA ORDEM"),
                ordens, descricao, valor,
                adicionar, servicos, remover, voltar);

        tela.setStyle("-fx-padding: 20;");
        stage.setTitle("Cadastro de Serviço");

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
